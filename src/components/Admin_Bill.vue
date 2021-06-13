<template>
  <div id="Admin_Bill">
  <el-breadcrumb separator="/">
    <el-breadcrumb-item :to="{ path: '/manager/am' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>账单中心</el-breadcrumb-item>
  </el-breadcrumb>
  <h1 align="center">账单中心</h1>
  <el-table
                  :header-cell-style="{'text-align':'center'}"
                  :data="tableData"
                  border
                  style="width: 1200px;text-align: center !important;">
                  <el-table-column
                  prop="id"
                  label="序号"
                  align="center"
                  width="50"
                  >

                  </el-table-column>
                  <!-- porp里写上接受axios过来的json对象中字段的名字 直接就可以就受了 -->
                  <el-table-column
                    prop="createDate"
                    :formatter="formatDate"
                    label="创建时间"
                    align="center"
                    width="120">
                  </el-table-column>
                  <el-table-column
                    prop="productName"
                    label="商品"
                    align="center"
                    width="120">
                  </el-table-column>

                  <el-table-column
                    prop="price"
                    label="价格"
                    align="center"
                    width="100">
                  </el-table-column>
                  <el-table-column
                    prop="store"
                    label="商家"
                    align="center"
                    width="120">
                  </el-table-column>
                  <!-- 打算用来判断支出或收入 -->
                  <el-table-column
                    prop="payIncome"
                    label="说明"
                    align="center"
                    :formatter="formatInOut"
                    width="75">
                  </el-table-column>


                  <el-table-column
                    prop="orderId"
                    label="订单号"
                    align="center"
                    width="100"
                    >
                  </el-table-column>

                  <el-table-column
                    prop="storeOrderId"
                    label="商家订单号"
                    width="100"
                    >
                  </el-table-column>

                  <el-table-column
                    prop="classify"
                    label="账单分类"
                    width="100"
                  >
                  </el-table-column>

                  <el-table-column
                    label="操作"
                    align="center"
                    >

                    <template slot-scope="scope">
                     <el-button type="info" icon="el-icon-edit" size="small" @click="edit_Visible = true , getAllBillById(scope.row)">编辑</el-button>
                     <el-button type="danger" icon="el-icon-delete" @click="open(scope.row)" size="small">删除</el-button>
                     <el-button type="primary" icon="el-icon-circle-plus" @click="edit_Visible = true" size="small">添加</el-button>
                    </template>

                  </el-table-column>
                </el-table>

                <el-pagination
                      @click="getAllBillByPage()"
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
                   title="账单信息操作"
                   :visible.sync="edit_Visible"
                   :before-close="handleClose"
                   :append-to-body="true"
                   :center="true"
                  >
                 <el-form :inline="true" :model="formInline" class="demo-form-inline">
                   <el-form-item label="">
                      <el-input v-model="formInline.id" placeholder="序号" type="hidden"></el-input>
                   </el-form-item>

                   <el-form-item label="订单号">
                     <el-input v-model="formInline.orderId" placeholder="订单号">
                     </el-input>
                   </el-form-item>
                   <el-form-item label="商家订单号">
                     <el-input v-model="formInline.storeOrderId" placeholder="商家订单号"></el-input>
                   </el-form-item>
                   <el-form-item label="商品名称">
                     <el-input v-model="formInline.productName" placeholder="商品"></el-input>
                   </el-form-item>
                   <el-form-item label="商品价格">
                     <el-input v-model="formInline.price" placeholder="价格"></el-input>
                   </el-form-item>
                   <el-form-item label="商家名">
                     <el-input v-model="formInline.store" placeholder="商家"></el-input>
                   </el-form-item>

                   <el-form-item label="创建时间">
                     <el-input v-model="formInline.createDate" placeholder="创建时间" type="date"></el-input>
                   </el-form-item>
                   <el-form-item label="账单分类">
                     <el-input v-model="formInline.classify" placeholder="账单分类"></el-input>
                   </el-form-item>
                   <el-form-item label="说明">
                     <el-input v-model="formInline.payIncome" placeholder="说明"></el-input>
                   </el-form-item>
                   <el-form-item label="选择">
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
    name: 'Admin_Bill',
    data() {
      return{
       radio: '',
       edit_Visible: false,
       pagesize:2,
       currentPage: 1,
       tableData:[],
       total: this.$route.query.data.length,
       formInline: {
              id: 1,
              orderId:'',
              storeOrderId:'',
              productName: '',
              price:'',
              store:'',
              createDate:'',
              classify:'',
              payIncome:''
            },
     }
    },
    mounted() {
      this.getAllBillByPage()
      // this.handleSizeChange()
      // this.getAllAccount()
    },
    methods:{
      edit(){
        console.log(this.formInline.id)
        if(this.radio==1){
          //修改
          this.axios({
            method:'post',
            url:'http://localhost:9000/updateBillById?id='+this.formInline.id ,
            params:[
              {
                // id:this.formInline.id,
                orderId:this.formInline.orderId,
                storeOrderId: this.formInline.storeOrderId,
                productName: this.formInline.productName,
                price: this.formInline.price,
                store: this.formInline.store,
                createDate: this.formInline.createDate,
                classify: this.formInline.classify,
                payIncome: this.formInline.payIncome
              }
            ],
            data:this.formInline
          }).then(
        (rs)=>{
          alert("修改成功！")
           // console.log(row.id)
           this.tableData=rs.data
        })

        }else if(this.radio==2){
          //添加
              this.axios({
              method:'post',
              url:'http://localhost:9000/addBill',
              params:[
                {
                  id:0,
                  orderId:this.formInline.orderId,
                  storeOrderId: this.formInline.storeOrderId,
                  productName: this.formInline.productName,
                  price: this.formInline.price,
                  store: this.formInline.store,
                  createDate: this.formInline.createDate,
                  classify: this.formInline.classify,
                  payIncome: this.formInline.payIncome
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
          alert("请先选择操作！")
        }
      },

      getAllBillById(row){
        this.axios.get('http://localhost:9000/getOneBill?id='+row.id).then(
        (rs)=>{
           console.log(row.id)
           id:0,
           this.formInline.id=rs.data.id
           this.formInline.orderId=rs.data.orderId
           this.formInline.storeOrderId=rs.data.storeOrderId
           this.formInline.productName=rs.data.productName
           this.formInline.price=rs.data.price,
           this.formInline.store=rs.data.store,
           this.formInline.createDate=rs.data.createDate,
           this.formInline.classify=rs.data.classify,
           this.formInline.payIncome=(rs.data.payIncome==1)?'支出':'收入'
        }
        )
      },
     getAllBillByPage(){
         this.axios.get('http://localhost:9000/getAllBillByPage?current='+this.currentPage+'&size='+this.total).then(
         (rs)=>{
           this.tableData=rs.data
         }
         )
     },
      handleSizeChange(val) {
        //这里有问题
        this.axios.get('http://localhost:9000/getAllBillByPage?current='+this.currentPage+'&size='+val).then(
        (rs)=>{
           this.tableData=rs.data
        }
        )
        console.log(`每页 ${val} 条`);
        console.log(`当前页: ${val}`);
           },
       handleCurrentChange(val) {
        this.axios.get('http://localhost:9000/getAllBillByPage?current='+(this.currentPage+1)+'&size='+val).then(
        (rs)=>{
          this.tableData=rs.data
        }
        )
        console.log(`每页 ${val} 条`);
        console.log(`当前页: ${val}`);
      },
      open(row) {
              this.$confirm('此操作将永久删除该账单, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                console.log(row);
                // console.log(this.tableData.id.);
                this.axios.delete('http://localhost:9000/deleteBillById?id='+row.id)
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

    formatInOut(row,column){
        return row[column.property]==1?'支出':'收入'
      },
      formatDate(row, column) {
             // 获取单元格数据
             let data = row[column.property]
             if (data =='') {
                 return ''
             }else {
                 let dt = new Date(data)
                 return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() ;
             }
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
