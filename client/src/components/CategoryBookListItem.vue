<template>
  <li class="book-box">
    <div class="book-button-box">
      <div class="book-image">
        <img
          :src="require('@/assets/images/books/' + bookImageFileName(book))"
          :alt="book.title"
        />
      </div>
      <div class="buy-button-box">
        <button
          class="button-secondary"
          v-on:click="$store.dispatch('addToCart', book)"
        >
          Add to Cart
        </button>
        <button v-if="book.isPublic" class="button-secondary-inverse">
          Read Now
        </button>
      </div>
    </div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
  </li>
</template>

<script>
export default {
  name: "categoryBookListItem",
  props: {
    book: {
      type: Object,
      required: true,
    },
  },
  methods: {
    bookImageFileName: function (book) {
      let name = book.title.toLowerCase();
      name = name.replace(/ /g, "-");
      name = name.replace(/[.,/#!$%^&*;:{}=?_`~()]/g, "");
      return `${name}.gif`;
    },
  },
};
</script>
<style scoped>
.book-box {
  display: flex;
  flex-direction: column;
  background-color: var(--clr-bg-tertiary);
  border-radius: 6px;
  padding: 0.5em;
  gap: 0.25em;
  width: 13em;
}

.book-button-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.buy-button-box {
  display: flex;
  flex-direction: column;
  padding-left: 0.5em;
  gap: 0.5em;
}

.book-image img {
  border: 2px solid white;
  border-radius: 4px;
  height: 190px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.book-title {
  font-weight: bold;
}

.book-author {
  font-style: italic;
}

.book-price {
  font-weight: bold;
  text-align: right;
  margin-top: auto;
}
</style>
