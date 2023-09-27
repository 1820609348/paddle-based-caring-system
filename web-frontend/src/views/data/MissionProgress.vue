<template>
  <div>

    <!--对话框-->
    <el-dialog
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose"
        class="dialog">
      <h1 style="text-align: center;font-size: 32px;margin: 0 0 15px;">{{ dialogLabel }}信息</h1>
      <div class="main-dialog">
        <div class="left-dialog">
          <el-card class="item">
            <div style="display: flex">
              <img src="../../assets/older.png" class="older-img">
              <div>
                <p class="text">姓名：{{ dialogForm.name }}</p>
                <p class="text">年龄：{{ dialogForm.age }}</p>
              </div>
            </div>
          </el-card>
          <el-card class="item">
            <p style="text-align: center">紧急联系人：曾勇</p>
            <p style="text-align: center">紧急联系电话：8008008800</p>
          </el-card>
        </div>
        <div class="right-dialog">
          <el-card class="item">
            <p>疾病史：冠心病，心肌梗塞，关节炎伴有骨质疏松</p>
            <p>既往史：近五年未有摔倒或罹患重大疾病历史</p>
            <p>认知功能：完善</p>
            <p>在服药物：无</p>
            <p>高危疾病：无</p>
            <p>体型：体型偏瘦，身高163cm，有稍许驼背，头发几乎全白</p>
          </el-card>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="clickCancel">关 闭</el-button>
      </span>
    </el-dialog>

    <!--上方按钮-->
    <div style="display: flex;justify-content: space-between">
      <span>
        <el-button @click="handleAdd">检查</el-button>
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
          <el-table-column prop="option" label="类型" key="option"
                           header-align="center"></el-table-column>
          <el-table-column prop="name" label="待救援人姓名" key="name"
                           header-align="center"></el-table-column>
          <el-table-column prop="team" label="救援团队" key="team"
                           header-align="center"></el-table-column>
          <el-table-column prop="pos" label="位置" key="pos"
                           header-align="center"></el-table-column>
          <el-table-column prop="rank" label="任务等级" key="rank"
                           header-align="center">
            <template slot-scope="scope">
              <el-tag :type="getRankColor(scope.row.rank)">{{ scope.row.rank }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="status" label="救援状态" key="status"
                           header-align="center">
            <template slot-scope="scope">
              <el-tag :type="getStatusColor(scope.row.status)">{{ scope.row.status }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="op" label="操作" header-align="center">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.row)">查看</el-button>
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
import {addUserInfo, deleteUserInfo, getUserInfo, updateUserInfo} from "@/api";
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
        option: '类型',
        name: '待救援人姓名',
        team: '救援团队',
        pos: '位置',
        rank: '任务等级',
        status: '救援状态',
      },
      tableData: [],
      tableDataBack: [],
      pageData: [],
      opMode: 0,//0表示新增，1表示编辑
      totalTableData: 0,//表格总条数
      pageSize: 8,//分页大小
      selectStr: "",//查询字符串
      currentPage: 1
    }
  }
  ,
  computed: {
    dialogLabel() {
      if (this.opMode === 0) {
        return "检查"
      } else {
        return "查看"
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
      getUserInfo().then(value => {
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
    }
    ,
    //取消
    clickCancel() {
      this.handleClose();
    },
    //提交
    clickSubmit() {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          if (this.opMode === 0) {
            //新增逻辑
            addUserInfo(this.dialogForm).then(res => {
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
            updateUserInfo(this.dialogForm).then(res => {
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
        }
      })
    },
    //表单关闭时清空数据和状态
    handleClose() {
      this.dialogVisible = false;
    },
    //新建
    handleAdd() {
      this.opMode = 0;
      this.dialogVisible = true;
    },
    //查看
    handleEdit(row) {
      this.opMode = 1;
      this.dialogVisible = true;
      //注意这里不能直接赋值，因为需要深拷贝
      this.dialogForm.name = row.name;
      this.dialogForm.age = row.age;
    },
    //删除
    handleDelete(row) {
      deleteUserInfo(row["id"]).then(() => {
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
    }
  }
}
</script>
<style>
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
      margin: 10px;

      .text {
        font-size: 18px;
        line-height: 24px;
        text-indent: 1em;
      }
    }
  }
}

.user-table {
  margin-top: 20px;
}

.cell {
  text-align: center;
}

.el-button:hover {
  background-color: rgba(191, 209, 82, 0.1);
  color: #bfd152;
  border: rgba(191, 209, 82, 0.2) 1px solid;
}

.el-button:active, .el-button:focus, .el-input__inner:focus, .el-input__inner:after, .el-select .el-input.is-focus .el-input__inner {
  background-color: rgba(191, 209, 82, 0.1);
  color: #bfd152;
  border: rgba(191, 209, 82, 0.5) 1px solid;
}

.el-select-dropdown__item.selected {
  color: #bfd152;
}

.el-select .el-input__inner:focus {
  border-color: #bfd152;
}

.el-date-table td.current:not(.disabled) span {
  background-color: #bfd152;
}

.el-date-table td.today span, .el-date-table td.available:hover {
  color: #bfd152;
}

</style>