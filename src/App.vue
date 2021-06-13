<template>
  <div id="app">
   <router-view />

   
  </div>
</template>

<script>
import Qs from 'qs'
export default {
  name: 'App'
  // comments:{

  // }
}
</script>

<style  >
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background: url(assets/bg.gif);
  background-size:cover;
  height: 100%;
}

*{
  padding: 0;
  margin: 0;
}
html,body{
  width: 100%;
  height: 100%;
}

</style>
