<template>
  <div class="hello">
   <div class="whole" onLoad="redirect();">
   	<img src="../assets/back.jpg" />
       <div class="mask"></div>
   </div>
   <div class="b">
   		<img src="../assets/404.png" class="center"/>
   		<p>
   			出错了，想溜进来没门！<br>
   			可能输入的网址及数据错误或此页面不存在<br>
          <a href="javascript:history.go(-1)" style="color: white;text-decoration: none;">返回</a>
   		</p>
   	</div>
  </div>
</template>

<script>

export default {
  name: 'Error',
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped="scoped">
.center{
  margin:0 auto;
 }
.whole{
  width:100%;
  height:100%;
  line-height:100%;
  position:fixed;
  bottom:0;left:0;
  z-index:-1000;
  overflow:hidden;
  }
.whole img{
  width:100%;
  height:100%;
  position: absolute;
  }
.mask{
  width:100%;height:100%;
  position:absolute;top:0;left:0;
  background:#000;opacity:0.6;
  filter:alpha(opacity=60);
 }
.b{
  width:100%;
  text-align:center;
  height:400px;
  position:absolute;
  top:50%;
  margin-top:-230px
}
  .a{
      width:150px;
      height:50px;
      margin-top:30px
    }
  .a a{
    display:block;
    float:left;
    width:150px;
    height:50px;
    background:#fff;
    text-align:center;
    line-height:50px;
    font-size:18px;
    border-radius:25px;
    color:#333}
 .a a:hover{
    color:#000;
    box-shadow:#fff 0 0 20px;
  }
p{
  color:#fff;
  margin-top:40px;
  font-size:24px;
}
#num{
  margin:0 5px;
  font-weight:bold;
 }
</style>
