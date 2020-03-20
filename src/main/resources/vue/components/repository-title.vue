<template id="repository-title">
  <div>
    <div class="repository-name">{{ name }}</div>
  </div>
</template>
<script>
  Vue.component("repository-title", {
    template: "#repository-title",
    props: ["name"],
  });
</script>
<style>
  .repository-name {
    font-size: large;
    color: #409EFF;
  }
</style>
