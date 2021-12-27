<template>
  <footer class="container">
    <section class="copyright">
      <div>&#169; 2021 The Book Burrow</div>
    </section>
    <section class="links">
      <router-link to="/">about</router-link>
      <div>|</div>
      <router-link to="/">contact</router-link>
      <div>|</div>
      <router-link to="/">directions</router-link>
    </section>
    <section class="social-media-icons">
      <router-link to="/" class="button-nothing"
        ><i class="fab fa-facebook-square"></i
      ></router-link>
      <router-link to="/" class="button-nothing"
        ><i class="fab fa-twitter-square"></i
      ></router-link>
    </section>
  </footer>
</template>

<script>
export default {
  name: "AppFooter",
};
</script>

<style scoped>
footer {
  background: var(--clr-bg-secondary);
  display: flex;
  gap: 1em;
  justify-content: space-between;
  align-items: center;
}

.copyright,
.links,
.social-media-icons {
  display: flex;
  flex-direction: row;
  width: 33%;
  gap: 0.5em;
}

.links {
  justify-content: center;
}

.social-media-icons {
  flex-direction: row-reverse;
  font-size: 2rem;
}

.copyright {
  font-size: 0.75rem;
  color: var(--clr-neutral);
}
</style>
