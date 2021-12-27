<template>
  <ul>
    <template v-for="book in $store.state.selectedCategoryBooks">
      <category-book-list-item :key="book.bookId" :book="book">
        book
      </category-book-list-item>
    </template>
  </ul>
</template>

<script>
import CategoryBookListItem from "./CategoryBookListItem";

export default {
  name: "CategoryBookList",
  props: {},
  components: {
    CategoryBookListItem,
  },
  data: function () {
    return {};
  },
  created: function () {
    this.$store.dispatch("selectCategory", this.$route.params.name);
    this.$store.dispatch("fetchSelectedCategoryBooks");
  },
  methods: {},
};
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 1em;
}
</style>
