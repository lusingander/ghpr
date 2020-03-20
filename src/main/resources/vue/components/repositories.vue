<template id="repositories">
  <div class="repositories">
    <el-collapse>
      <el-collapse-item v-for="repo in repos" :key="repo.name" :name="repo.name">
        <template slot="title">
          <repository-title :name="repo.name"></repository-title>
        </template>
        <repository-detail :url="repo.url"></repository-detail>
        <pull-requests :prs="repo.pullRequests"></pull-requests>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>
<script>
  Vue.component("repositories", {
    template: "#repositories",
    props: ["repos"],
  });
</script>
<style>
  .repositories {
    margin: 10px 20px
  }
</style>
