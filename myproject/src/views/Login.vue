<template>
  <div class="login">
   <div class="head">
      <div class="wrapper">
        <div class="img_wrapper">
          <img src="./logo.png"/>
        </div>
        <div class="title">
          <div class="big">二手交易平台</div>
          <div class="small">SECOND-HAND TRADING PLATFORM</div>
        </div>
      </div>
    </div>
    <div class="content">
      <div class="wrapper">
        <div class="title">ACCOUNT LOGIN</div>
        <div class="input"><span>账号</span><input type="text"/></div>
        <div class="input"><span>密码</span><input type="password"/></div>
        <div class="btn">
          <span class="one" @click.stop.prevent="is_mailbox_check_show = true"><a href="#">我要注册</a></span>|
          <span class="one second"><a href="#">忘记密码</a></span>
          <span class="two" @click.stop.prevent="fun_log_in"><a href="#">登录</a></span>
        </div>
        <div class="logo">
          <span>账号</span>
          <span>登录</span>
        </div>
      </div>
    </div>
    <transition name="toggle">
        <div v-if="is_tip_show" class="tip_wrapper" ><tip v-on:toggle="fun_tip"></tip></div>    
        </transition>
        <transition name="toggle">
        <div v-if="is_prompt_show" class="prompt_wrapper" ><prompt v-on:toggle_prompt="fun_prompt"></prompt></div>    
    </transition>
    <transition name="mailbox_check">
      <div v-if="is_mailbox_check_show" class="mailbox_check">
        <div class="wrapper_1">
          <div class="title_1">邮箱验证</div>
          <div class="mail_content">
            <div class="wrapper2">
              <span class="name">邮箱</span>
              <input type="text" />
              <span class="message"><a href="#">获取验证码</a></span>
            </div>
            <div class="wrapper2">
              <span class="name">验证码</span>
              <input type="text" />
            </div>
            <div class="btn">
              <span @click.stop.prevent="is_register_code_find_show = true"><a href="#">确定</a></span>
              <span @click.stop.prevent="is_mailbox_check_show = false"><a href="#">取消</a></span>
            </div>
          </div>
        </div>
      </div>
    </transition>
    <transition name="register_code_find">
      <div v-if="is_register_code_find_show" class="register_code_find">
        <div class="wrapper">
          <div class="title">用户注册</div>
          <div class="register_content">
            <div class="wrapper_2"><span>邮箱</span><input type="text"></div>
            <div class="wrapper_2"><span>设置密码</span><input type="text"></div>
            <div class="wrapper_2"><span>确认密码</span><input type="text"></div>
            <div class="btn">
              <span><a href="#">确定</a></span>
              <span @click.stop.prevent="is_register_code_find_show = false"><a href="#">取消</a></span>
            </div>
          </div>
        </div>
      </div>
    </transition>
    
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  name: "Login",
  data() {
    return {
      password: "",
      newpassword: "",
      username: "",
      e_mail: "",
      time: 60,
      timecount: "发送验证码",
      verification: "",
      show: false,
    };
  },
  // 辅助函数也一样，获取模块中的state值
  computed: {
    ...mapState({
      isclose: (state) => state.user.isclose,
      islogin: (state) => state.user.islogin,
    }),
  },

  methods: {
    ...mapMutations("user", ["setUserInfo", "ISLOG", "CLOSE"]),

    login() {
      if (this.password == "" || this.password == "") {
        this.$message.error("账号或者密码为空");
        return;
      }
      let obj = {
        password: this.password,
        username: this.username,
      };
      this.$axios({
        url: "/user/login",
        method: "POST",
        data: JSON.stringify(obj),
      })
        .then((res) => {
          if (res.data.state == 200) {
            this.$message.success("登录成功！");
            //获取当前登录信息
            this.$axios({
              url: "/user/getCurrentUser",
              method: "GET",
            })
              .then((res) => {
                let data = res.data.data.data;
                if (res.data.state == 200) {
                  this.setUserInfo(data);
                  this.ISLOG();
                  this.CLOSE();
                }
              })
              .catch((e) => {
                this.$message(e);
              });
            //跳转主页
            this.$router.replace({
              path: "/",
            });
          }
          if (res.data.state == 403) {
            this.$message.error("该用户不存在，请前往注册！");
          } else if (res.data.state == 402) {
            this.$message.error("请求异常，登陆失败！");
          } else if (res.data.state == 400) {
            this.$message.error("登陆失败，密码或账号错误！");
          }
        })
        .catch((e) => {
          this.$message(e);
        });
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
      if (this.e_mail != "") {
        this.$axios({
          url: "/email/sendEmail/findPasswordCode",
          method: "POST",
          data: this.e_mail,
        })
          .then((res) => {
            let data = res.data;
            if (res.data.state == 200) {
              this.$message.success("发送成功，请稍等！");
              this.countDown();
            }
          })
          .catch((e) => {
            this.$message.error(e);
          });
      }else{
        this.$message.error("请输入邮箱！");
      }
    },
    showfindback() {
      this.show = true;
    },
    //找回密码
    findback() {
      const userReg = /^[1-9a-zA-Z]{1}[0-9a-zA-Z]{5,9}$/; //6-10位字母数字
      const pwdReg = /^[a-zA-Z]\w{5,17}$/; //6-18位字母数字下划线 字母开头
      if (!userReg.test(this.username)) {
        this.$message.error("账号为6-10位字母数字字母");
        return;
      }
      if (!pwdReg.test(this.password)) {
        this.$message.error("密码为6-18位字母数字或下划线 字母开头");
        return;
      }
      let obj = {
        newPassword: this.newpassword,
        username: this.username,
      };
      this.$axios({
        url: "/user/findPassword/" + this.verification,
        method: "POST",
        data: JSON.stringify(obj),
      })
        .then((res) => {
          let data = res.data.data;
          alert(data);
          if (res.data.state == 200) {
            this.$message.success("找回成功,请登录！");
          } else {
            this.$message.error("找回失败！");
          }
        })
        .catch((e) => {
          this.$message.error(e);
        });
    },
  },
};
</script>

<style>
.el-header{
  position: absolute;
  display: block;
}
.el-main {
  background-image: url(../assets/images/loginback.jpg);
  background-size: cover;
  height: 600px;
}
</style>