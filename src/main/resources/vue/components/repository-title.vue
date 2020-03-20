<template id="repository-title">
  <div>
    <span class="repository-name">{{ repo.name }}</span>
    <span class="repository-description">{{ repo.description }}</span>
  </div>
</template>
<script>
  Vue.component("repository-title", {
    template: "#repository-title",
    props: ["repo"],
  });
</script>
<style>
  .repository-name {
    font-size: large;
    color: #409EFF;
  }
  .repository-description {
    margin: 0px 20px;
    color: #909399;
  }
</style>
