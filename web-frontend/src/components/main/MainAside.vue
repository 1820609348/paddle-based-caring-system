<template>
  <el-row class="height-full container">
    <el-col :span="24" class="height-full">
      <el-menu
          default-active="0"
          class="el-menu-vertical-demo menu-align height-full"
          background-color="#fcfcfe"
          text-color="#55295c"
          active-text-color="#bfd152">
        <!--2. 定义子菜单，相当于菜单的嵌套，和菜单写法基本一致，但要多出一个template用于定义子菜单的名字-->
        <el-submenu v-for="item in menuData" :index="item.label" :key="item.label">
          <template slot="title">
            <i :class="`el-icon-${item.icon}`"></i>
            <span>{{ item.label }}</span>
          </template>
          <el-menu-item v-for="subitem in item.children" :key="subitem.label" :index="subitem.label"
                        @click="clickMenu(subitem)">{{ subitem.label }}
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-col>
  </el-row>
</template>
<script>
export default {
  data() {
    return {
      menuData: [
        {
          label: '数据汇总',
          icon: 's-data',
          children: [
            {
              path: '/main/realtime-statistics',
              name: 'realtime-statistics',
              label: '实时统计',
              icon: 'data-line',
            },
            {
              path: '/main/mission-progress',
              name: 'mission-progress',
              label: '任务进展',
              icon: 'user',
            },
          ]
        },
        {
          label: '任务详情',
          icon: 'data-board',
          children: [
            {
              path: '/main/risk-occured',
              name: 'risk-occured',
              label: '风险发生管理',
              icon: 'data-board',
            },
          ]
        },
        {
          label: '常态化管理',
          icon: 'setting',
          children: [
            {
              path: '/main/elder-info',
              name: 'elder-info',
              label: '易风险老人信息状态管理',
              icon: 'user',
            },
            {
              path: '/main/family-info',
              name: 'family-info',
              label: '家属信息管理',
              icon: 'coordinate',
            },
            {
              path: '/main/logs-info',
              name: 'logs-info',
              label: '日志信息管理',
              icon: 'files',
            },
          ],
        },
      ]
    }
  },
  methods: {
    clickMenu(item) {
      if (this.$route.path !== item.path) {
        this.$router.push(item.path);
      }
    },
  },
  computed: {}
}
</script>
<style scoped lang="less">

//可以帮助菜单项左侧对齐
.menu-align {
  text-align: left;
}

.background {
  background-color: #545c64;
}

.height-full {
  height: 100%;
}
</style>