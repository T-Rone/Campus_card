<template >
  <el-container style="margin-top: -60px; background-color: #d22e2e;" onLoad="getData()">
     <el-container>
        <el-header style="text-align: right; font-size: 12px;width: 100%;">
          <span><img src="../assets/dzlogo.png" alt="" style="transform:scale(0.5,0.5);
          margin-left: -50px;margin-top: -10px;" align="left">
          </span>
          <h1>我的校园卡</h1>
        </el-header>
      <el-aside width="280px"  style="background-color: rgb(255, 255, 255) !important;color: #000000;
      margin-left: 30px;margin-top: 14px;">
        <nav class="nav-width wisCard-nav">
        		<div class="bh-clearfix wisCard-clearfix">
        			<div class="wisCard-information">
        				<div class="img_wrap">
        					<img class="user-img" src="../assets/userphoto.png" alt="" style="vertical-align: middle;width: 160px;height: 180px;">
        					<span>
        					</span>
        				</div>

        				<div class="bh-text-caption-large bh-mv-4 user-name" v>
        					<p>姓名 :{{Account.stuName}} </p>
        				  <p>校园卡号 :{{Account.stuId}}</p>
                  <p>(账号：{{Account.accountId}})</p>
        					<p>学院 :{{Account.college}}</p>
        					<p>学/工号 :{{Account.stuId}}</p>
        					<p>可用金额 :{{Account.balance}}元</p>
        				</div>
        			</div>
        		</div>
        		<div style="margin:0 5px" class="wisCard-option">

        			<!-- <a href="javaScript:void(0)" data-action="在线充值" >在线充值<i
        				class="iconfont icon-keyboardarrowright option-icon"></i></a><br> -->
        		</div>
        	</nav>
      </el-aside>
      </el-container>
       <el-container>
         <el-header class="sz">
           <el-dropdown>
             <span class="el-dropdown-link">
               <i class="el-icon-setting el-icon--right">设置</i>
             </span>
             <el-dropdown-menu slot="dropdown">
               <el-dropdown-item @click.native="pwd_Visible = true">
                修改密码</el-dropdown-item>
               <el-dropdown-item >
                 <a href="self.location=document.referrer;"  class="primary"
                 style="text-decoration: none;color: #606266;">
                   退出</a></el-dropdown-item>
             </el-dropdown-menu>
           </el-dropdown>
           <span style="font-size: 14px;line-height: 68px;">{{Account.stuName}}&nbsp;&nbsp;&nbsp;&nbsp;</span>
           <el-avatar shape="square" :size="28"  >
             <img src="../assets/userphoto.png" alt="头像" width="28px">
           </el-avatar>
        </el-header>
         <el-main style="background-color: #f4f5fa !important;margin-left: -650px;height: 200px;">
          <div class="block">
             <span >时间筛选:&nbsp;&nbsp;</span>
             <el-date-picker
               v-model="value2"
               type="daterange"
               @change="changeDate"
               align="left"
               unlink-panels
               range-separator="至"
               start-placeholder="开始日期"
               end-placeholder="结束日期"
               value-format="yyyy-MM-dd"
               :picker-options="pickerOptions">
             </el-date-picker>
           <br/>{{value2}}<br />
           <span class="demonstration">全部流水: </span>
            <el-link type="primary" @click="getBillByPay()">支出</el-link>
             <el-link type="primary" @click="income = true">充值</el-link>
             <br/>
             <span class="demonstration">消费类型: </span>
              <el-link type="primary" @click="getAllBill()">全部</el-link>
               <el-link type="primary">超市</el-link>
               <el-link type="primary">餐饮</el-link>
               <el-link type="primary">水电费</el-link>
                <br/>
               <span class="demonstration">详情: </span>
               <el-link type="info" >当月支出</el-link>
               <el-link type="disable" >1000.0</el-link>
               <el-link type="info">当月充值</el-link>
               <el-link type="disable" >{{month_charge}}</el-link>

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

           </div>
         </el-main>
            <el-container style="margin-left: -650px;">
              <el-table
                 :header-cell-style="{'text-align':'center'}"
                 :data="tableData"
                 border
                 style="width: 500px;text-align: center !important;">
                 <!-- porp里写上接受axios过来的json对象中字段的名字 直接就可以就受了 -->
                 <el-table-column
                   fixed
                   prop="createDate"
                   :formatter="formatDate"
                   label="创建时间"
                   align="center"
                   width="175">
                 </el-table-column>
                 <el-table-column
                   prop="productName"
                   label="商品"
                   align="center"
                   width="175">
                 </el-table-column>

                 <el-table-column
                   prop="price"
                   label="价格"
                   align="center"
                   width="175">
                 </el-table-column>
                 <el-table-column
                   prop="store"
                   label="商家"
                   align="center"
                   width="175">
                 </el-table-column>
                 <!-- 打算用来判断支出或收入 -->
                 <el-table-column
                   prop="payIncome"
                   label="说明"
                   align="center"
                   :formatter="formatInOut"
                   width="175">
                 </el-table-column>

                 <el-table-column
                   prop="orderId"
                   label="订单号"
                   align="center"
                   >
                 </el-table-column>

                 <!-- <el-table-column
                   prop="store_order_id"
                   label="商家订单号"
                   width="150"
                   >
                 </el-table-column>
 -->
                 <el-table-column
                   fixed="right"
                   label="操作"
                   align="center"
                   width="150"
                   >
                   <template slot-scope="scope">
                     <el-button  @click="dialogVisible = true , getOneBill(scope.$index)" type="primary"
                     icon="el-icon-search" size="small">查看详细信息</el-button>
                   </template>
                      <!-- <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button> -->
                     <!-- <el-button type="info" icon="el-icon-edit" size="small">编辑</el-button> -->
                     <!-- <el-button type="danger" icon="el-icon-delete" size="small">删除</el-button> -->
                 </el-table-column>
              </el-table>
               <el-dialog
                    title="详细信息"
                    :visible.sync="dialogVisible"
                    :before-close="handleClose"
                    :append-to-body="true"
                    :center="true"
                    >
                    <!-- 数据库获取的信息  -->
<el-form :inline="true" :model="formInline" class="demo-form-inline" >
  <el-form-item label="订单号">
    <el-input v-model="formInline.orderId" placeholder="订单号">
        <p>{{formInline.orderId}}</p>
    </el-input>
  </el-form-item>
  <el-form-item label="商家订单号">
    <el-input v-model="formInline.store_order_id" placeholder="商家订单号"></el-input>
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
    <el-input v-model="formInline.createDate" placeholder="创建时间"></el-input>
  </el-form-item>
  <el-form-item label="账单分类">
    <el-input v-model="formInline.classify" placeholder="账单分类"></el-input>
  </el-form-item>
  <el-form-item label="说明">
    <el-input v-model="formInline.payIncome" placeholder="说明"></el-input>
  </el-form-item>
</el-form>
<!-- <tr v-for="(people,i) in tableData">
  	<td>{{i}}</td>
  	<td>{{people.orderId}}</td>
  	<td>{{people.payIncome}}</td>
  	<td>{{people.createDate}}</td>
  </tr> -->
                   <span slot="footer" class="dialog-footer">
                      <el-button @click="dialogVisible = false">取 消</el-button>
                      <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
                    </span>
                 </el-dialog>

 <el-dialog
                    title="开始充值"
                    :visible.sync="income"
                    :before-close="handleClose"
                    :append-to-body="true"
                    :center="true"
                    >
                    <el-form :inline="true" :model="BankCard" class="demo-form-inline">
                      <el-form-item label="卡号">
                        <el-input v-model="BankCard.cardNumber" placeholder="请输入卡号"></el-input>
                      </el-form-item>
                      <el-form-item label="密码">
                         <el-input v-model="BankCard.cardPwd" placeholder="请输入密码"></el-input>
                      </el-form-item>

                      <el-form-item label="充值金额">
                         <el-input v-model="BankCard.recharge" width="100px" placeholder="请输入充值金额" >
                         </el-input>
                      </el-form-item>


                    </el-form>
 <span slot="footer" class="dialog-footer">
      <el-button @click="income = false">取 消</el-button>
      <el-button type="primary" @click="onSubmit() ,income = false" width="100px">提交信息</el-button>
    </span>
 </el-dialog>

 <el-dialog
   title="修改密码"
   :visible.sync="pwd_Visible"
   :before-close="handleClose"
   :append-to-body="true"
   :center="true"
  >
 <el-form :inline="true" :model="formInline" class="demo-form-inline">
   <el-form-item label="新密码">
      <el-input v-model="new_pwd" placeholder="请输入新密码"></el-input>
   </el-form-item>

  </el-form>
<span slot="footer" class="dialog-footer">
      <el-button @click="pwd_Visible = false">取 消</el-button>
      <el-button type="primary" @click="edit_pwd() ,pwd_Visible = false" width="100px">提交信息</el-button>
</span>
 </el-dialog>

               </el-table>
            </el-container>
        <el-footer>
        </el-footer>
       </el-container>
  </el-container>
</template>

<script>
export default {
 data() {
      return {
        flag: true,
        BankCard:{
          balance: this.$route.query.bc.balance,
          bank: this.$route.query.bc.bank,
          cardNumber: this.$route.query.bc.cardNumber,
          cardPwd: this.$route.query.bc.cardPwd,
          category:this.$route.query.bc.category,
          recharge:''
        },
        Account:{
          stuName: this.$route.query.data.stuName,
          stuId: this.$route.query.data.stuId,
          stuPwd:this.$route.query.data.stuPwd,
          college:this.$route.query.data.college,
          accountId:this.$route.query.data.accountId,
          balance:this.$route.query.data.balance
        },
        new_pwd: '',
        pagesize:1,
        currentPage: 1,
        currentPage1: 1,
        currentPage2: 2,
        currentPage3: 3,
        currentPage4: 4,
        total: this.$route.query.bill.length,
        formInline: {
                  orderId:'',
                  storeOrderId:'',
                  productName: '',
                  price:'',
                  store:'',
                  createDate:'',
                  classify:'',
                  payIncome:''
                },
        pwd_Visible: false,
        income: false,
        dialogVisible: false,
        tableData: this.$route.query.bill
        // [{
        //           date: this.$route.query.bill.createDate,
        //           name: this.$route.query.bill.createDate,
        //           province: '上海',
        //           city: '普陀区',
        //           address: '上海市普陀区金沙江路 1518 弄',
        //           zip: 200333
        // }]
        ,
        pickerOptions: {
          shortcuts: [
           {
             text: '今日消费',
             onClick(picker) {
               const end = new Date();
               const start = new Date();
               start.setTime(start.getTime());
               picker.$emit('pick', [start, end]);
             }
           },
           {
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          },{
            text: '最近一年',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 365);
              picker.$emit('pick', [start, end]);
            }
          }
          ]
        },
        value2: '',
        month_charge:'',
      };
    },

    //页面一刷新就调用，可以说与页面同时加载
    mounted() {
      //this.getAllBillByPage()
      // this.handleSizeChange()
    },

    methods: {
      getAllBillByPage(){
          this.axios.get('http://localhost:9000/getAllBillByPage?current='+this.currentPage+'&size='+this.total).then(
          (rs)=>{
            this.tableData=rs.data
          }
          )
      },
      getAllBill(){
         this.axios.get('http://localhost:9000/getAllBill').then(
         (rs)=>{
           this.tableData=rs.data
         }
         )
      },
      getBillByPay(){
        this.axios.get("http://localhost:9000/getBillByPay")
         .then(
         (rs)=>
            {
             this.tableData=rs.data
            }
         )
      },
          changeDate(val) {
             this.value2 = val;
             console.log(this.value2[0]);
             console.log(this.value2[1]);
             this.axios.get("http://localhost:9000/getBillByDate?start="+this.value2[0]+"&end="+this.value2[1])
              .then(
              (rs)=>
                 {
                  this.tableData=rs.data
                 }

              )
           },
      // sendBill(row,column){
      //   console.log(row)
      //   return this.axios.get("http://localhost:9000/getOneBill?id="+row)
      // },
      getOneBill ( index ){
        // console.log(index+1);
         return this.axios.get("http://localhost:9000/getOneBill?id="+ (index+1)).then((rs)=>
            {
              this.formInline.orderId=rs.data.orderId;
              this.formInline.store_order_id=rs.data.storeOrderId;
              this.formInline.productName=rs.data.productName
              this.formInline.price=rs.data.price,
              this.formInline.store=rs.data.store,
              this.formInline.createDate=rs.data.createDate,
              this.formInline.classify=rs.data.classify,
              this.formInline.payIncome=(rs.data.payIncome==1)?'支出':'收入'
            }
         )
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
      edit_pwd() {
        this.axios.post("http://localhost:9000/edit_pwd?new_pwd="+this.new_pwd+"&stuId="+this.Account.stuId).then(response => {
          console.log('修改密码'+this.new_pwd);
          if(response.status==200){
            alert("修改成功!新密码为"+this.new_pwd)
          }else{
            alert("修改失败!")
          }
        })
      },
      onSubmit() {
       this.axios.post("http://localhost:9000/addBalance?recharge="+this.BankCard.recharge).then(response => {
         console.log('充值金额：'+this.BankCard.recharge);
         if(response.status==200){
           this.Account.balance=response.data.balance
           this.month_charge+=this.BankCard.recharge
           alert("充值成功!充值金额："+this.BankCard.recharge+"\n 校园卡余额："+this.Account.balance)
         }else{
           alert("充值失败!")
         }
       })
      },
      //这个函数未用上
     back(){
          this.$router.go(-1);
     },
     handleClose(done) {
             this.$confirm('确认关闭？')
               .then(_ => {
                 done();
               })
               .catch(_ => {});
     },
     open() {
        this.$alert('<p>插入html片段</p>', 'html插入详情', {
                dangerouslyUseHTMLString: true
             });
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
          this.axios.get('http://localhost:9000/getAllBillByPage?current='+(this.currentPage)+'&size='+val).then(
          (rs)=>{
            this.tableData=rs.data
          }
          )
          console.log(`每页 ${val} 条`);
          console.log(`当前页: ${val}`);
        }
        // getData() {
        //   this.axios.get("http://localhost:9000/main").then(response => {

        //   }
        // }

  }
 };
  </script>
<style>
  .el-table{
    width: 786.6px;
    /* margin: 0 auto; */
  }

  .block{
    float: left;
    margin: 20px;
    margin-top: 10px;
    background-color: #f4f5fa !important;
    text-align: left;
    height: 32px;
    line-height: 32px;
  }


.el-link{
  margin: 10px;

}

.demonstration{
}

  .sz{
    margin:0 auto;
    margin-right: 10px;
  }
 .el-header {
    color: #ffffff;
    line-height: 60px;
  }

  .el-aside {
    color: #ffffff;
    height:  calc(100vh);
  }
  h1 {
    font-size: 24px;
    font-weight: 700;
    float: left;
    line-height: 44px;
    margin-left: -110px;
  }
  .el-avatar{
 font-size: 24px;
    font-weight: 700;
    float: right;
    line-height: 44px;
    margin-top: 20px;
  }
.el-dropdown-link {
    cursor: pointer;
    color: #ffffff;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }

.el-main{
  background-color: #ffffff;
  margin-left: -454px;
  margin-top:14px;
     box-sizing: border-box;
 }

.user-name {
    text-align: left;
    width: 240px;
    float: left;
    padding: 16px 1px;
    font-size: 14px;
    line-height: 35px;
    margin-left: 50px;

}

/* .user-name {
   text-align: left;
    float: left;
    padding: 16px 1px;
    font-size: 14px;
    line-height: 35px;
} */
.bh-mt-4, .bh-mv-4 {
    margin-top: 4px !important;
    margin-bottom: 4px !important;
}
.el-footer{
  background-color: #d22e2e;
  margin-left: -900px;
}
</style>
