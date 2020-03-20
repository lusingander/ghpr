<template id="user-prs">
  <el-collapse v-model="activeOwners">
    <el-collapse-item v-for="owner in owners" :key="owner.name" :name="owner.name">
      <template slot="title">
        <owner-title :name="owner.name"></owner-title>
      </template>
      <owner-detail :url="owner.url"></owner-detail>
      <repositories :repos="owner.repositories"></repositories>
    </el-collapse-item>
  </el-collapse>
</template>
<script>
  Vue.component("user-prs", {
    template: "#user-prs",
    data: () => ({
      activeOwners: [],
      owners: [],
      cursors: null,
      total: 0,
      error: null,
    }),
    created() {
      let userId = this.$javalin.pathParams["id"];
      axios.get("/api/user/" + userId).then(response => {
        const data = response.data;
        this.activeOwners = data.owners.map(o => o.name);
        this.owners = data.owners;
        this.cursors = data.cursors;
        this.total = data.totalCount;
      }).catch(error =>
        this.error = error
      );
    },
  });
</script>
<style>
</style>
