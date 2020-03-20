<template id="repository-link">
  <span>
    <el-link type="info" icon="el-icon-link" :href="url" target="_blank">
      {{ name }}
    </el-link>
  </span>
</template>
<script>
  Vue.component("repository-link", {
    template: "#repository-link",
    props: ["url"],
    computed: {
      name: function() {
        // TODO: FIX
        return this.url.replace("https://github.com/", "");
      }
    }
  });
</script>
