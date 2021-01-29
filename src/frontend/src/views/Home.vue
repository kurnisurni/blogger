<template>
  <div>
    <h1>List of Articles</h1>
    <ul v-for="article in allArticles" :key="article.id">
      <li>
        <router-link 
          :title="article.title"
          :to="{ 
            name: 'editArticle', 
            params: { id : article.id }
          }"
        >
          {{article.title}}
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
export default {
  name: 'Home',
  methods: {
    ...mapActions(['getAllArticles'])
  },
  created() {
    this.getAllArticles()
  },
  computed: {
    ...mapGetters(['allArticles'])
  },
}
</script>