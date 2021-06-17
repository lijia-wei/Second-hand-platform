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
    <el-main v-if="!isregist">
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
          <span class="second" @click="wantregi">我要注册</span>
          <el-button type="danger" @click="login">登录</el-button>
        </div>
       
      </div>
    </el-main>

    <!-- 注册 -->
    <el-main v-if="isregist">
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
            <el-input v-model="formLabelRegister.verifyCode" style="width:60%; margin-left:-13px"></el-input>
            <span class="sendver" @click="sendVerify">{{ timecount }}</span>
          </el-form-item>
        </el-form>

        <div class="btn">
          <span class="second" @click="directlog">有账号直接登录</span>
          <el-button type="danger" @click="registered">注册</el-button>
        </div>
       
      </div>
    </el-main>

    <myfooter></myfooter>
    
  </div>
</template>

<script>
import myfooter from "../components/content/foot.vue"
import { mapState, mapMutations } from "vuex";
export default {
  name: "Login",
  data() {
    return {
        labelPosition: 'right',
        formLabelLogin: {
          phonenumber: '',
          password: '',
        },
        formLabelRegister: {
          userName: '',
          userNum: '',
          userPhone: '',
          userPwd: '',
          verifyCode: '',
        },
        verification: "",
        time: 60,
        timecount: "发送验证码",
        isregist: false,
    }
  },
  // 辅助函数也一样，获取模块中的state值
  computed: {
    ...mapState({
      isclose: (state) => state.user.isclose,
      islogin: (state) => state.user.islogin,
    }),
  },
  components: {
    myfooter
  },
  methods: {
     ...mapMutations("user", ["setUserInfo", "setUserAllInfo", "ISLOG", "CLOSE"]),
    //登录
    login(){
      var param = {
        userPhone: this.formLabelLogin.phonenumber,
        userPwd: this.formLabelLogin.password
      }
      this.$axios({
          url: "http://192.168.1.144:8080/api/anon/login",
          method: "POST",
          data: JSON.stringify(param),
        })
          .then((res) => {
            if (res.data.msg == "成功") {
              this.$message.success("登录成功！");
              this.setUserInfo(res.data.data);
              this.ISLOG();
              this.CLOSE();
              //获取用户具体信息
              this.$axios({
                url: "http://192.168.1.144:8080/api/user/getOwnerMsg",
                method: "GET",
                headers: {"Authorization": this.$store.state.user.userInfo.token},
              }).then((res) => {
                if (res.data.msg == "成功") { 
                  this.setUserAllInfo(res.data.data);
                }
              }).catch((e) => {
                  this.$message(e);
              });

              //跳转主页
              this.$router.replace({
                path: "/",
              });
            }
          })
          .catch((e) => {
            this.$message(e);
          });
      },
      wantregi() {
        this.isregist = true;
      },
      directlog(){
        this.isregist = false;
      },

    //验证码倒计时
    countDown() {
      var time = 60;
      const datathis = this;
      function ti() {
        var t = setInterval(function () {
          time--;
          datathis.timecount = time + "秒后重新发送";
          if (time == 0) {
            clearTimeout(t);
            datathis.timecount = "发送验证码";
            time = 60;
          }
        }, 1000);
      }
      ti(datathis);
    },
    sendVerify() {
      //发送验证码
      if (this.formLabelRegister.userPhone != "") {
        this.$axios({
          url: "http://192.168.1.144:8080/api/anon/getCode?phoneNumber=" + this.formLabelRegister.userPhone,
          method: "POST",
          data: JSON.stringify(this.formLabelRegister.userPhone),
        })
          .then((res) => {
            if (res.data.msg == "成功") {
              this.$message.success("发送成功，请稍等！")
            } else {
              this.$message.error("发送异常！");
            }
          })
          .catch((e) => {
            this.$message.error(e);
          });
      }
      else {
        this.$message.error("请输入手机号！");
      }
    },

    //注册
    registered() {
      let obj = {
        userName: this.formLabelRegister.userName,
        userNum: this.formLabelRegister.userNum,
        userPhone: this.formLabelRegister.userPhone,
        userPwd: this.formLabelRegister.userPwd,
        verifyCode: this.formLabelRegister.verifyCode
      };
      this.$axios({
        url: "http://192.168.1.144:8080/api/anon/insertUser",
        method: "POST",
        data: JSON.stringify(obj),
      })
        .then((res) => {
          let data = res.data.data;
          if (res.data.msg == "成功") {
            this.$message.success("注册成功请登录！");
            this.$router.push({ path: "/login" });
          } else if (res.data.state == 400) {
            this.$message.error("用户名已存在或验证码错误！");
          }
        })
        .catch((e) => {
          this.$message.error(e);
        });
    },
  }
};
</script>

<style>
body {
  /* overflow:hidden; */
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
.sendver {
  background-color: azure;
  color: black;
  border: none;
  font-size: 13px;
  padding: 3px;
  margin-left: 10px;
}
</style>