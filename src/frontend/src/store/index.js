import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const url = 'http://localhost:8081/api/v1/articles/'

export default new Vuex.Store({
  state: {
    articles: [],
    ascending_articles: [],
    descending_articles: [],
    article: {}
  },
  getters: {
    allArticles: state => state.articles,
    currentArticle: state => state.article,
    ascendingArticles: state => state.ascending_articles,
    descendingArticles: state => state.descending_articles,
  },
  mutations: {
    GET_ALL_ARTICLES: (state, articles) => state.articles = articles,
    GET_ASC_ARTICLES: (state, articles) => state.ascending_articles = articles,
    GET_DESC_ARTICLES: (state, articles) => state.descending_articles = articles,
    GET_CURRENT_ARTICLE: (state, article) => state.article = article,
    ADD_ARTICLE: (state, article) => state.articles.push(article),
    UPDATE_ARTICLE: (state, updatedArticle) => {
      const index = state.articles.findIndex( article => article.id == updatedArticle.id )
      Vue.set(...state.articles, index, updatedArticle)
    },
    DELETE_ARTICLE: (state, deletedArticle) => {
      const index = state.articles.findIndex( article => article.id == deletedArticle.id )
      state.articles.splice(index, 1)
    }
  },
  actions: {
    async getAllArticles({ commit }) {
      const response = await axios.get(url)
      commit('GET_ALL_ARTICLES', response.data)
    },
    async getCurrentArticle({ commit }, articleId) {
      const response = await axios.get(`${url}${articleId}`)
      commit('GET_CURRENT_ARTICLE', response.data)
    },
    async getAscendingArticles({ commit }) {
      const response = await axios.get(`${url}publishedAsc`)
      commit('GET_ASC_ARTICLES', response.data)
    },
    async getDescendingArticles({ commit }) {
      const response = await axios.get(`${url}publishedDesc`)
      commit('GET_DESC_ARTICLES', response.data)
    },
    async addArticle({ commit }, articleData) {
      const response = await axios.post(url, {
        title: articleData['title'],
        content: articleData['content']
      })

      commit('ADD_ARTICLE', response.data)
    },
    async updateArticle({ commit }, articleData) {
      const response = await axios.patch(`${url}${articleData['id']}`, {
        title: articleData['title'],
        content: articleData['content']
      })
      commit('UPDATE_ARTICLE', response.data)
    },
    async deleteArticle({ commit }, articleId) {
      const response = await axios.delete(`${url}${articleId}`)

      commit('DELETE_ARTICLE', response.data)
    },
   }
})
