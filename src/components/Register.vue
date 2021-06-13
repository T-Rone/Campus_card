<template>
  <el-container style="margin-top: -60px;">
      <el-header style="text-align: left; font-size: 12px;height: 102px;">
      <div class="logo"><img src="../assets/dzlogo.png" alt="">
        &nbsp;&nbsp;&nbsp;<span style="color: white;">---</span>&nbsp;&nbsp;&nbsp;
       <span style="color: white;font-size: 20px;">统一身份认证</span></div>
      </el-header >
     <el-main>
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
            <h3 class="login-title">修改密码</h3>
            <el-form-item label="账号" prop="username" style="" >
              <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
            </el-form-item>

            <el-form-item label="确认密码" prop="re_password">
              <el-input type="password" placeholder="请再次输入密码" v-model="form.new_pwd"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" v-on:click="onSubmit('loginForm')">提交</el-button>
              &nbsp;&nbsp;&nbsp;
              <el-button ><router-link to="/">返回</router-link></el-button>
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
  </el-container>
</template>

<script>

  export default {
     name: "Login",
     data() {
       return {
         form: {
           username: '',
           password: '',
           new_pwd: ''
         },

         // 表单验证，需要在 el-form-item 元素中增加 prop 属性
         rules: {
           username: [
             {required: true, message: '账号不可为空', trigger: 'blur'}
           ],
           password: [
             {required: true, message: '密码不可为空', trigger: 'blur'}
           ],
           re_password: [
             {
                required: true,
                message: '请再次输入密码',
                trigger: 'blur',
             }
           ],
         },
         // 对话框显示和隐藏
         dialogVisible: false
       }
     },
     methods :{
        onSubmit(formName) {
          if(this.form.password==this.form.new_pwd){
          this.axios.post("http://localhost:9000/edit_pwd?new_pwd="+this.form.new_pwd+"&stuId="+this.form.username).then(response => {
          console.log('修改密码'+this.form.new_pwd);
          if(response.status==200){
            alert("修改成功!新密码为"+this.form.new_pwd)
            this.$router.push({
                path:'/'
            })
          }else{
            alert("修改失败!")
          }
        }
      )}else{
         alert("两次密码输入不一致")
        }
        }
     }
  }
</script>

<style>
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
