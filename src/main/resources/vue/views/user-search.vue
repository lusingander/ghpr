<template id="user-search">
  <div>
    <el-row :gutter="10">
      <el-col :span="7" :offset="8">
        <el-input placeholder="GitHub account ID" v-model="input" @keydown.enter.native="search"></el-input>
      </el-col>
      <el-col :span="1">
        <el-button icon="el-icon-search" circle @click="search"></el-button>
      </el-col>
    </el-row>
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
</style>
