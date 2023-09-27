<template>
  <div>

    <!--对话框-->
    <el-dialog
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose"
        class="dialog">
      <h1 style="text-align: center;font-size: 32px;margin: 0 0 15px;">{{ dialogLabel }}信息</h1>
      <el-form ref="form" :model="dialogForm" label-width="80px" class="main-dialog">
        <div class="left-dialog">
          <el-card class="item">
            <el-form-item label="姓名" class="text">
              <el-input v-model="dialogForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" class="text">
              <el-select v-model="dialogForm.gender" placeholder="性别">
                <el-option label="男性" value="男性"></el-option>
                <el-option label="女性" value="女性"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="年龄" class="text">
              <el-input v-model="dialogForm.age"></el-input>
            </el-form-item>
          </el-card>
        </div>
        <div class="right-dialog">
          <el-card class="item">
            <el-form-item label="状态" class="text">
              <el-select v-model="dialogForm.status" placeholder="老人状态">
                <el-option label="健康" value="健康"></el-option>
                <el-option label="良好" value="良好"></el-option>
                <el-option label="一般" value="一般"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="家庭情况" class="text">
              <el-input v-model="dialogForm.family"></el-input>
            </el-form-item>
            <el-form-item label="居住地点" class="text">
              <el-input v-model="dialogForm.pos"></el-input>
            </el-form-item>
          </el-card>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="clickSubmit">提 交</el-button>
        <el-button @click="clickCancel">关 闭</el-button>
      </span>
    </el-dialog>

    <!--上方按钮-->
    <div style="display: flex;justify-content: space-between">
      <span>
        <el-button @click="handleAdd">新增</el-button>
        <el-button @click="refreshButton">刷新</el-button>
      </span>
      <span>
        <el-input style="width: 200px;margin-right: 10px" v-model="selectStr" prefix-icon="el-icon-search"
                  placeholder="输入姓名进行查询">
        </el-input>
        <el-button @click="selectData(selectStr)">查询</el-button>
        <el-button @click="selectData('')">重置</el-button>
      </span>
    </div>
    <div>
      <el-card class="user-table">
        <!--表格-->
        <el-table
            :data="this.pageData"
            style="width: 100%"
            height="480px">
          <el-table-column prop="name" label="姓名" key="name"
                           header-align="center"></el-table-column>
          <el-table-column prop="guardian" label="监护老人" key="guardian"
                           header-align="center">
            <template slot-scope="scope">
              <p @click="clickElder(scope.row.guardian)" class="elder-name">{{ scope.row.guardian }}</p>
            </template>
          </el-table-column>
          <el-table-column prop="relationship" label="与老人的关系" key="relationship"
                           header-align="center"></el-table-column>
          <el-table-column prop="registrationDate" label="登记时间" key="registrationDate"
                           header-align="center">
          </el-table-column>
          <el-table-column prop="familySituation" label="家庭情况" key="familySituation"
                           header-align="center">
          </el-table-column>
          <el-table-column prop="residence" label="居住地点" key="residence"
                           header-align="center">
          </el-table-column>
          <el-table-column prop="op" label="操作" header-align="center">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.row)">修改</el-button>
              <el-button size="mini" style="background-color:rgba(205,58,2,0.8);color:#fffefb"
                         @click="handleDelete(scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <!--分页-->
        <div style="display: flex;justify-content: center">
          <el-pagination
              layout="prev, pager, next"
              :current-page.sync="currentPage"
              :total="totalTableData"
              :page-size="pageSize"
              @current-change="handlePage">
          </el-pagination>
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
import {addFamilyInfo, deleteFamilyInfo, getFamilyInfo, updateFamilyInfo} from "@/api";

export default {
  data() {
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('年龄不能为空'));
      }
      if (!Number.isInteger(value)) {
        callback(new Error('请输入数字值'));
      } else {
        callback();
      }
    };
    return {
      dialogVisible: false,
      formLabelWidth: '70px',
      dialogForm: {
        name: "",
        age: "",

      },
      rules: {
        name: {required: true, message: '姓名不得为空', trigger: 'blur'},
        gender: {required: true, message: '性别不得为空', trigger: 'input'},
        age: [
          {required: true, message: '性别不得为空', trigger: 'blur'},
          {validator: checkAge, trigger: 'blur'},
        ]
      },
      tableHeader: {
        id: '编号',
        name: '姓名',
        gender: "性别",
        pos: '位置',
        status: '身体状态',
        family: "家庭情况",
      },
      tableData: [],
      tableDataBack: [],
      pageData: [],
      opMode: 0,//0表示新增，1表示编辑
      totalTableData: 0,//表格总条数
      pageSize: 6,//分页大小
      selectStr: "",//查询字符串
      currentPage: 1
    }
  }
  ,
  computed: {
    dialogLabel() {
      if (this.opMode === 0) {
        return "新增"
      } else {
        return "修改"
      }
    },

  },
  mounted() {
    this.refresh()
  }
  ,
  methods: {
    //刷新状态
    refresh() {
      getFamilyInfo().then(value => {
        this.tableData = value.data;
        this.tableDataBack = this.tableData//数据备份，为了查询功能的实现
        this.pageData = this.getPage(this.pageSize, this.currentPage)//保持页不变
        this.totalTableData = value.data.length || 0;
        //如果有查询内容，保持相对查询不变
        if (this.selectStr) {
          this.selectData(this.selectStr);
        }
      }).catch(err => {
        console.log(err)
      })
    },
    //status颜色
    tagColor(status) {
      if (status === "健康") {
        return 'success'
      } else if (status === "良好") {
        return 'warning'
      } else {
        return 'danger'
      }
    },
    //取消
    clickCancel() {
      this.handleClose();
    },
    //提交
    clickSubmit() {
      if (this.opMode === 0) {
        //新增逻辑
        addFamilyInfo(this.dialogForm).then(res => {
          if (res.data === true) {
            this.$message.success("新建成功");
            this.refresh();
          } else {
            this.$message.error("新建失败");
            this.refresh()
          }
        })
      }
      if (this.opMode === 1) {
        //编辑逻辑
        updateFamilyInfo(this.dialogForm).then(res => {
          if (res.data === true) {
            this.$message.success("编辑成功");
            this.refresh();
          } else {
            this.$message.error("编辑失败");
            this.refresh()

          }
        })
      }
      /*向后端发送数据*/
      this.handleClose();
      this.dialogVisible = false;
    },
    //表单关闭时清空数据和状态
    handleClose() {
      this.dialogVisible = false;
      this.dialogForm = {};
    },
    //新建
    handleAdd() {
      this.opMode = 0;
      this.dialogVisible = true;
    },
    //修改
    handleEdit(row) {
      this.opMode = 1;
      this.dialogVisible = true;
      //注意这里不能直接赋值，因为需要深拷贝
      this.dialogForm = row;
    },
    //删除
    handleDelete(row) {
      deleteFamilyInfo(row["id"]).then(() => {
        this.refresh();
        this.$message.success("删除成功")
      }).catch(err => {
        this.$message.error("删除失败")
        console.log(err)
      })
    },
    //点击分页
    handlePage(val) {
      this.pageData = this.getPage(this.pageSize, val)
    },
    //返回分页内容
    getPage(pageSize, pageCount) {
      let res = this.tableData.slice(pageSize * (pageCount - 1), pageSize * pageCount)
      return res;
    },
    //子串查询所有结果
    selectData(selectStr) {
      this.tableData = this.tableDataBack.filter((item) => {
        return item.name.indexOf(selectStr) !== -1
      })
      this.pageData = this.getPage(this.pageSize, 1);//返回第一页
      this.totalTableData = this.tableData.length || 0;
      if (!selectStr) this.selectStr = selectStr;
    },
    //刷新按钮
    refreshButton() {
      this.refresh();
      this.$message.success("刷新成功")
    },
    //rank的颜色
    getRankColor(rank) {
      if (rank === "一级") {
        return "danger"
      } else {
        if (rank === "二级") {
          return "warning"
        } else {
          return "primary"
        }
      }
    },
    //status的颜色
    getStatusColor(status) {
      if (status === "救援中") return "warning"
      if (status === "救援成功") return "success"
    },
    //点击老人姓名跳转到详情页面
    clickElder(name) {
      localStorage.setItem("name", name)
      this.$router.push('/main/elder-info')
    }
  }
}
</script>
<style>
.elder-name:hover {
  color: #bfd152;
  cursor: pointer;
}

.dialog {
  .main-dialog {
    display: flex;

    .left-dialog {
      width: 48%;
      margin: 10px;

      .older-img {
        object-fit: cover;
        height: 120px;
        width: 120px;
      }
    }

    .right-dialog {
      width: 48%;
      margin: 10px;
    }

    .item {
      width: 100%;
      margin: 5px;

      .text {
        font-size: 18px;
        line-height: 24px;
      }

      .el-input__inner {
        width: 200px;
      }
    }
  }
}

.user-table {
  margin-top: 20px;
}

& .text {
  text-indent: 0 !important;
}

.el-form-item__label {
  text-align: left;
  padding: 3px;
}
</style>