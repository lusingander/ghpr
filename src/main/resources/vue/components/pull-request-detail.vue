<template id="pull-request-detail">
  <div>
    <pull-request-link :url="pr.url" :number="pr.number"></pull-request-link>
    <span class="pull-request-date">
      <span>Created: {{ createdAt }}</span>
      <span v-if="pr.closedAt">Closed: {{ closedAt }}</span>
    </span>
    <change-lines :additions="pr.additions" :deletions="pr.deletions"></change-lines>
  </div>
</template>
<script>
  Vue.component("pull-request-detail", {
    template: "#pull-request-detail",
    props: ["pr"],
    computed: {
      createdAt: function() {
        return new Date(this.pr.createdAt).toLocaleString();
      },
      closedAt: function() {
        return new Date(this.pr.closedAt).toLocaleString();
      }
    }
  });
</script>
<style>
  .pull-request-date {
    color: #909399;
    padding: 0px 10px;
  }
</style>
