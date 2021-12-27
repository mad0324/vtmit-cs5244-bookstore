<template>
  <div class="home-page">
    <section class="welcome-text flow-content container">
      <p>Welcome to <strong>The Book Burrow!</strong></p>
      <p>Dig around for something you would like to read!</p>
      <div class="home-buttons">
        <router-link class="button-primary" to="../category/Business"
          >Find Books
        </router-link>
        <router-link
          class="button-primary"
          :to="{ name: 'category', params: { name: randomCategory() } }"
          >Surprise Me
        </router-link>
      </div>
    </section>
    <section class="favorites container">
      <p>Burrow-Themed Favorites</p>
      <home-favorites-list></home-favorites-list>
    </section>
    <section class="buffer">
      <div>&nbsp;</div>
    </section>
  </div>
</template>

<script>
import HomeFavoritesList from "@/components/HomeFavoritesList";

export default {
  name: "Home",
  components: { HomeFavoritesList },
  methods: {
    randomCategory: function () {
      return this.$store.state.categories[
        Math.floor(Math.random() * this.$store.state.categories.length)
      ].name;
    },
  },
};
</script>

<style scoped>
.home-page {
  display: flex;
  flex-direction: column;
  text-align: center;
}

.home-buttons {
  display: flex;
  gap: 0.5em;
  justify-content: center;
}

.favorites {
  background-color: var(--clr-bg-tertiary);
}

.favorites p {
  padding-bottom: 0.75em;
}

.favorite-items a {
  text-align: center;
  cursor: pointer;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}
</style>
