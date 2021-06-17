<template>
  <div class="post">
    <!-- Start of Header -->
    <mainav />

    <el-header style="height: 100px;">
      <div class="wrapper">
        <div class="img_wrapper">
          <img src="../assets/images/logo.png" />
        </div>
        <div class="title">
          <div class="big">在线发布商品</div>
          <div class="small">SECOND-HAND TRADING PLATFORM</div>
        </div>
      </div>
    </el-header>
    <!-- 发布-->
    <el-main>
      <div class="wrapper">
        <div class="title">ACCOUNT POST</div>
        <el-form :label-position="labelPosition" label-width="100px" :model="goodInform">
          <el-form-item label="商品名称">
            <el-input v-model="goodInform.goodsName"></el-input>
          </el-form-item>
          <el-form-item label="商品新旧度">
            <el-input v-model="goodInform.goodsLevel"></el-input>
          </el-form-item>
          <el-form-item label="保质期">
            <el-input v-model="goodInform.goodsDue"></el-input>
          </el-form-item>
          <el-form-item label="商品描述">
            <el-input v-model="goodInform.goodsIntro"></el-input>
          </el-form-item>
          <el-form-item label="商品数量">
            <el-input v-model="goodInform.goodsNum"></el-input>
          </el-form-item>
          <el-form-item label="商品价格">
            <el-input v-model="goodInform.goodsPrice"></el-input>
          </el-form-item>
          <el-form-item label="商品标签编号">
            <el-input v-model="goodInform.tagsId"></el-input>
          </el-form-item>
        </el-form>
        <div class="btn">
          <el-dropdown>
            <span class="el-dropdown-link">
              查看标签编号<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-for="(item,index) in tagList" :key="index">{{item.id}}：{{item.tagsName}}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-button type="danger" @click="post">发布</el-button>
        </div>
       
      </div>
    </el-main>
    <myfooter></myfooter>
    
  </div>
</template>

<script>
import myfooter from "../components/content/foot.vue"
import mainav from "../components/content/mainav";
import tagset from "@/components/content/personal/tagSet";


export default {
  name: "postGood",
  data() {
    return {
        labelPosition: 'right',
        goodInform: {
          goodsDue: "",
          goodsIntro: "",
          goodsLevel: "",
          goodsName: "",
          goodsNum: "",
          goodsPrice: "",
          tagsId: ""
        },
        tagList: [],
    }
  },
  // 辅助函数也一样，获取模块中的state值
  components: {
    myfooter,
    mainav,
    tagset
  },
  methods: {
    //发布
    post() {
      let obj = {
        goodsDue: this.goodInform.goodsDue,
        goodsIntro: this.goodInform.goodsIntro,
        goodsLevel: this.goodInform.goodsLevel,
        goodsName: this.goodInform.goodsName,
        goodsNum: this.goodInform.goodsNum,
        goodsPrice: this.goodInform.goodsPrice,
        tagsId: this.goodInform.tagsId,
      };
      this.$axios({
        url: "http://192.168.1.144:8080/api/goods/insertGoods",
        method: "POST",
        data: JSON.stringify(obj),
        headers: { Authorization: this.$store.state.user.userInfo.token },
      })
        .then((res) => {
          let data = res.data.data;
          if (res.data.msg == "成功") {
            this.$message.success("发布成功！");
          } 
        })
        .catch((e) => {
          this.$message.error(e);
        });
    },
    getGoodTag() {
      this.$axios({
        url: "http://192.168.1.144:8080/api/tags/selectAll",
        method: "GET",
        headers: { Authorization: this.$store.state.user.userInfo.token },
      })
        .then((res) => {
          if (res.data.msg == "成功") {
            this.tagList = res.data.data;
          }
        })
        .catch((e) => {
          this.$message(e);
        });
    },
  },
  mounted() {
    this.getGoodTag(); 
  },
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