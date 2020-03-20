<template id="owner-detail">
  <div>
    <el-link :href="url" target="_blank">{{ url }}</el-link>
  </div>
</template>
<script>
  Vue.component("owner-detail", {
    template: "#owner-detail",
    props: ["url"],
  });
</script>
<style>
</style>
