<template id="user-prs">
    <div v-if="data">
        {{ data }}
    </div>
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