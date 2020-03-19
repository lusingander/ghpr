<template id="user-prs">
  <el-collapse>
    <el-collapse-item v-for="owner in owners" :key="owner.name">
      <template slot="title">{{ owner.name }}</template>
      <div class="repositories">
        <el-collapse>
          <el-collapse-item v-for="repo in owner.repositories" :key="repo.name">
            <template slot="title">{{ repo.name }}</template>
            <pull-requests :prs="repo.pullRequests"></pull-requests>
          </el-collapse-item>
        </el-collapse>
      </div>
    </el-collapse-item>
  </el-collapse>
</template>
<script>
  Vue.component("user-prs", {
    template: "#user-prs",
    data: () => ({
      owners: [],
      cursors: null,
      total: 0,
      error: null,
    }),
    created() {
      let userId = this.$javalin.pathParams["id"];
      axios.get("/api/user/" + userId).then(response => {
        const data = response.data;
        this.owners = data.owners
        this.cursors = data.cursors
        this.total = data.totalCount
      }).catch(error =>
        this.error = error
      );
    },
  });
</script>
<style>
  .repositories {
    margin: 10px 20px
  }
</style>
