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
          <input class="search-btn" type="submit" value="搜索" />
          <div id="search-error-container"></div>
        </form>
      </div>
    </div>
    <!-- @click="getGoodData(1)" -->
    <div class="excel">
      <el-tabs
        :tab-position="tabPosition"
        @tab-click="handleClick"
      >
        <el-tab-pane
          :label="item.tagsName"
          v-for="(item, index) in tagList"
          :key="index"
        >
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="goodsName" label="商品名称" width="150">
            </el-table-column>
            <el-table-column prop="goodsNum" label="商品数量" width="80">
            </el-table-column>
            <el-table-column prop="goodsPrice" label="价格" width="60">
            </el-table-column>
            <el-table-column prop="goodsLevel" label="几成新" width="80">
            </el-table-column>
            <el-table-column prop="goodsDue" label="到期日期" width="150">
            </el-table-column>
            <el-table-column prop="goodsIntro" label="商品描述" width="250">
            </el-table-column>
            <el-table-column prop="goodsRead" label="浏览量" width="80">
            </el-table-column>
            <el-table-column prop="goodsStatus" label="商品状态" width="80">
            </el-table-column>
            <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">下单</el-button>
            </template>
          </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>

    <!-- start of foot -->
    <myfooter />
  </div>
</template>

<script>
import mainav from "../components/content/mainav";
import myfooter from "@/components/content/indexfoot";

export default {
  name: "Home",
  data() {
    return {
      search: "",
      qName: "",
      tabPosition: "left",
      tableData: [],
      tagList: [],
    };
  },
  components: {
    mainav,
    myfooter,
  },
  methods: {
    // searchbtn() {
    //   if (this.search == "") {
    //     this.$message.error("关键字不能为空");
    //     return;
    //   } else {
    //     this.qName = this.search;
    //   }
    // },
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
    handleClick(tab, event) {
      // console.log(tab, event);
      let str = event.target.getAttribute("id");
      let tag = parseInt(str.substring(4, 5));
      this.getGoodData(tag + 1);
    },
    getGoodData(tag) {
      this.$axios({
        url:
          "http://192.168.1.144:8080/api/goods/getAllGoodsByTag?tagId=" + tag,
        method: "POST",
        data: tag,
      })
        .then((res) => {
          if (res.data.msg == "成功") {
            this.tableData = res.data.data;
          }
        })
        .catch((e) => {
          this.$message(e);
        });
    },
    handleDelete(index, row) {
      this.buy(row.id);
    },
    buy(id) {
      console.log(id);
      let param={
          goodId: id
      }
      this.$axios({
        url: "http://192.168.1.144:8080/api/order/createOrder",
        method: "POST",
        data: JSON.stringify(param),
        headers: { Authorization: this.$store.state.user.userInfo.token },
      })
        .then((res) => {
          if (res.data.msg == "成功") {
            this.$message.success("下单成功！");
          }
        })
        .catch((e) => {
          this.$message(e);
        });
    },
  },
  mounted() {
    this.getGoodTag();
    this.getGoodData(1);
  },
};
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
.el-tabs--left,
.el-tabs--right {
  width: 90%;
  margin: 2em auto;
  border: solid 2px rgb(231, 213, 213);
  padding: 1em;
}
</style>