<template>
  <div id='list' class="list-group">
    <a href="#" class="list-group-item active">
      Cras justo odio
    </a>
    <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
    <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
    <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
    <a href="#" class="list-group-item list-group-item-action disabled">Vestibulum at eros</a>
  </div>
</template>

<script>
export default {
  name: 'DemoList'
}
</script>

<style scoped>
  #list {
    float: left;
    height: 100%;
    width: 250px;
  }
</style>