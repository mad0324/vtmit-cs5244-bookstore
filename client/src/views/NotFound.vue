<template>
  <div>
    <div>The page your were looking for was not found.</div>
    <router-link class="button-primary" to="/">Return Home </router-link>
  </div>
</template>

<script>
export default {
  name: "NotFound",
};
</script>

<style scoped>
div {
  display: flex;
  flex-direction: column;
  align-self: center;
  gap: 0.5em;
  margin: 0.5em;
}

.button-primary {
  align-self: center;
  text-align: center;
  width: 8em;
  margin-bottom: 0.5em;
}
</style>
