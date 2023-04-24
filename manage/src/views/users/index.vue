<template>
  <div>
    <h1>用户列表</h1>
  </div>
</template>

<script>
import { $userList } from "@/api/user";
export default {
  name: "users",
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
        searchKey: "",
        status: undefined,
      },
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    getUserList() {
      $userList(this.params).then((res) => {
        console.log(res);
      });
    },
  },
};
</script>

<style scoped>
</style>