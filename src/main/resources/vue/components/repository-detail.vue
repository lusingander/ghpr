<template id="repository-detail">
  <div>
    <el-link :href="url" target="_blank">{{ url }}</el-link>
    <span class="repository-stars-count">
      <el-tag>Fork: {{ forks }}</el-tag>
      <el-tag><i class="el-icon-star-off"></i> {{ stars }}</el-tag>
    </span>
  </div>
</template>
<script>
  Vue.component("repository-detail", {
    template: "#repository-detail",
    props: ["url", "forks", "stars"],
  });
</script>
<style>
  .repository-stars-count {
    float: right;
    margin-right: 30px;
  }
</style>
