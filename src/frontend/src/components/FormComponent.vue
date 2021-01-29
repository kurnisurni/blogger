<template>
  <div>
    <h1>{{ formTitle }}</h1>
    <b-alert v-model="showSavedAlert" variant="success" dismissible>
      Article saved!
    </b-alert>

    <b-alert v-model="showDeletedAlert" variant="danger" dismissible>
      Article deleted!
    </b-alert>

    <b-form @submit.prevent="onSubmit">
      <b-form-group label="Title:">
        <b-form-input
          v-model="article.title"
          placeholder="Enter Title"
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Content:">
        <b-form-textarea
          v-model="article.content"
          placeholder="Enter Content"
        ></b-form-textarea>
      </b-form-group>
      <b-form-group label="Published:" v-if="this.$route.params.id != undefined">
        <b-form-input
          v-model="article.published"
          placeholder="Enter Published Date"
        ></b-form-input>
      </b-form-group>
      <b-button type="submit" variant="primary" class="mr-4">Save</b-button>
      <b-button type="reset" variant="danger" v-if="this.$route.params.id != undefined" @click="removeArticle">Delete</b-button>
    </b-form>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import _ from 'lodash'
export default {
  name: "FormComponent",
  props: ['id'],
  data() {
    return {
      article: {
        id: '',
        title: '',
        content: '',
        published: ''
      },
      showSavedAlert: false,
      showDeletedAlert: false
    }
  },
   methods: {
    ...mapActions(['addArticle', 'updateArticle', 'deleteArticle', 'getCurrentArticle']),
    saveArticle() {
      this.addArticle(this.article)
    },
    changeArticle() {
      this.updateArticle(this.article)
    },
    removeArticle() {
      this.deleteArticle(this.id)
      this.showDeletedAlert = true
    },
    onSubmit() {
      if(this.$route.params.id == undefined) {
        this.saveArticle()
      } else {
        this.changeArticle()
      }
      this.showSavedAlert = true
    },
  },
  created() {
    this.getCurrentArticle(this.id)
  },
  computed: {
    ...mapGetters(['currentArticle']),
    selectedArticle() {
      return this.currentArticle
    },
    formTitle() {
      if(this.id  != undefined) {
        return "Update Article"
      } else {
        return "Create New Article"
      }
    },
  },
  watch: {
    selectedArticle(n) {
      this.article = _.cloneDeep(n)
    }
  }
}
</script>