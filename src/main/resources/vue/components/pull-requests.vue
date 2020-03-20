<template id="pull-requests">
  <div class="pull-requests">
    <el-collapse>
      <el-collapse-item v-for="pr in prs" :key="pr.url">
        <template slot="title">
          <pull-request-title :pr="pr"></pull-request-title>
        </template>
        <pull-request-detail :pr="pr"></pull-request-detail>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>
<script>
  Vue.component("pull-requests", {
    template: "#pull-requests",
    props: ["prs"],
  });
</script>
<style>
  .pull-requests {
    margin: 10px 20px
  }
</style>
