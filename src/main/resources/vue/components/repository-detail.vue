<template id="repository-detail">
  <div>
    <el-link :href="repo.url" target="_blank">{{ repo.url }}</el-link>
    <span class="repository-tags">
      <el-tag effect="plain">
        <span class="repository-language-color" :style="this.color"></span>{{ repo.langName }}
      </el-tag>
      <el-tag>Fork: {{ repo.forks }}</el-tag>
      <el-tag><i class="el-icon-star-off"></i> {{ repo.stars }}</el-tag>
    </span>
  </div>
</template>
<script>
  Vue.component("repository-detail", {
    template: "#repository-detail",
    props: ["repo"],
    computed: {
      color: function() {
        return "background-color:" + this.$props.repo.langColor
      }
    }
  });
</script>
<style>
  .repository-tags {
    float: right;
    margin-right: 30px;
  }
  .repository-language-color {
    position: relative;
    top: 1px;
    display: inline-block;
    width: 12px;
    height: 12px;
    border-radius: 50%;
    margin-right: 6px;
}
</style>
