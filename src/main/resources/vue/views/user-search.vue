<template id="user-search">
  <div class="user-search">
    <p>Show GitHub Pull Requests created by the user:</p>
    <el-row :gutter="10">
      <el-col :span="7" :offset="8">
        <el-input placeholder="GitHub account ID" v-model="input" @keydown.enter.native="search"></el-input>
      </el-col>
      <el-col :span="1">
        <el-button icon="el-icon-search" circle @click="search"></el-button>
      </el-col>
    </el-row>
    <app-footer></app-footer>
  </div>
</template>
<script>
  Vue.component("user-search", {
    template: "#user-search",
    data: () => ({
      input: ''
    }),
    methods: {
      search() {
        window.location = `/user/${this.input}`
      }
    }
  });
</script>
<style>
  .user-search {
    padding: 30px 0px;
    text-align: center;
  }
  .user-search p {
    color: #909399;
  }
</style>
