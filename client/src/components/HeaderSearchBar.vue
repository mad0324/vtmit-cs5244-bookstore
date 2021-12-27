<template>
  <form>
    <input type="text" />
    <button class="button-nothing">
      <i class="fas fa-search"></i>
    </button>
  </form>
</template>

<script>
export default {
  name: "HeaderSearchBar",
};
</script>

<style scoped>
form {
  background-color: white;
  border-radius: 4px;
  border: var(--clr-bg-tertiary);
  border-width: 1px;
  border-style: solid;
  display: flex;
}

input {
  border-radius: 4px;
  border: none;
  flex-grow: 1;
  outline: none;
}
</style>
