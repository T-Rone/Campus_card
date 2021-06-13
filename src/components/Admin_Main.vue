<template>
<div id="Admin_Main">
    <h1>欢迎管理员登录系统</h1>
    <router-link to="/manager/au" @click="getAllAccount()">前往用户中心查看</router-link>
</div>
</template>

<script>
  export default {
    name: 'Admin_Main',
    mounted(){
      // this.getAllAccount()
    },
    methods:{
      getAllAccount(){
        this.axios.get('http://localhost:9000/getAllAccount').then(
        (rs)=>{
                  this.$router.push({
                     path:'/manager/au',
                     query:{
                       data: rs.data,
                     }
                   });
        }
        )
      }
      // ,
      // getAllBill(){
      //    this.axios.get('http://localhost:9000/getAllBill').then(
      //    (rs)=>{
      //      this.tableData=rs.data
      //    }
      //    )
      // }
    }
  }
</script>

<style>
.main{
}
</style>
