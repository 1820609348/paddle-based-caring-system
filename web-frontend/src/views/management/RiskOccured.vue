<template>
  <div>
    <div class="outer-box">
      <div class="left-box">
        <!--列表-->
        <el-card class="recv-info">
          <!--表格-->
          <el-table
              :data="this.tableData"
              style="width: 100%">
            <el-table-column prop="name" label="待救援人姓名" key="name"
                             header-align="center"></el-table-column>
            <el-table-column prop="time" label="请求时间" key="time"
                             header-align="center"></el-table-column>
            <el-table-column prop="pos" label="位置" key="pos"
                             header-align="center"></el-table-column>
            <el-table-column prop="op" label="操作" header-align="center">
              <template slot-scope="scope">
                <div class="button-style">
                  <el-button size="mini" @click="handler(scope.row,scope.row.name,scope.row.lng,scope.row.lat)"
                             class="table-button">查看
                  </el-button>
                  <el-button size="mini" @click="handleRecv(scope.row)" class="table-button">接受</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
        <!--详细信息-->
        <el-card class="detail">
          <h1 style="text-align: center;font-size: 32px;margin: 0 0 15px;color: #909399">详细信息</h1>
          <div class="detail-info">
            <div class="detail-info-side">
              <p class="text-label">姓名：<span class="text-value">{{ currInfo.name }}</span></p>
              <p class="text-label">年龄：<span class="text-value">{{ currInfo.age }}</span></p>
              <p class="text-label">病因：<span class="text-value">{{ currInfo.option }}</span></p>
              <p class="text-label">状态：<span class="text-value">
                <el-tag :type="getStatusColor(currInfo.status)" size="mini" v-if="currInfo.status">{{
                    currInfo.status
                  }}</el-tag>
              </span></p>
              <p class="text-label">等级：<span class="text-value">
                <el-tag :type="getRankColor(currInfo.rank)" size="mini" v-if="currInfo.rank">{{
                    currInfo.rank
                  }}</el-tag>
              </span></p>
            </div>
            <div class="detail-info-side">
              <p class="text-label">时间：<span class="text-value">{{ currInfo.time }}</span></p>
              <p class="text-label">地点：<span class="text-value">{{ currInfo.pos }}</span></p>
              <p class="text-label">经度：<span class="text-value">{{ currInfo.lng }}</span></p>
              <p class="text-label">纬度：<span class="text-value">{{ currInfo.lat }}</span></p>
            </div>
          </div>
        </el-card>
      </div>
      <!--地图-->
      <el-card class="cardStyle">
        <!-- ready,地图组件渲染完毕时触发，返回一个百度地图的核心类和地图实例 -->
        <baidu-map
            id="map"
            class="mapStyle"
            :center="centerPoint"
            :zoom="17"
            :scroll-wheel-zoom="true"
            @ready="handler('',116.388056,39.9075)"
            @click="getPoint"
        >
          <bm-marker
              v-for="marker in markerArr"
              :key="marker.id"
              :position="marker"
              animation="BMAP_ANIMATION_BOUNCE"
          />
        </baidu-map>
      </el-card>
    </div>
  </div>
</template>
<script>
import {getRecvInfo, recvMission} from "@/api";

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
        time: '请求时间',
        pos: '位置',
        rank: '任务等级',
        status: '救援状态',
      },
      tableData: [],
      centerPoint: {
        lng: 0,
        lat: 0,
      },
      markerArr: [
        {
          id: 0,
          lng: 116.388056,
          lat: 39.9075,
        },
      ],
      markerPoint: {},
      currInfo: {}
    }
  },
  computed: {},
  mounted() {
    this.refresh()
  }
  ,
  methods: {
    //刷新状态
    refresh() {
      getRecvInfo().then(value => {
        this.tableData = value.data;
        this.tableData.forEach((item, i) => {
          this.markerArr.push({
            id: i + 1,
            lng: item.lng,
            lat: item.lat
          })
        })
      }).catch(err => {
        console.log(err)
      })
    }
    ,
    //接受
    handleRecv(row) {
      this.tableData = this.tableData.filter((item) => {
        return item.id !== row.id
      })
      row.status = "救援中"
      recvMission(row).then(() => {
        this.$message.success("接受成功！")
      }).catch(() => {
        this.$message.error("接受失败")
      })
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
      if (status === "等待救援") return "danger"
    },
    // 修改中心坐标
    handler(item, lng, lat,) {
      if (item) {
        this.currInfo = item;
        this.centerPoint.lng = item.lng;
        this.centerPoint.lat = item.lat;
      } else {
        this.centerPoint.lng = lng;
        this.centerPoint.lat = lat;
      }
    },
    // 添加点击函数，点击后修改marker的经纬度
    getPoint(e) {
      // 点击获取point经纬度
      const {lng, lat} = e.point;

      // 获取一个随机不重复的字符串做为id
      let id = new Date().getTime() + parseInt(Math.random() * 10000);

      // 新增一个marker标记
      this.markerArr.push({
        id: id,
        lng: lng,
        lat: lat,
      });
    },
  },
}
</script>
<style scoped>
.outer-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;

  .left-box {
    display: flex;
    flex-direction: column;
    width: 48%;

    .recv-info {
      height: 40vh;

      .button-style {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        width: 50%;
        text-align: center;

        .table-button {
          margin-left: 0
        }
      }
    }

    .detail {
      margin-top: 3.5vh;
      height: 42vh;
      width: 100%;

      .text-label {
        color: #55295c;

        .text-value {
          color: rgba(93, 46, 166, 0.98);
        }
      }

      .detail-info {
        display: flex;
        flex-direction: row;

        .detail-info-side {
          width: 50%;
          margin-left: 5px;
        }
      }
    }
  }
}

.mapStyle {
  width: 100%;
  height: 80vh;
}

.cardStyle {
  width: 50%;
  height: 100%;
}


</style>