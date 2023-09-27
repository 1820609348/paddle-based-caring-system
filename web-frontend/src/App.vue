<template>
  <div id="app">
    <!-- 在路由要显示的位置显示路由 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {}
}
</script>

<style lang="less">
// 去除container自带的8px的margin
html, body {
  margin: 0;
  padding: 0;
}

// 不显示滚动条
::-webkit-scrollbar {
  display: none;
}

// 不显示百度图标
.anchorBL {
  display: none;
}
</style>
