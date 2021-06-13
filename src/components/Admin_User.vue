<template>
<div id="Admin_User">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/manager/am' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户中心</el-breadcrumb-item>
    </el-breadcrumb>
    <h1 align="center">用户中心</h1>
    <el-table
                    :header-cell-style="{'text-align':'center'}"
                    :data="tableData"
                    border
                    style="width: 1200px;text-align: center !important;">
                    <!-- porp里写上接受axios过来的json对象中字段的名字 直接就可以就受了 -->
                    <el-table-column
                    prop="id"
                    label="序号"
                    align="center"
                    width="50"
                    >

                    </el-table-column>
                    <el-table-column
                      prop="accountId"
                      label="账号"
                      align="center"
                      width="120">
                    </el-table-column>

                    <el-table-column
                      prop="stuName"
                      label="姓名"
                      align="center"
                      width="120">
                    </el-table-column>
                    <el-table-column
                      prop="stuId"
                      label="学号"
                      align="center"
                      width="150">
                    </el-table-column>
                    <el-table-column
                      prop="stuPwd"
                      label="密码"
                      align="center"
                      width="150">
                    </el-table-column>

                    <el-table-column
                      prop="balance"
                      label="校园卡余额"
                      align="center"
                      width="160"
                      >
                    </el-table-column>


                    <el-table-column
                      prop="college"
                      label="学院"
                      align="center"
                      width="150"
                      >
                    </el-table-column>

                    <el-table-column
                      fixed="right"
                      label="操作"
                      align="center"
                      >
                        <template slot-scope="scope">
                         <!-- <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button> -->
                        <el-button type="info" icon="el-icon-edit" size="small" @click="edit_Visible = true , getAllAccountById(scope.row)">编辑</el-button>
                        <el-button type="danger" icon="el-icon-delete" @click="open(scope.row)" size="small">删除</el-button>
                        <el-button type="primary" icon="el-icon-circle-plus" @click="edit_Visible = true" size="small">添加</el-button>
                        </template>
                    </el-table-column>
  </el-table>

                <el-pagination
                      @click="getAllAccountByPage()"
                      @size-change="handleSizeChange"
                      @current-change="handleCurrentChange"
                      :current-page="currentPage"
                      :page-sizes="[1, 2, 3, 4]"
                      :page-size="2"
                      layout="total, sizes, prev,  pager, next, jumper"
                      :total="total"
                      >
                </el-pagination>



 <el-dialog
   title="用户信息操作"
   :visible.sync="edit_Visible"
   :before-close="handleClose"
   :append-to-body="true"
   :center="true"
  >
 <el-form :inline="true" :model="formInline" class="demo-form-inline">
   <el-form-item label="">
      <el-input v-model="formInline.id" placeholder="请输入账号" type="hidden"></el-input>
   </el-form-item>

   <el-form-item label="账号">
      <el-input v-model="formInline.accountId" placeholder="请输入账号"></el-input>
   </el-form-item>

   <el-form-item label="姓名">
      <el-input v-model="formInline.stuName" placeholder="请输入姓名"></el-input>
   </el-form-item>


   <el-form-item label="学号">
      <el-input v-model="formInline.stuId" placeholder="请输入学号"></el-input>
   </el-form-item>


   <el-form-item label="密码">
      <el-input v-model="formInline.stuPwd" placeholder="请输入密码"></el-input>
   </el-form-item>


   <el-form-item label="余额">
      <el-input v-model="formInline.balance" placeholder="请输入校园卡余额"></el-input>
   </el-form-item>

   <el-form-item label="学院">
      <el-input v-model="formInline.college" placeholder="请输入学院"></el-input>
   </el-form-item>
   <el-form-item label="选择操作">
       <span style="margin-left: 150px;"></span>
       <el-radio v-model="radio" label="1" name="radio">修改</el-radio>
       <el-radio v-model="radio" label="2" name="radio">添加</el-radio>
     <br/>
   </el-form-item>
  </el-form>
<span slot="footer" class="dialog-footer">
      <el-button @click="edit_Visible = false">取 消</el-button>
      <el-button type="primary" @click="edit()  ,edit_Visible = false" width="100px">提交信息</el-button>
</span>
 </el-dialog>

</div>
</template>

<script>
  export default {
    name: 'Admin_User',
    data() {
      return{
       radio: '',
       edit_Visible: false,
       pagesize:2,
       currentPage: 1,
       tableData:[],
       total: this.$route.query.data.length,
       formInline: {
                id:1,
                stuName: '',
                stuId: '',
                stuPwd: '',
                college:'',
                accountId: '',
                balance:'',
          }
     }
    },
    mounted() {
      // this.getAllAccountByPage()
      // this.handleSizeChange()
      this.getAllAccount()
    },
    methods:{
      getAllAccount(){
        this.axios.get('http://localhost:9000/getAllAccount').then(
        (rs)=>{
                this.tableData=rs.data
        }
        )
      },
      edit(){
        console.log(this.radio)
        console.log(this.formInline.id)
        if(this.radio==1){
          //修改
          this.axios({
            method:'post',
            url:'http://localhost:9000/updateAccountById?id='+this.formInline.id,
            params:[
              {
                stuName: this.formInline.stuName,
                stuId: this.formInline.stuId,
                stuPwd: this.formInline.stuPwd,
                college: this.formInline.college,
                accountId: this.formInline.accountId,
                balance: this.formInline.balance,
              }
            ],
            data:this.formInline
          }).then(
        (rs)=>{
          if(rs.status==200)
          {
          alert("修改成功！")  
          // console.log(row.id)
          this.tableData=rs.data
         }else{
           alert("修改失败")
         }
          
        })

        }else if(this.radio==2){
          //添加
              this.axios({
              method:'post',
              url:'http://localhost:9000/addAccount',
              params:[
                {
                  id:0,
                  stuName: this.formInline.stuName,
                  stuId: this.formInline.stuId,
                  stuPwd: this.formInline.stuPwd,
                  college: this.formInline.college,
                  accountId: this.formInline.accountId,
                  balance: this.formInline.balance,
                }
              ],
              data:this.formInline
            }).then(
          (rs)=>{
              alert("添加成功！")
             // console.log(row.id)
             this.tableData=rs.data
             this.total=this.total+1
          })

        }else{
          alert("请先选择操作类别！")
        }
      },
      getAllAccountById(row){
        this.axios.get('http://localhost:9000/getAllAccountById?id='+row.id).then(
        (rs)=>{
           console.log(row.id)
           this.formInline=rs.data
        }
        )
      },
      getAllAccountByPage(){
          this.axios.get('http://localhost:9000/getAllAccountByPage?current='+this.currentPage+'&size='+this.total).then(
          (rs)=>{
            this.tableData=rs.data
          }
          )
      },
      handleSizeChange(val) {
        //这里有问题
        this.axios.get('http://localhost:9000/getAllAccountByPage?current='+this.currentPage+'&size='+val).then(
        (rs)=>{
           this.tableData=rs.data
        }
        )
        console.log(`每页 ${val} 条`);
        console.log(`当前页: ${val}`);
           },
       handleCurrentChange(val) {
        this.axios.get('http://localhost:9000/getAllAccountByPage?current='+(this.currentPage+1)+'&size='+val).then(
        (rs)=>{
          this.tableData=rs.data
        }
        )
        console.log(`每页 ${val} 条`);
        console.log(`当前页: ${val}`);
      },
      open(row) {
              this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                console.log(row);
                // console.log(this.tableData.id.);
                this.axios.delete('http://localhost:9000/deleteAccountById?id='+row.id)
                .then(
                (rs)=>{
                  this.tableData=rs.data,
                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                  })

                  }
                )

              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });
              });
          },


    handleClose(done) {
            this.$confirm('确认关闭？')
              .then(_ => {
                done();
              })
              .catch(_ => {});
    }
    }
  }
</script>

<style>
</style>
