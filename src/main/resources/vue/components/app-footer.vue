<template id="app-footer">
  <footer>
    <el-link :underline="false" href="http://github.com/lusingander/ghpr" target="_blank">
      <img src="/github-mark.png">
    </el-link>
  </footer>
</template>
<script>
  Vue.component("app-footer", {
    template: "#app-footer"
  });
</script>
<style>
  footer {
    background-color: #FFFFFF;
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    text-align: center;
    padding: 5px;
    border-top: 1px solid #EBEEF5;
  }
  footer img {
    width: 30px;
    height: 30px;
  }
  footer img:hover {
    opacity: 0.6;
  }
</style>
