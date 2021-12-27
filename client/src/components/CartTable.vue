<template>
  <table class="cart-table" v-if="!cart.empty">
    <tr>
      <th></th>
      <th>Title</th>
      <th>Price</th>
      <th>Quantity</th>
      <th>Total</th>
    </tr>
    <tr v-for="item in cart.items" :key="item.book.bookId">
      <td class="image">
        <img
          :src="
            require('@/assets/images/books/' + bookImageFileName(item.book))
          "
          :alt="item.book.title"
        />
      </td>
      <td class="book-title">{{ item.book.title }}</td>
      <td class="book-price">{{ item.book.price | asDollarsAndCents }}</td>
      <td class="book-quantity">
        x<input
          type="number"
          min="0"
          max="99"
          step="1"
          v-model.trim.number.lazy="item.quantity"
          @change="updateCart(item.book, item.quantity)"
        />
      </td>
      <td class="book-total">
        {{ (item.book.price * item.quantity) | asDollarsAndCents }}
      </td>
    </tr>
    <tr class="footer-row" v-if="cart.numberOfItems > 0">
      <td></td>
      <td class="footer-title">All Books Subtotal:</td>
      <td></td>
      <td class="book-quantity">{{ cart.numberOfItems }}</td>
      <td class="book-total">
        {{ cart.subtotal | asDollarsAndCents }}
      </td>
    </tr>
  </table>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "CartTable",
  computed: {
    ...mapState(["cart"]),
  },
  methods: {
    bookImageFileName: function (book) {
      let name = book.title.toLowerCase();
      name = name.replace(/ /g, "-");
      name = name.replace(/[.,/#!$%^&*;:{}=?_`~()]/g, "");
      return `${name}.gif`;
    },
    updateCart: function (book, quantity) {
      this.$store.dispatch("updateCart", { book, quantity });
    },
  },
};
</script>

<style scoped>
img {
  border: 1px solid black;
  border-radius: 4px;
  height: 100px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
  max-width: 80px;
  align-self: center;
}

.image {
  padding: 0.2em;
}

input {
  background-color: var(--clr-bg-tertiary);
  border: none;
  border-radius: 8px;
  outline: none;
  text-align: center;
  width: 3.125em;
}

input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  opacity: 1;
}

.book-price,
.book-total {
  text-align: right;
  padding: 1em;
}

.book-quantity {
  text-align: center;
}

.footer-title {
  text-align: right;
}

table {
  border-spacing: 0;
  border: 2px solid var(--clr-secondary);
  border-radius: 8px;
  background: none;
}

th {
  padding-top: 0.75em;
  padding-bottom: 0.75em;
  background-color: var(--clr-primary);
  color: white;
}

th:first-child {
  border-top-left-radius: 5px;
}

th:last-child {
  border-top-right-radius: 5px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:nth-child(odd) {
  background-color: white;
}

tr:hover {
  background-color: #ddd;
}

tr.footer-row {
  height: 50px;
  font-weight: bold;
  color: white;
  background-color: var(--clr-primary);
}

tr:last-child td:first-child {
  border-bottom-left-radius: 5px;
}

tr:last-child td:last-child {
  border-bottom-right-radius: 5px;
}
</style>
