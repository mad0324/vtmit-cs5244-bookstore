<template>
  <div class="checkout-page">
    <section class="empty-page" v-if="cart.empty">
      <div>You must have one or more books to check out.</div>
      <router-link
        :to="{
          name: 'category',
          params: { name: $store.state.selectedCategoryName },
        }"
        class="button-primary"
        >Continue Shopping
      </router-link>
    </section>

    <section v-show="checkoutStatus != ''" class="checkoutStatusBox">
      <div v-if="checkoutStatus == 'ERROR'">
        Error: Please fix the problems below and try again.
      </div>

      <div v-else-if="checkoutStatus == 'PENDING'">Processing...</div>

      <div v-else-if="checkoutStatus == 'OK'">Order placed...</div>

      <div v-else>
        <!-- "checkoutStatus == 'SERVER_ERROR'" -->
        An unexpected error occurred, please try again.
      </div>
    </section>

    <section class="checkout-page-body" v-if="!cart.empty">
      <form @submit.prevent="submitOrder">
        <div class="fields input-fields">
          <div class="input-and-error">
            <div>
              <label for="name">Name: </label>
              <input
                type="text"
                size="20"
                id="name"
                name="name"
                v-model.lazy="$v.name.$model"
              />
            </div>
            <template v-if="$v.name.$error">
              <span class="error" v-if="!$v.name.required"
                >Name is required</span
              >
              <span class="error" v-else-if="!$v.name.minLength">
                Name must have at least
                {{ $v.name.$params.minLength.min }} letters.
              </span>
              <span class="error" v-else-if="!$v.name.maxLength">
                Name can have at most
                {{ $v.name.$params.maxLength.max }} letters.
              </span>
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>

          <div class="input-and-error">
            <div>
              <label for="address">Address: </label>
              <input
                type="text"
                size="20"
                id="address"
                name="address"
                v-model.lazy="$v.address.$model"
              />
            </div>
            <template v-if="$v.address.$error">
              <span class="error" v-if="!$v.address.required"
                >Address is required</span
              >
              <span class="error" v-else-if="!$v.address.minLength">
                Address must have at least
                {{ $v.address.$params.minLength.min }} letters.
              </span>
              <span class="error" v-else-if="!$v.address.maxLength">
                Address can have at most
                {{ $v.address.$params.maxLength.max }} letters.
              </span>
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>

          <div class="input-and-error">
            <div>
              <label for="phone">Phone: </label>
              <input
                class="textField"
                type="text"
                size="20"
                id="phone"
                name="phone"
                v-model.lazy="$v.phone.$model"
              />
            </div>
            <template v-if="$v.phone.$error">
              <span class="error" v-if="!$v.phone.required"
                >Phone is required</span
              >
              <span class="error" v-else-if="!$v.phone.phone">
                Must use a valid phone number.
              </span>
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>

          <div class="input-and-error">
            <div>
              <label for="email">Email: </label>
              <input
                type="text"
                size="20"
                id="email"
                name="email"
                v-model.lazy="$v.email.$model"
              />
            </div>
            <template v-if="$v.email.$error">
              <span class="error" v-if="!$v.email.required"
                >Email is required</span
              >
              <span class="error" v-else-if="!$v.email.email">
                Must use a valid email address.
              </span>
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>

          <div class="input-and-error">
            <div>
              <label for="ccNumber">Credit card: </label>
              <input
                type="text"
                size="20"
                id="ccNumber"
                name="ccNumber"
                v-model.lazy="$v.ccNumber.$model"
              />
            </div>
            <template v-if="$v.ccNumber.$error">
              <span class="error" v-if="!$v.ccNumber.required"
                >Credit card is required</span
              >
              <span class="error" v-else-if="!$v.ccNumber.ccNumber">
                Must use a valid credit card number.
              </span>
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>

          <div class="input-and-error">
            <div>
              <label>Exp Date: </label>
              <select v-model="ccExpiryMonth">
                <option
                  v-for="(month, index) in months"
                  :key="index"
                  :value="index + 1"
                >
                  {{ month }} ({{ index + 1 }})
                </option>
              </select>
              <select v-model="ccExpiryYear">
                <option v-for="index in 15" :key="index">
                  {{ yearFrom(index - 1) }}
                </option>
              </select>
            </div>
          </div>
        </div>

        <div class="fields value-fields">
          <div class="value-field">
            <div>Subtotal:</div>
            <div>{{ cart.subtotal | asDollarsAndCents }}</div>
          </div>
          <div class="value-field">
            <div>Surcharge:</div>
            <div>
              {{ cart.surcharge | asDollarsAndCents }}
            </div>
          </div>
          <div class="total-field">
            <div>Total:</div>
            <div>{{ cart.total | asDollarsAndCents }}</div>
          </div>

          <input
            type="submit"
            name="submit"
            class="button-primary"
            :disabled="checkoutStatus == 'PENDING'"
            value="Complete Purchase"
          />
        </div>
      </form>
    </section>
  </div>
</template>

<script>
import {
  required,
  email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";

import isCreditCard from "validator/lib/isCreditCard";
import isMobilePhone from "validator/lib/isMobilePhone";

const creditCard = (value) => isCreditCard(value);
const phone = (value) => isMobilePhone(value, "en-US");

export default {
  data() {
    return {
      name: "",
      address: "",
      phone: "",
      email: "",
      ccNumber: "",
      ccExpiryMonth: new Date().getMonth() + 1,
      ccExpiryYear: new Date().getFullYear(),
      checkoutStatus: "",
    };
  },
  validations: {
    name: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    address: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    phone: {
      required,
      phone,
    },
    email: {
      required,
      email,
    },
    ccNumber: {
      required,
      creditCard,
    },
  },

  computed: {
    cart() {
      return this.$store.state.cart;
    },
    months() {
      return [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ];
    },
  },

  methods: {
    submitOrder() {
      console.log("Submit order");
      this.$v.$touch(); // Ensures validators always run
      if (this.$v.$invalid) {
        this.checkoutStatus = "ERROR";
      } else {
        this.checkoutStatus = "PENDING";
        setTimeout(() => {
          this.$store
            .dispatch("placeOrder", {
              name: this.name,
              address: this.address,
              phone: this.phone,
              email: this.email,
              ccNumber: this.ccNumber,
              ccExpiryMonth: this.ccExpiryMonth,
              ccExpiryYear: this.ccExpiryYear,
            })
            .then(() => {
              this.checkoutStatus = "OK";
              this.$router.push({ name: "confirmation" });
            })
            .catch((reason) => {
              this.checkoutStatus = "SERVER_ERROR";
              console.log("Error placing order", reason);
            });
        }, 100);
      }
    },

    yearFrom(index) {
      return new Date().getFullYear() + index;
    },
  },
};
</script>

<style scoped>
.empty-page {
  margin-left: auto;
  margin-right: auto;
  display: flex;
  flex-direction: column;
  gap: 1em;
  justify-content: space-around;
  padding: 1em;
  max-width: 23em;
  text-align: center;
}

.checkout-page-body {
  display: flex;
  padding: 1em;
  justify-content: space-around;
  margin-left: auto;
  margin-right: auto;
}

form {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  gap: 2em;
}

.fields {
  display: flex;
  flex-direction: column;
  gap: 0.5em;
}

.input-fields {
  align-items: flex-end;
}

.input-and-error {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.value-field {
  display: flex;
  justify-content: space-between;
}

.total-field {
  display: flex;
  justify-content: space-between;
  font-weight: bolder;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

.error {
  color: red;
  text-align: right;
  font-size: 1rem;
  font-style: italic;
}

.checkoutStatusBox {
  margin-top: 1em;
  padding: 0.1em;
  text-align: center;
  color: red;
  font-style: italic;
}
</style>
