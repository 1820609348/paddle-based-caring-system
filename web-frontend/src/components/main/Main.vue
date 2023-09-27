<template>
  <!--container-->
  <!--整体按照纵向排列，纵向等高的在一个container中写-->

    <el-container class="container">
      <!--header-->
      <el-header class="header">
        <MainNav/>
      </el-header>
      <!--main-->
      <el-container>
        <el-aside width="20%" class=" aside">
          <MainAside class="main-aside"></MainAside>
        </el-aside>
        <el-main class=" main">
          <MainContent></MainContent>
        </el-main>
      </el-container>
    </el-container>
</template>

<script>
import MainAside from "@/components/main/MainAside.vue";
import MainNav from "@/components/main/MainNav.vue";
import MainContent from "@/components/main/MainContent.vue"

export default {
  components: {MainAside, MainNav, MainContent},
  data() {
    return {}
  },
  methods: {
    onSubmit(path) {
      this.$router.push(path)
    }
  }
}
</script>
<style>
.container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.el-main, .el-header {
  background-color: white;
}
</style>