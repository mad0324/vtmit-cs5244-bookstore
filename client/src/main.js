import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store, { CART_STORAGE_KEY, ORDER_STORAGE_KEY } from "./store";
import "@/assets/css/global.css";
import Vuelidate from "vuelidate";
import TreeView from "vue-json-tree-view";

Vue.config.productionTip = false;

const PriceFormatter = new Intl.NumberFormat("en-US", {
  style: "currency",
  currency: "USD",
  minimumFractionDigits: 2,
});

Vue.filter("asDollarsAndCents", function (cents) {
  return PriceFormatter.format(cents / 100.0);
});

Vue.use(Vuelidate);
Vue.use(TreeView);

new Vue({
  router,
  store,
  render: function (h) {
    return h(App);
  },
  created() {
    const cartString = localStorage.getItem(CART_STORAGE_KEY);
    if (cartString) {
      const shoppingCart = JSON.parse(cartString);
      this.$store.commit("SET_CART", shoppingCart);
    }
    const orderString = sessionStorage.getItem(ORDER_STORAGE_KEY);
    if (orderString) {
      const orderData = JSON.parse(orderString);
      this.$store.commit("SET_ORDER_DETAILS", orderData);
    }
  },
}).$mount("#app");
