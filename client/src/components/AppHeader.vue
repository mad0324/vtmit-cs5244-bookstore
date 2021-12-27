<template>
  <header class="container">
    <section class="bookstore-logo">
      <router-link to="/">
        <img
          src="@/assets/images/site/logo.png"
          alt="The Book Burrow Logo"
          width="150px"
          height="auto"
        />
      </router-link>
    </section>
    <section class="title-and-search-bar">
      <h1>
        <router-link to="/" class="text-logo">The Book Burrow</router-link>
      </h1>
      <HeaderSearchBar></HeaderSearchBar>
    </section>
    <section class="header-dropdown-and-cart">
      <div class="header-cart-and-login">
        <router-link to="/cart" class="cart button-nothing">
          {{ $store.state.cart.numberOfItems
          }}<i class="cart fas fa-shopping-cart"></i>
        </router-link>
        <button class="button-primary button-login">MD</button>
      </div>
      <header-dropdown-menu></header-dropdown-menu>
    </section>
  </header>
</template>

<script>
import HeaderDropdownMenu from "@/components/HeaderDropdown";
import HeaderSearchBar from "@/components/HeaderSearchBar";

export default {
  name: "AppHeader",
  components: {
    HeaderDropdownMenu,
    HeaderSearchBar,
  },
};
</script>

<style scoped>
header {
  background: var(--clr-bg-secondary);
  display: flex;
  padding: 1em;
  gap: 1em;
  justify-content: space-between;
}

.title-and-search-bar {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.text-logo,
.text-logo:hover {
  font-family: var(--font-title);
  font-size: 4rem;
  text-decoration: none;
  color: var(--clr-primary);
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  min-width: 150px;
}

.header-dropdown-and-cart {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.header-cart-and-login {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.cart {
  font-size: xx-large;
}

.button-login {
  padding: 0.5em 0.5em;
  border-radius: 25%;
  font-size: large;
}

@media (max-width: 600px) {
  header {
    flex-direction: column;
  }
}
</style>
