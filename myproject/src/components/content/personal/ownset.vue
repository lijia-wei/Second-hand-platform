<template>
  <div class="ownset">
    <li>id: {{this.$store.state.user.userAllInfo.id}}</li>
    <li>用户名：{{this.$store.state.user.userInfo.username}}</li>
    <li>手机号：{{this.$store.state.user.userAllInfo.userPhone}}</li>
    <li>学 号：{{this.$store.state.user.userAllInfo.userNum}}</li>
    <li>信用度：{{this.$store.state.user.userAllInfo.userCredit}}</li>
    <div class="change">
      <a @click="show2">修改密码</a>
      <input
          type="text"
          v-model="new2"
          maxlength="10"
          minlength="6"
          placeholder="旧密码"
          v-if="showpass"
        />
        <input
          type="text"
          v-model="new3"
          maxlength="10"
          minlength="6"
          placeholder="新密码：6-10位数字、字母组合"
          v-if="showpass"
        />
        <input
          type="text"
          v-model="phone"
          maxlength="10"
          minlength="6"
          placeholder="手机号"
          v-if="showpass"
        />
        <button class="changeput" v-if="showpass" @click="changePassWord">提交</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ownset",
  data() {
    return {
      showpet: false,
      showpass: false,
      phone: "",
      new2: "",
      new3: "",
    }
  },
  methods: {
    changePassWord(){
      let params = {
        newPassword: this.new2,
        oldPassword: this.new3,
        username: this.phone
      }
      this.$axios({
        url: "http://192.168.1.144:8080/api/user/updatePassword",
        method: "PUT",
        data: JSON.stringify(params)
      }).then((res) => {
          let data = res.data.data.data;  
          if(res.data.state == 200){
            this.$store.state.user.userInfo.password = this.new3;
            sessionStorage.getItem("userInfo");
            location. reload();
            this.$message.success("修改成功！");
          }else{
             this.$message.error("修改失败！");
          }
        })
    },
    show1() {
      this.showpet = !this.showpet;
    },
    show2() {
      this.showpass = !this.showpass;
    }
  }
}
</script>

<style>
  .el-tag {
    height: 105px;
    margin: 1em;
    border-color: #cae3fc;
    border-width: 1px;
    font-size:14px;
  }
  .ownset{
    margin: 1em 0 2em 3em;
    font-size: 16px;
  }
  .ownset li{
    display: block;
  }
  .change{
    margin-bottom: 1em;
  }
  .change li {
    display: inline;
  }
  .change a {
    font-size: 12px;
    margin-left: 3em;
  }
  .change input{
    margin-left: 2em;
  }
  .change a:hover {
    color: rgb(99, 6, 250);
  }
  .change button {
    font-size: 14px;
    margin-left: 1em;
    margin-bottom: 1em;
  }
</style>