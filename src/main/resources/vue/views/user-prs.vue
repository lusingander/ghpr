<template id="user-prs">
  <div class="user-prs">
    <user-profile :profile="profile"></user-profile>
    <user-prs-summary :total="total"></user-prs-summary>
    <div v-if="owners">
      <el-collapse v-model="activeOwners">
        <el-collapse-item v-for="owner in owners" :key="owner.name" :name="owner.name">
          <template slot="title">
            <owner-title :name="owner.name"></owner-title>
          </template>
          <repositories :repos="owner.repositories"></repositories>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div v-if="error">
      {{ error }}
    </div>
    <app-footer></app-footer>
  </div>
</template>
<script>
  Vue.component("user-prs", {
    template: "#user-prs",
    data: () => ({
      activeOwners: [],
      profile: null,
      owners: null,
      cursors: null,
      total: 0,
      error: null,
    }),
    created() {
      let userId = this.$javalin.pathParams["id"];
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      axios.get("/api/user/" + userId).then(response => {
        this.profile = response.data;
      }).catch(error =>
        this.error = error
      );
      axios.get("/api/user/" + userId + "/prs").then(response => {
        const data = response.data;
        this.activeOwners = data.owners.map(o => o.name);
        this.owners = data.owners;
        this.cursors = data.cursors;
        this.total = data.totalCount;
      }).catch(error =>
        this.error = error
      ).finally(() =>
        loading.close()
      );
    },
  });
</script>
<style>
  .user-prs {
    padding: 30px 50px 40px;
  }
</style>
