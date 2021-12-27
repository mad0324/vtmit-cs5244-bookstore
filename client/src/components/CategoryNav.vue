<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <template v-for="category in $store.state.categories">
        <router-link
          :key="category.categoryId"
          v-if="$store.state.selectedCategoryName === category.name"
          class="button-primary selected-category-button"
          :to="{ name: 'category', params: { name: category.name } }"
          tag="li"
        >
          {{ category.name }}
        </router-link>
        <router-link
          :key="category.categoryId"
          v-else
          class="button-primary unselected-category-button"
          :to="{ name: 'category', params: { name: category.name } }"
          tag="li"
        >
          {{ category.name }}
        </router-link>
      </template>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "CategoryNav",
};
</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  text-align: center;
  background-color: var(--clr-secondary);
  font-family: var(--font-title);
}

.button-primary.selected-category-button {
  background-color: var(--clr-bg-tertiary);
  color: var(--clr-primary);
}

.button-primary.unselected-category-button,
.button-primary.unselected-category-button:visited {
  background-color: var(--clr-secondary);
  box-shadow: none;
}

.button-primary.unselected-category-button:hover,
.button-primary.unselected-category-button:active {
  background-color: var(--clr-bg-tertiary);
  color: var(--clr-primary);
  box-shadow: none;
}

a,
a:visited,
a:hover,
a:active {
  color: white;
  text-decoration: none;
}

@media (max-width: 600px) {
  .category-buttons {
    flex-direction: column;
  }
}
</style>
