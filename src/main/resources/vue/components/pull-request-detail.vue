<template id="pull-request-detail">
  <div>
    <pull-request-link :url="pr.url" :number="pr.number"></pull-request-link>
    <span class="pull-request-date">
      <span>Created: {{ createdAt }}</span>
      <span v-if="pr.closedAt">Closed: {{ closedAt }}</span>
    </span>
    <change-lines :additions="pr.additions" :deletions="pr.deletions"></change-lines>
    <span class="pull-request-tags">
      <el-tag>
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" class="octicons"><path fill-rule="evenodd" d="M14 1H2c-.55 0-1 .45-1 1v8c0 .55.45 1 1 1h2v3.5L7.5 11H14c.55 0 1-.45 1-1V2c0-.55-.45-1-1-1zm0 9H7l-2 2v-2H2V2h12v8z"></path></svg>
        {{ pr.comments }}
      </el-tag>
    </span>
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
  .pull-request-tags {
    float: right;
    margin-right: 30px;
  }
  .pull-request-tags .octicons {
    height: 12px;
    fill: #409EFF;
    vertical-align: text-bottom;
  }
</style>
