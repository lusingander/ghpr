<template id="repository-title">
  <div>
    <span class="repository-name">{{ name }}</span>
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
