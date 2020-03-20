<template id="pull-request-detail">
  <div>
    <span class="pull-request-date">
      <span>Created: {{ pr.createdAt }}</span>
      <span v-if="pr.closedAt">Closed: {{ pr.closedAt }}</span>
    </span>
  </div>
</template>
<script>
  Vue.component("pull-request-detail", {
    template: "#pull-request-detail",
    props: ["pr"]
  });
</script>
<style>
  .pull-request-date {
    color: #909399;
    padding: 0px 10px;
  }
</style>
