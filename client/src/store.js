import Vue from "vue";
import Vuex from "vuex";
import ApiService from "@/services/ApiService";
import { ShoppingCart } from "@/models/ShoppingCart";

export const CART_STORAGE_KEY = "cart";
export const ORDER_STORAGE_KEY = "order";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    categories: [],
    selectedCategoryName: "Business",
    selectedCategoryBooks: [],
    cart: new ShoppingCart(),
    orderDetails: null,
  },
  mutations: {
    SET_CATEGORIES(state, newCategories) {
      state.categories = newCategories;
    },
    SELECT_CATEGORY(state, category) {
      state.selectedCategoryName = category;
    },
    SET_SELECTED_CATEGORY_BOOKS(state, books) {
      state.selectedCategoryBooks = books;
    },
    ADD_TO_CART(state, book) {
      state.cart.addItem(book, 1);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    UPDATE_CART(state, { book, quantity }) {
      state.cart.update(book, quantity);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    CLEAR_CART(state) {
      state.cart.clear();
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    SET_CART(state, shoppingCart) {
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(shoppingCart));
      let newCart = new ShoppingCart();
      shoppingCart.items.forEach((item) => {
        newCart.addItem(item.book, item.quantity);
      });
      state.cart = newCart;
    },
    SET_ORDER_DETAILS(state, order) {
      state.orderDetails = order;
      sessionStorage.setItem(
        ORDER_STORAGE_KEY,
        JSON.stringify(this.state.orderDetails)
      );
    },
    CLEAR_ORDER_DETAILS(state) {
      state.orderDetails = null;
      sessionStorage.setItem(
        ORDER_STORAGE_KEY,
        JSON.stringify(this.state.orderDetails)
      );
    },
  },
  actions: {
    fetchCategories(context) {
      ApiService.fetchCategories()
        .then((categories) => {
          console.log("Data: " + categories);
          context.commit("SET_CATEGORIES", categories);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
        });
    },
    selectCategory(context, name) {
      context.commit("SELECT_CATEGORY", name);
    },
    fetchSelectedCategoryBooks(context) {
      return ApiService.fetchSelectedCategoryBooks(
        context.state.selectedCategoryName
      )
        .then((books) => {
          console.log("Data: " + books);
          context.commit("SET_SELECTED_CATEGORY_BOOKS", books);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
          throw reason;
        });
    },
    addToCart(context, book) {
      context.commit("ADD_TO_CART", book);
    },
    updateCart(context, { book, quantity }) {
      context.commit("UPDATE_CART", { book, quantity });
    },
    clearCart(context) {
      context.commit("CLEAR_CART");
    },
    placeOrder({ commit, state }, customerForm) {
      commit("CLEAR_ORDER_DETAILS");
      return ApiService.placeOrder({
        cart: state.cart,
        customerForm: customerForm,
      }).then((order) => {
        commit("CLEAR_CART");
        commit("SET_ORDER_DETAILS", order);
      });
    },
  },
});
