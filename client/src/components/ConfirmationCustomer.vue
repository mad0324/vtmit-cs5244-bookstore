<template>
  <table class="customer-info-table">
    <tr>
      <th colspan="2">Customer Information</th>
    </tr>
    <tr>
      <td class="title">Name:</td>
      <td class="info">
        {{ orderDetails.customer.customerName }}
      </td>
    </tr>
    <tr>
      <td class="title">Address:</td>
      <td class="info">{{ orderDetails.customer.address }}</td>
    </tr>
    <tr>
      <td class="title">Email:</td>
      <td class="info">{{ orderDetails.customer.email }}</td>
    </tr>
    <tr>
      <td class="title">Phone:</td>
      <td class="info">{{ orderDetails.customer.phoneDisplay }}</td>
    </tr>
    <tr>
      <td class="title">Credit Card:</td>
      <td class="info">
        {{ orderDetails.customer.ccNumberDisplay }}
        ({{ orderDetails.customer.ccExpDateDisplay }})
      </td>
    </tr>
    <tr class="footer-row">
      <td></td>
      <td></td>
    </tr>
  </table>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "ConfirmationCustomer",
  computed: {
    ...mapState(["orderDetails", "cart", "selectedCategoryName"]),
  },
};
</script>

<style scoped>
table {
  border-spacing: 0;
  border: 2px solid var(--clr-secondary);
  border-radius: 8px;
  background: none;
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

td.title {
  padding: 0.5em 1em 0.5em 0.5em;
  text-align: right;
}

td.info {
  padding: 0.5em;
}

tr.footer-row {
  height: 0.5em;
  background-color: var(--clr-primary);
}

tr:last-child td:first-child {
  border-bottom-left-radius: 5px;
}

tr:last-child td:last-child {
  border-bottom-right-radius: 5px;
}
</style>
