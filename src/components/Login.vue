<template>
  <div class="bg">
   <el-container style="margin-top: -60px;">
      <el-header style="text-align: left; font-size: 12px;height: 102px;">
      <div class="logo"><img src="../assets/dzlogo.png" alt="">
        &nbsp;&nbsp;&nbsp;<span style="color: white;">---</span>&nbsp;&nbsp;&nbsp;
       <span style="color: white;font-size: 20px;">统一身份认证</span>
       </div>
      </el-header >
     <el-main>
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
            <h3 class="login-title">欢迎登录</h3>
            <el-form-item label="账号" prop="username" style="" >
              <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
            </el-form-item>
            <span style="height: 35px;line-height: 35px;text-align: center;">
              <el-radio v-model="radio" label="1" name="radio">学生用户</el-radio>
              <el-radio v-model="radio" label="2" name="radio">管理员用户</el-radio>
            </span>
            <el-form-item style="margin: 0 auto;">
              <el-button type="primary" v-on:click="onSubmit('loginForm')">登录</el-button>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <el-button type="" style="text-decoration: none !important;">
                 <router-link to="/register">忘记密码</router-link>
               </el-button>
            </el-form-item>
          </el-form>

          <el-dialog
            title="温馨提示"
            :visible.sync="dialogVisible"
            width="30%"
            >
            <span>请输入账号和密码</span>
            <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
          </el-dialog>

     </el-main>
     <el-footer></el-footer>
     <!-- <img src="../assets/bg.gif" alt="" width="100%" height="100%" > -->
  </el-container>
  </div>
</template>

<script>

import Qs from 'qs'
 export default {
    name: "Login",
    data() {
      return {
        radio: '',
        form: {
          username: '',
          password: ''
        },

        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          username: [
            {required: true, message: '账号不可为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不可为空', trigger: 'blur'}
          ]
        },

        // 对话框显示和隐藏
        dialogVisible: false
      }
    },

    methods: {
      // function
       getUserAccount:function() {
         return this.axios.get('http://localhost:9000/login?username=' + this.form.username + '&password=' + this.form.password+'&radio='+this.radio);
      },
      // function
       getBill:function(){
         return this.axios.get('http://localhost:9000/getAllBill')
      },
      // function
       getBankCard :function(){
         return this.axios.get('http://localhost:9000/getOneBankCard')
      },
      onSubmit(formName) {
        var stuId =this.form.username;
        var stuPwd=this.form.password;
        var radio=this.radio;
        //注意！一定要重新定义一下this指向
        var _this = this;
        // 为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
             //
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            // this.$router.push("/main");
            this.axios.all(
          [ this.getUserAccount(), this.getBankCard(),this.getBill()]
            // {
            //   methods: 'get',
            //   url: 'http://localhost:9000/login?username=' + this.form.username + '&password=' + this.form.password+'&radio='+this.radio,
            //   transformRequest: [function (data) {
            //     return Qs.stringify(data)
            //   }]
            // }
            ).then(
            this.axios.spread( (acc_response,bc_response,bill_response) =>
            // (response) =>
            {
              console.log(acc_response);
              console.log(bc_response);
              console.log(bill_response);
              console.log(stuId);
              if(acc_response.status==200&&(
                      acc_response.data.name==stuId&&acc_response.data.password==stuPwd)
                       ||(acc_response.data.stuId==stuId&&acc_response.data.stuPwd==stuPwd)){
                if(radio==1){
                   this.$router.push({
                     path:'/main',
                     query:{
                       data: acc_response.data,
                       bc: bc_response.data,
                       bill: bill_response.data
                     }
                   });
                 }else{
                   this.$router.push({
                     path:'/manager',
                     query:{
                       data: acc_response.data
                     }
                   });
                 }
              }
              else{
                  this.$router.push("/error");
                  console.log('error')
              }

            }));
          }
           else {
            this.dialogVisible = true;
            return false;
          }
        });
      }
    }
  }
</script>

<style>
  /* .bg{ */
     /* } */
.logo{
  margin-left: 100px;
  margin-top: 50px;
}
.login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 150px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
    background-color: white ;
    opacity: 0.9;
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }
</style>
