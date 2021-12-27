<template>
  <div class="cart-page">
    <div class="cart-message">
      <div v-if="$store.state.cart.numberOfItems < 1">Your Cart is Empty</div>
      <div v-if="$store.state.cart.numberOfItems === 1">
        Your Cart Contains 1 Book
      </div>
      <div v-if="$store.state.cart.numberOfItems > 1">
        Your Cart Contains {{ cart.numberOfItems }} Books
      </div>
      <button
        v-if="$store.state.cart.numberOfItems > 0"
        class="button-primary-inverse"
        @click="clearCart()"
      >
        Clear Cart
      </button>
    </div>
    <cart-table></cart-table>
    <div class="continue-buttons">
      <router-link
        :to="{
          name: 'category',
          params: { name: selectedCategoryName },
        }"
        class="button-primary-inverse"
        >Continue Shopping
      </router-link>
      <router-link
        v-if="$store.state.cart.numberOfItems > 0"
        class="button-primary-border"
        to="../checkout"
        >Proceed to Checkout
      </router-link>
    </div>
  </div>
</template>

<script>
import CartTable from "@/components/CartTable";
import { mapState } from "vuex";

export default {
  name: "Cart",
  components: { CartTable },
  computed: {
    ...mapState(["cart", "selectedCategoryName"]),
  },
  methods: {
    clearCart: function () {
      this.$store.dispatch("clearCart");
    },
  },
};
</script>

<style scoped>
.cart-page {
  padding: 1em;
  display: flex;
  flex-direction: column;
  gap: 1em;
  margin: 0 4em;
}

.cart-message {
  align-self: center;
  display: flex;
  justify-content: space-around;
  flex-direction: column;
  gap: 0.2em;
}

button {
  padding: 0 0.5em;
  font-size: 0.75rem;
}

.continue-buttons {
  display: flex;
  justify-content: flex-end;
  text-align: center;
  gap: 1em;
}
</style>
