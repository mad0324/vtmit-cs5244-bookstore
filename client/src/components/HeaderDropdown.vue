<template>
  <div class="header-dropdown">
    <router-link
      to="../category/Business"
      class="button-hover-menu categories-button"
    >
      Categories <i class="fas fa-caret-down"></i>
    </router-link>
    <ul>
      <template v-for="category in $store.state.categories">
        <router-link
          :key="category.categoryId"
          :to="{ name: 'category', params: { name: category.name } }"
          tag="li"
        >
          {{ category.name }}
        </router-link>
      </template>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HeaderDropdownMenu",
};
</script>

<style scoped>
.categories-button {
  background-color: var(--clr-secondary);
  margin-top: 0.25em;
}

.header-dropdown {
  position: relative;
}

.header-dropdown ul {
  background-color: var(--clr-secondary);
  color: white;
  max-height: 0;
  border-radius: 8px;
  position: absolute;
  overflow-y: hidden;
  transition: max-height 250ms ease-in-out;
}

.header-dropdown:hover ul {
  z-index: 1;
  cursor: pointer;
  max-height: 12em;
  transition: max-height 250ms ease-in-out;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown li:hover {
  background: black;
  border-radius: 8px;
}
</style>
