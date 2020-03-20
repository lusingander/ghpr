<template id="change-lines">
  <span>
    <span class="change-lines-additions">+{{ additions }}</span>
    <span class="change-lines-deletions">-{{ deletions }}</span>
  </span>
</template>
<script>
  Vue.component("change-lines", {
    template: "#change-lines",
    props: ["additions", "deletions"]
  });
</script>
<style>
  .change-lines-additions {
    color: #67C23A;
  }
  .change-lines-deletions {
    color: #F56C6C;
  }
</style>
