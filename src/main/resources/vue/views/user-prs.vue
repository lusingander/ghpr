<template id="user-prs">
  <el-collapse>
    <el-collapse-item v-for="owner in data.owners" :key="owner.name">
      <template slot="title">{{ owner.name }}</template>
      <div class="repositories">
        <el-collapse>
          <el-collapse-item v-for="repo in owner.repositories" :key="repo.name">
            <template slot="title">{{ repo.name }}</template>
            <div class="pull-requests">
              <el-collapse>
                <el-collapse-item v-for="pr in repo.pullRequests" :key="pr.url">
                  <template slot="title">{{ pr.title }}</template>
                  State: {{ pr.state }} Created at: {{ pr.createdAt }} {{ pr.url }}
                </el-collapse-item>
              </el-collapse>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
    </el-collapse-item>
  </el-collapse>
  <div v-if="error">
    {{ error }}
  </div>
</template>
<script>
  Vue.component("user-prs", {
    template: "#user-prs",
    data: () => ({
      data: null,
      error: null,
    }),
    created() {
      let userId = this.$javalin.pathParams["id"];
      axios.get("/api/user/" + userId).then(response => {
        this.data = response.data;
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
.pull-requests {
  margin: 10px 20px
}
</style>
