<template>
  <div class="index">
    <!-- Start of Header -->
    <mainav />

    <!-- Start of Search Wrapper -->
    <div class="search-area-wrapper">
      <div class="search-area container">
        <h3 class="search-header">Sedond-Hand Platform</h3>
        <router-link to="/"><a class="logo-img"></a></router-link>
        <p class="search-tag-line" style="margin-top: 50px">
          校园二手交易信息平台，让旧物重获新生！
        </p>

        <form class="search-form clearfix" @submit.prevent="onSubmit">
          <input
            class="search-term required"
            type="text"
            v-model="search"
            placeholder="请输入商品名称"
          />
          <input
            class="search-btn"
            type="submit"
             @click="getGoodData(1)"
            value="搜索"
          />
          <div id="search-error-container"></div>
        </form>
      </div>
    </div>

    <div class="excel">
      <el-tabs :tab-position="tabPosition" style="height: 200px;">
        <el-tab-pane label="全部商品">
          <el-table
            :data="tableData"
            border
            style="width: 100%">
            <el-table-column
              prop="goodsName"
              label="商品名称"
              width="150" >
            </el-table-column>
            <el-table-column
              prop="userId"
              label="发布者"
              width="60">
            </el-table-column>
            <el-table-column
              prop="goodsPrice"
              label="价格"
              width="80">
            </el-table-column>
            <el-table-column
              prop="goodsLevel"
              label="几成新">
            </el-table-column>
            <el-table-column
              prop="goodsDue"
              label="到期日期">
            </el-table-column>
            <el-table-column
              prop="goodsIntro"
              label="商品描述">
            </el-table-column>
            <el-table-column
              prop="goodsRead"
              label="浏览量">
            </el-table-column>
            <el-table-column
              prop="goodsStatus"
              label="商品状态">
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="书籍"></el-tab-pane>
        <el-tab-pane label="衣服"></el-tab-pane>
        <el-tab-pane label="自行车"></el-tab-pane>
      </el-tabs>
    </div>
    
    <!-- start of foot -->
    <myfooter />
  </div>
</template>

<script>
import mainav from '../components/content/mainav';
import myfooter from "@/components/content/indexfoot";
export default {
  name: "Home",
  data() {
      return {
        search: "",
        qName: "",
        tabPosition: "left",
        tableData: [],
      }
    },
  components: {
    mainav,
    myfooter
  },
  methods: {
    searchbtn() {
      if (this.search == "") {
        this.$message.error("关键字不能为空");
        return;
      } else {
        this.qName = this.search;
      }
    },
    getGoodData(tag){
      console.log("ok");
      this.$axios({
          url: "http://192.168.1.144:8080/api/goods/getAllGoodsByTag?tagId="+tag,
          method: "POST",
          data: tag,
        })
          .then((res) => {
            if (res.data.msg == "成功") {
              console.log(res.data.data);
              this.tableData = res.data.data;
            }
          })
          .catch((e) => {
            this.$message(e);
          });
       
    }
  },
  // mounted(){
  //   this.getGoodData(1);
  // }
}
</script>

<style>
.index {
  margin: 0 auto;
  background-color: white;
  height: 100%;
}
.excel {
  width: 100%;
}
.el-tabs--left, .el-tabs--right {
  width: 90%;
  margin: 2em auto;
  border: solid 2px rgb(231, 213, 213);
  padding: 1em;
}
</style>