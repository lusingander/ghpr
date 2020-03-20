<template id="pull-request-link">
  <span>
    <el-link type="info" icon="el-icon-link" :href="url" target="_blank">
      #{{ number }}
    </el-link>
  </span>
</template>
<script>
  Vue.component("pull-request-link", {
    template: "#pull-request-link",
    props: ["url", "number"]
  });
</script>
