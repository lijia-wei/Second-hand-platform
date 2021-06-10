<template>
  <div class="login">
    <el-header>
      <div class="wrapper">
        <div class="img_wrapper">
          <img src="../assets/images/logo.png" />
        </div>
        <div class="title">
          <div class="big">二手交易平台</div>
          <div class="small">SECOND-HAND TRADING PLATFORM</div>
        </div>
      </div>
    </el-header>
    <!-- 登录 -->
    <el-main>
      <div class="wrapper">
        <div class="title">ACCOUNT LOGIN</div>
        <el-form :label-position="labelPosition" label-width="80px" :model="formLabelLogin">
          <el-form-item label="手机号">
            <el-input v-model="formLabelLogin.phonenumber"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="formLabelLogin.password"></el-input>
          </el-form-item>
        </el-form>

        <div class="btn">
          <span class="one">我要注册</span>|
          <span class="second">忘记密码</span>
          <el-button type="danger" @click="login">登录</el-button>
        </div>
       
      </div>
    </el-main>

    <!-- 注册 -->
    <el-main>
      <div class="wrapper">
        <div class="title">ACCOUNT REGISTER</div>
        <el-form :label-position="labelPosition" label-width="80px" :model="formLabelRegister">
          <el-form-item label="用户名">
            <el-input v-model="formLabelRegister.userName"></el-input>
          </el-form-item>
          <el-form-item label="学号">
            <el-input v-model="formLabelRegister.userNum"></el-input>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="formLabelRegister.userPhone"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="formLabelRegister.userPwd"></el-input>
          </el-form-item>
          <el-form-item label="验证码">
            <el-input v-model="formLabelRegister.verifyCode"></el-input>
          </el-form-item>
        </el-form>

        <div class="btn">
          <span class="second">直接登录</span>
          <el-button type="danger" @click="login">取消</el-button>
          <el-button type="danger" @click="login">注册</el-button>
        </div>
       
      </div>
    </el-main>

    <myfooter></myfooter>
    
  </div>
</template>

<script>
import myfooter from "../components/content/footer.vue"
export default {
  name: "Login",
  data() {
    return {
        labelPosition: 'right',
        formLabelLogin: {
          phonenumber: '15182918600',
          password: '123456',
        },
        formLabelRegister: {
          userName: '',
          userNum: '',
          userPhone: '',
          userPwd: '',
          verifyCode: '',
        }
    }
  },
  components: {
    myfooter
  },
  methods: {
    login(){
      var param = {
        userPhone: this.formLabelAlign.phonenumber,
        userPwd: this.formLabelAlign.password
      }
      this.$axios({
          url: "/anon/login",
          method: "POST",
          data: JSON.stringify(param),
        })
          .then((res) => {
            let data = res.data;
            if (res.data.state == 200) {
              
            }
          })
          .catch((e) => {
            
          });
       }//else{
      //   this.$message.error("请输入邮箱！");
      // }
    }
  
  
};
</script>

<style>
body {
  /* overflow:hidden; */
  background-color: beige;
}
.login {
  display: flex;
  width: 1000px;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  flex-direction: column;
  height: 100%;
  margin: 0 auto;
}
.el-header{
  box-flex: 0;
  -ms-flex: 0 0 200px;
  flex: 0 0 200px;
  background: #fff;
}
.el-header .wrapper {
  width: 50%;
  margin: 0 auto;
  margin-top: 0px;
  margin-top: 40px;
  text-align: center;
}
.wrapper .img_wrapper{
  display: inline-block;
}
.wrapper .img_wrapper img{
  width: 80px;
  height: 80px;
}
.wrapper .title {
  color: black;
  display: inline-block;
vertical-align: top;
}
.wrapper .title .big {
  line-height: 60px;
  font-size: 28px;
  letter-spacing: 5px;
}
.wrapper .title .small {
  line-height: 15px;
  font-size: 10px;
  letter-spacing: 1px;
}

.el-main {
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;
  background: #fc965a;
}
.el-main .wrapper{
  position: relative;
  width: 40%;
  margin: 0 auto;
  margin-top: 0px;
  margin-top: 6px;
  background: rgba(245, 250, 250, 0.23);
  padding: 10px 5px;
  border-radius: 3px;
  text-align: center;
  color: #fff;
}
.el-main .wrapper .title {
  font-size: 22px;
  letter-spacing: 1px;
  margin: 35px 0;
  color: white;
}
.el-main .wrapper .el-form-item {
  width: 80%;
  margin-left: 1em;
  
}
.el-main .wrapper .el-form-item .el-form-item__label{
  color: white;
}
.el-main .wrapper .el-form-item .el-input__inner {
  height: 30px;
  line-height: 30px;
}
.el-main .wrapper .btn {
  text-align: right;
  margin-right: 60px;
  margin-bottom: 25px;
}
.el-main .wrapper .second {
  margin-right: 2em;
}
.el-main .wrapper .one, .el-main .wrapper .second{
  font-size: 14px;
  color: white;
}
.el-main .wrapper .btn {
  margin-left: 2em;
}
</style>