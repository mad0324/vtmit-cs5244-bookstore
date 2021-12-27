<template>
  <ul>
    <router-link to="/" tag="li">
      <img src="@/assets/images/books/holes.gif" alt="holes" />
    </router-link>
    <router-link to="/" tag="li">
      <img
        src="@/assets/images/books/watership-down.gif"
        alt="watership-down"
      />
    </router-link>
    <router-link to="/" tag="li">
      <img src="@/assets/images/books/the-hobbit.gif" alt="the-hobbit" />
    </router-link>
    <router-link to="/" tag="li">
      <img
        src="@/assets/images/books/tarzan-of-the-apes.gif"
        alt="tarzan-of-the-apes"
      />
    </router-link>
    <router-link to="/" tag="li">
      <img src="@/assets/images/books/peter-rabbit.gif" alt="peter-rabbit" />
    </router-link>
    <router-link to="/" tag="li">
      <img
        src="@/assets/images/books/a-brief-history-of-time.gif"
        alt="a-brief-history-of-time"
      />
    </router-link>
    <router-link to="/" tag="li">
      <img
        src="@/assets/images/books/the-time-machine.gif"
        alt="the-time-machine"
      />
    </router-link>
  </ul>
</template>

<script>
export default {
  name: "HomeFavoritesList",
};
</script>

<style scoped>
ul {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
  justify-content: space-around;
}

li {
  text-align: center;
  cursor: pointer;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

img {
  height: 200px;
}
</style>
