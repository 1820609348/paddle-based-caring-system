<template>
  <el-row class="main">
    <el-col :span="8" style="padding-right: 10px">
      <el-card>
        <div class="hospital">
          <img src="../../assets/redCross.jpg" class="hospital-avator">
          <div class="user-info">
            <p class="city-name">{{ hospitalInfo.cityName }}</p>
            <p class="hospital-name">{{ hospitalInfo.hospitalName }}</p>
          </div>
        </div>
        <div class="hospital-info">
          <div class="info1">
            <p class="label-name">中心地址<span class="value-name">{{ hospitalInfo.hospitalAddress }}</span></p>
            <p class="label-name">负责片区<span class="value-name">{{ hospitalInfo.managedArea }}</span></p>
            <p class="label-name">联系方式<span class="value-name">{{ hospitalInfo.connectTel }}</span></p>
          </div>
          <div class="info2">
            <p class="text1">中心负责人</p>
            <p class="text2">{{ hospitalInfo.lawerPerson }}</p>
          </div>
        </div>
      </el-card>
      <el-card class="mall-info">
        <el-table :data="tableData" stripe style="width:100%">
          <el-table-column v-for="(val,key) in tableHead" :prop="key" :label="val" :key="key"></el-table-column>
        </el-table>
      </el-card>
    </el-col>
    <el-col :span="16" style="padding-left: 10px">
      <!--六个卡片-->
      <div class="card-pos">
        <el-card v-for="item in dealData" :key="item.name" :body-style="{display:'flex'}">
          <!--动态渲染style-->
          <i :class="`el-icon-${item.icon}`" class="icon" :style="{background:item.color}"></i>
          <div class="deal-detail">
            <p class="price">{{ item.value }}人</p>
            <p class="name">{{ item.name }}</p>
          </div>
        </el-card>
      </div>
      <!--折线图-->
      <el-card style="height: 260px">
        <div ref="echart1" style="height: 245px"></div>
      </el-card>
      <div class="two-sub-graph-container">
        <!--两个子图表-->
        <el-card class="sub-graph">
          <div ref="echart2" style="height: 250px"></div>
        </el-card>
        <el-card class="sub-graph">
          <div ref="echart3" style="height: 250px"></div>
        </el-card>
      </div>
    </el-col>

  </el-row>
</template>
<script>
import {getData} from "@/api";
import * as echarts from "echarts"

export default {
  data() {
    return {
      hospitalInfo: {
        cityName: "成都市",
        hospitalName: "急救中心",
        hospitalAddress: "成都市急救中心",
        managedArea: "成华区 锦江区",
        connectTel: "8008008800",
        lawerPerson: "曾勇"
      },
      tableHead: {
        name: '姓名',
        age: '年龄',
        status: '状态',
        pos: '位置',
      },
      tableData: [],
      dealData: [
        {
          name: "今日救援失败",
          value: "0",
          icon: "s-custom",
          color: "#888",
        },
        {
          name: "今日正在救援",
          value: "23",
          icon: "warning",
          color: "#ffb980",
        },
        {
          name: "今日救援成功",
          value: "5",
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "历史救援失败",
          value: "0",
          icon: "s-custom",
          color: "#888",
        },
        {
          name: "历史总救援数",
          value: "124",
          icon: "data-analysis",
          color: "#a262ad",
        },
        {
          name: "历史救援成功",
          value: "106",
          icon: "success",
          color: "#2ec7c9",
        },
      ],
    }
  },
  mounted() {
    this.assignValue();
  },
  methods: {
    //获取所有get方法得到的数据
    assignValue() {
      //table数据
      getData("getTableData").then(val => {
        this.tableData = val.data
      }).catch(err => {
        console.log(err);
      });
      //Lin数据
      getData("getLinData").then(value => {
        let options = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: value.data.map(obj => obj.name)
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: [
            {name:'Day',type: 'value'},
            {name:'History',type: 'value'}
          ],
          series: value.data
        }
        echarts.init(this.$refs.echart1).setOption(options);
      }).catch(error => {
        console.log(error)
      })
      //Bar数据
      getData("getBarData").then(value => {
        let options = {
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          legend: {
            data: value.data.map(obj => obj.name)
          },
          color: ['#b6a2de', '#6ecdf6'],
          series: value.data
        }
        echarts.init(this.$refs.echart2).setOption(options);
      }).catch(error => {
        console.log(error)

      })
      //Pie数据
      getData("getPieData").then(value => {
        let options = {
          tooltip: {
            trigger: 'item'
          },
          textStyle: {
            fontSize: 10
          },
          legend: {
            itemHeight: 8,
            itemWidth: 12,
            left: 'center'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: '50%',
              data: value.data,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        }
        echarts.init(this.$refs.echart3).setOption(options);
      }).catch(error => {
        console.log(error)
      })
    },
  }
}
</script>
<style scoped>
.hospital {
  display: flex;
  padding-bottom: 10px;
  margin-bottom: 10px;
  border-bottom: 1px solid #ccc;

  .hospital-avator {
    height: 120px;
    margin-left: 10px;
  }

  .user-info {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-left: 40px;

    .city-name {
      font-size: 28px;
      margin-bottom: 10px;
    }

    .hospital-name {
      margin: 0;
      font-size: 28px;
      color: #999999
    }
  }
}

.hospital-info {
  display: flex;
  flex-direction: row;
  margin-top: 20px;

  .info1 {
    margin-left: 10px;
    border-right: 1px #ccc solid;
    padding-right: 20px;

    .label-name {
      line-height: 10px;
      font-size: 10px;
      color: #999999;

      .value-name {
        color: #666666;
        font-size: 10px;
        margin-left: 10px;
      }
    }
  }

  .info2 {
    text-align: center;
    width: 46%;
    margin-left: 5px;

    .text1 {
      line-height: 10px;
      color: #999999;
    }

    .text2 {
      line-height: 10px;
      font-size: 20px;
      color: #303133;
    }
  }

}

.mall-info {
  margin-top: 20px;
  height: 460px;
}

.icon {
  width: 80px;
  height: 80px;
  font-size: 30px;
  line-height: 80px;
  text-align: center;
  color: white;
}

.card-pos {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;

  .el-card {
    width: 30%;
    margin-bottom: 20px;
  }

  .deal-detail {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-left: 15px;


    .price {
      font-size: 30px;
      margin: 0 0 10px;
      line-height: 30px;
      height: 30px;
    }

    .name {
      font-size: 14px;
      color: #999999;
      text-align: center;
      margin: 0;
    }
  }
}

.two-sub-graph-container {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;

  .sub-graph {
    height: 235px;
    width: 48%
  }
}
</style>