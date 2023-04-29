<template>
  <div><h1>书籍id:{{bookId}}</h1></div>
</template>

<script>
export default {
  name: "bookInfo",
  data() {
    return {
      bookId: undefined,
    };
  },
  created() {
    this.bookId = this.$route.params.bookId;
  },
};
</script>

<style>
</style>