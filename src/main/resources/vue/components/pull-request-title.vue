<template id="pull-request-title">
  <div>
    <span class="pull-request-state">
      <el-tag :type="stateType" size="small">{{ pr.state }}</el-tag>
    </span>
    <span class="pull-request-name">{{ pr.title }}</span>
  </div>
</template>
<script>
  Vue.component("pull-request-title", {
    template: "#pull-request-title",
    props: ["pr"],
    computed: {
      stateType: function() {
        switch (this.$props.pr.state) {
          case "MERGED": return "success";
          case "OPEN": return "info";
          case "CLOSED": return "danger";
        }
        return "";
      }
    }
  });
</script>
<style>
  .pull-request-name {
    color: #606266;
  }
  .pull-request-state {
    padding: 0px 5px;
  }
</style>
