<template id="user-prs-summary">
  <div class="user-prs-summary">
    Total: {{ total }}
  </div>
</template>
<script>
  Vue.component("user-prs-summary", {
    template: "#user-prs-summary",
    props: ["total"],
  });
</script>
<style>
  .user-prs-summary {
    text-align: right;
    margin: 0px 10px 10px;
    font-size: 13px;
    color: #909399;
  }
</style>
