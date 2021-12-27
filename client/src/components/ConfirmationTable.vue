<template>
  <table>
    <tr>
      <th>Title</th>
      <th>Quantity</th>
      <th>Price</th>
    </tr>
    <tr v-for="(item, index) in orderDetails.lineItems" :key="item.bookId">
      <td class="book-title">
        {{ orderDetails.books[index].title }}
      </td>
      <td class="book-quantity">{{ item.quantity }}</td>
      <td class="book-total">
        {{
          (orderDetails.books[index].price * item.quantity) | asDollarsAndCents
        }}
      </td>
    </tr>
    <tr class="surcharge-row">
      <td class="book-title">Surcharge</td>
      <td></td>
      <td class="book-total">{{ cart.surcharge | asDollarsAndCents }}</td>
    </tr>
    <tr class="footer-row">
      <td></td>
      <td class="footer-title">Order Total:</td>

      <td class="book-total">
        {{ orderDetails.order.amount | asDollarsAndCents }}
      </td>
    </tr>
  </table>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "ConfirmationTable",
  computed: {
    ...mapState(["orderDetails", "cart", "selectedCategoryName"]),
  },
};
</script>

<style scoped>
.book-title {
  padding-left: 0.5em;
}

.book-total {
  text-align: right;
  padding: 0.5em;
}

.book-quantity {
  text-align: center;
}

table {
  border-spacing: 0;
  border: 2px solid var(--clr-secondary);
  border-radius: 8px;
  background: none;
  min-width: 23em;
}

th {
  background-color: var(--clr-primary);
  color: white;
  padding: 0.25em;
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

tr.surcharge-row {
  font-style: italic;
}

tr.footer-row {
  font-weight: bold;
  color: white;
  background-color: var(--clr-primary);
}

.footer-title {
  text-align: right;
}

tr:last-child td:first-child {
  border-bottom-left-radius: 5px;
}

tr:last-child td:last-child {
  border-bottom-right-radius: 5px;
}
</style>
