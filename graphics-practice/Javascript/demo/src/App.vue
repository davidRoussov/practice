<template>
  <div id="app">
    <!-- <img src="./assets/logo.png">
    <hello></hello> -->
    <demo-list></demo-list>
    <div id='demo'>
      <lesson0></lesson0>
    </div>
  </div>
</template>

<script>
import DemoList from './components/DemoList'
import Lesson0 from './components/Lesson0'

export default {
  name: 'app',
  components: {
    DemoList,
    Lesson0
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#demo {
  margin-left: 250px;
}
</style>
