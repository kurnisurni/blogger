<template>
  <div>
  <b-navbar toggleable="lg" type="dark" variant="info">
    <b-navbar-brand :to="{ name: 'home'}">Blogger App</b-navbar-brand>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item :to="{ name: 'newArticle'}">New Article</b-nav-item>
        <b-nav-item :to="{ name: 'sortArticle'}">Find By Published</b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>

<script>
export default {

}
</script>