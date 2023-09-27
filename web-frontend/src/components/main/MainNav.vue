<template>
  <el-container>
    <el-col :span="4"><h1>急救中心管理系统</h1></el-col>
    <el-col :span="20">
      <el-menu class="el-menu-demo" mode="horizontal">
        <el-menu-item v-for="item in menuData" :index="item.name" :key="item.name" @click="clickNav(item.name)">
          <i :class="`el-icon-${item.icon}`"></i>
          <span>{{ item.label }}</span>
        </el-menu-item>
        <el-dropdown class="user-space">
          <span class="el-dropdown-link">
            <img class="hospital-avator" src="../../assets/userAvator.jpg">
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人中心</el-dropdown-item>
            <el-dropdown-item>绑定修改</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-menu>

    </el-col>
    <!--    <el-col :span="2" class="">-->

    <!--    </el-col>-->
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      menuData: [
        {
          name: "About",
          label: "关于",
          icon: "info",
        },
        {
          name: "Product",
          label: "急救",
          icon: "bell",
        },
        {
          name: "Partner",
          label: "联系",
          icon: "chat-dot-round"
        },
        {
          name: "Exit",
          label: "退出",
          icon: "bottom-right"
        },
      ],
    }
  },
  methods: {
    clickNav(name) {
      if (name === 'Exit') {
        sessionStorage.removeItem("status")
        this.$router.push('/login')
        this.$message.success("退出成功！")
      }
    }
  }
}
</script>

<style scoped lang="less">
// 容器样式
.el-container {
  height: 100%;
  width: 100%;
}

//字体样式
h1 {
  text-align: center;
  font-family: "宋体";
  color: #1f0846;
}

//图标颜色
i, span {
  color: #55295c;
}

//菜单项flex布局
ul.el-menu-demo.el-menu--horizontal.el-menu {
  display: flex;
  justify-content: end;
}

//鼠标悬浮在菜单上的特效
.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 2px solid #bfd152;
}

// 头像
.user-space {
  display: flex;
  justify-content: center;
  width: 10%;
  margin: auto 0;

  .hospital-avator {
    border-radius: 50%;
    width: 45px;
    margin: auto;
  }
}

</style>