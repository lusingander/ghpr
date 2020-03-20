<template id="owner-title">
  <div>
    <div class="owner-name">{{ name }}</div>
  </div>
</template>
<script>
  Vue.component("owner-title", {
    template: "#owner-title",
    props: ["name"],
  });
</script>
<style>
  .owner-name {
    font-size: large;
    color: #409EFF;
  }
</style>
