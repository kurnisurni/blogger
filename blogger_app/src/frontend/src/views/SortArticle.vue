<template>
  <div>
    <h1>Sort Article</h1>
    <div class="mt-4">
      <b-button variant="outline-success" class="mr-3" @click="sortAscending">Ascending</b-button>
      <b-button variant="outline-danger" @click="sortDescending">Descending</b-button>
    </div>
    <b-table class="mt-4" striped hover :items="articles"></b-table>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'
export default {
  name: 'SortArticle',
  data() {
    return {
      articles: []
    }
  },
  methods: {
    ...mapActions(['getAscendingArticles','getDescendingArticles']),
    sortAscending() {
      this.articles = this.ascendingArticles
    },
    sortDescending() {
      this.articles = this.descendingArticles
    }
  },
  created() {
    this.getAscendingArticles()
    this.getDescendingArticles()
    this.sortAscending()
  },
  computed: {
    ...mapGetters(['ascendingArticles','descendingArticles'])
  },
}
</script>