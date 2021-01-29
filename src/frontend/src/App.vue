<template>
  <div id="app">
    <Navbar />
    <b-container class="pt-5">
      <router-view />
    </b-container>
  </div>
</template>

<script>
import Navbar from './components/Navbar'

export default {
  name: 'App',
  components: {
    Navbar
  },
}
</script>