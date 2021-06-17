<template>
    
  <div class="setting">
    <!--  Start of Header  -->
    <mainav />
    <div class="head">
      <div class="demo-avatar demo-basic el-row">
        <div class="el-col el-col-15">
          <div class="demo-basic--circle">
            <div class="block">
              <span
                class="el-avatar el-avatar--circle"
                :style="[{ backgroundImage: 'url(' + headsrc + ')' }]"  
              >
              <!-- v-loading="loading" -->
                <input
                  type="file"
                  accept="image/gif,image/jpeg,image/jpg,image/png"
                />
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="petname">用户名：{{this.$store.state.user.userInfo.username}}</div>
    </div>
    <!-- Main Content -->
    <div class="main">
      <el-tabs :tab-position="tabPosition">
        <el-tab-pane label="个人信息"> 
          个人信息
          <ownset />
        </el-tab-pane>

        <el-tab-pane label="我的发布">
          <mygoods />
        </el-tab-pane>

        <el-tab-pane label="我的订单">
          <ownorder />
        </el-tab-pane>

        <el-tab-pane label="标签管理" v-if="this.$store.state.user.userInfo.username=='罗慧颖'">
          <tagset />
        </el-tab-pane>
      </el-tabs>
    </div>

    <!-- start of foot -->
    <!-- <foot/> -->
  </div>
</template>

<script>
import mainav from '@/components/content/mainav';
import myfooter from "@/components/content/indexfoot";
import mygoods from "@/components/content/personal/owngoods";
import ownorder from "@/components/content/personal/ownorder";
import ownset from "@/components/content/personal/ownset";
import tagset from "@/components/content/personal/tagSet";

export default {
  name: "setting",
  data() {
    return {
      tabPosition: "left",
      requesturl: "/collection/select",
      headsrc: "https://tvax2.sinaimg.cn/crop.0.0.996.996.180/007CCnbDly8gk1it8d169j30ro0ro3zu.jpg?KID=imgbed,tva&Expires=1623847337&ssig=lRH9suF1YG",
      loading: true,
    };
  },
  components: {
    myfooter,
    mainav,
    ownset,
    mygoods,
    ownorder,
    tagset
  },
  methods: {
    
    // outback() {
    //   //跳转主页
    //   this.$router.replace({
    //     path: "/",
    //   });
    // }
  },
  created() {
    
    if(!this.$store.state.user.islogin){
      // this.outback();
    }
  },
};
</script>

<style>

.head {
  background: url("../../assets/images/cool-background.png") no-repeat;
  background-size: cover;
  width: 80%;
  margin: 2em auto 0.5em;
  font-size: 16px;
  border: solid #e4e7ed 1px;
  height: 200px;
  position: relative;
}
.head input {
  opacity: 0;
  display: block;
}
.head span {
  position: absolute;
  left: 45%;
  top: 30px;
  height: 120px;
  width: 120px;
  line-height: 120px;
  background-size: cover;
}
.head .petname {
  position: absolute;
  left: 45%;
  top: 80%;
  color: rgb(12, 11, 11);
  font-size: 18px;
}
.main {
  width: 80%;
  margin: 0 auto;
  font-size: 16px;
  border: solid #e4e7ed 2px;
}
.el-row::after,
.el-row::after {
  clear: both;
}
.el-row::before {
  display: table;
}
.el-tab-pane{
  font-size: 20px;
  margin: 1em 0;
}

</style>
