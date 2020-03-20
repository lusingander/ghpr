<template id="user-prs">
  <el-collapse>
    <el-collapse-item v-for="owner in owners" :key="owner.name">
      <template slot="title">{{ owner.name }}</template>
      <repositories :repos="owner.repositories"></repositories>
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
</style>
