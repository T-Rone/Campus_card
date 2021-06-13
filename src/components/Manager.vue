<template>
 <el-container style="margin-top: -60px; height: 100%;background-color: #ffffff;">
     <el-header style="width: 100%; ">
     <el-menu
       :default-active="activeIndex2"
       class="el-menu-demo"
       mode="horizontal"
       @select="handleSelect"
       background-color="#959494"
       text-color="#fff"
       active-text-color="#ffd04b">

       <el-submenu index="1">
       </el-submenu>
       <el-menu-item style="margin-left: 1250px;"></el-menu-item>
<!--       <el-submenu index="2">
         <template slot="title">我的工作台</template>
         <el-menu-item index="2-1">选项1</el-menu-item>
         <el-menu-item index="2-2">选项2</el-menu-item>
         <el-menu-item index="2-3">选项3</el-menu-item>
         <el-submenu index="2-4">
           <template slot="title">选项4</template>
           <el-menu-item index="2-4-1">选项1</el-menu-item>
           <el-menu-item index="2-4-2">选项2</el-menu-item>
           <el-menu-item index="2-4-3">选项3</el-menu-item>
         </el-submenu>{{adminName}}
       </el-submenu>
 -->       <el-submenu index="3" >
           <template slot="title" ><i class="el-icon-user"></i>管理员 </template>
           <el-menu-item index="3-1"><a href="self.location=document.referrer;">退出</a></el-menu-item>
           <!-- <el-menu-item index="3-2">选项2</el-menu-item> -->
       </el-submenu>

     </el-menu>

   </el-header>
   <el-container style="background-color=#959494;">
     <el-aside style="background-color=#959494;">
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#959494"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>用户管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1" icon="el-icon-search" >
             <router-link to="/manager/am" style="text-decoration: none;color: #FFFFFF;">
              用户查看
            </router-link>
            </el-menu-item>
        </el-menu-item-group>
      </el-submenu>



      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span>账单管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1" icon="el-icon-search">
          <span @click="getAllBill()" style="text-decoration: none;color: #FFFFFF;">
            账单查看
         </span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-menu-item style="margin-top: 200px;">

      </el-menu-item>

    </el-menu>
     </el-aside>

     <el-main>
       <router-view/>
     </el-main>

   </el-container>
 </el-container>
</template>

<script>
  export default{
 data() {
      return {
        // adminName: this.$route.query.data.username,
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    mounted(){
      this.getAllBill()
    },
  methods: {
      // handleSelect(key, keyPath) {
      //   console.log(key, keyPath);
      // },
      // handleOpen(key, keyPath) {
      //   console.log(key, keyPath);
      // },
      // handleClose(key, keyPath) {
      //   console.log(key, keyPath);
      // },
      getAllBill(){
         this.axios.get('http://localhost:9000/getAllBill').then(
         (rs)=>{
           this.$router.push({
              path:'/manager/ab',
              query:{
                data: rs.data,
              }
            });
         }
         )
      },
    }
  }
</script>

<style scoped="scoped">

  .element.style{
    width: 200px;
  }
.el-menu-vertical-demo{
  width: 200px;
  position: absolute;
}
 .el-menu-item-group{
    width: 200px;
    padding-left: 0px;
  }
.el-container{
  height: 100%;
}

.el-header{
  left: 0 ;
  right: 0;
  padding: 0;
}
.el-slider{
  width: 200px;
  height: 100% !important;
}
.el-aside .el-menu{
  /* border-right: none; */
  height: 100%;
}
.el-header .el-menu{
  /* border-bottom: none; */
 height: 100%;
}
.el-menu-demo{
  width: 100%;
  float: right;
}
</style>
