<template id="repository-detail">
  <div>
    <el-link :href="url" target="_blank">{{ url }}</el-link>
  </div>
</template>
<script>
  Vue.component("repository-detail", {
    template: "#repository-detail",
    props: ["url"],
  });
</script>
<style>
</style>
