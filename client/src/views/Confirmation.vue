<template>
  <div class="confirmation-page">
    <section class="empty-page" v-if="!$store.state.orderDetails">
      <div>No recent orders.</div>
      <router-link
        :to="{
          name: 'category',
          params: { name: selectedCategoryName },
        }"
        class="button-primary"
        >Continue Shopping
      </router-link>
    </section>
    <section v-else>
      <ul>
        <li class="confirmation-number">
          Confirmation #: {{ orderDetails.order.confirmationNumber }}
        </li>
        <li class="time">{{ new Date(orderDetails.order.dateCreated) }}</li>
      </ul>
      <div class="confirmation-info">
        <section>
          <confirmation-table></confirmation-table>
        </section>
        <section class="customer">
          <confirmation-customer></confirmation-customer>
          <router-link
            :to="{
              name: 'category',
              params: { name: selectedCategoryName },
            }"
            class="button-primary-border"
            >Continue Shopping
          </router-link>
        </section>
      </div>
    </section>
  </div>
</template>

<script>
import ConfirmationTable from "@/components/ConfirmationTable";
import ConfirmationCustomer from "@/components/ConfirmationCustomer";
import { mapState } from "vuex";

export default {
  name: "Confirmation",
  components: { ConfirmationTable, ConfirmationCustomer },
  computed: {
    ...mapState(["orderDetails", "selectedCategoryName"]),
  },
};
</script>

<style scoped>
.confirmation-page {
  padding: 1em;
  display: flex;
  flex-direction: column;
  gap: 1em;
  margin: 0 4em;
}

.empty-page {
  margin-left: auto;
  margin-right: auto;
  display: flex;
  flex-direction: column;
  gap: 1em;
  justify-content: space-around;
  padding: 1em;
  max-width: 13em;
  text-align: center;
}

.confirmation-number {
  text-align: center;
  font-weight: bold;
  color: var(--clr-secondary);
}

.time {
  text-align: center;
  padding-bottom: 1em;
  color: var(--clr-secondary);
}

.confirmation-info {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  gap: 1em;
}

.customer {
  display: flex;
  flex-direction: column;
  gap: 0.5em;
  align-items: flex-end;
}
</style>
