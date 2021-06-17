<template>
  <div class="owngoods">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="goodsName" label="商品名称" width="120">
        </el-table-column>
        <el-table-column prop="goodsNum" label="商品数量" width="90">
        </el-table-column>
        <el-table-column prop="goodsPrice" label="价格" width="60">
        </el-table-column>
        <el-table-column prop="goodsLevel" label="几成新" width="70"> </el-table-column>
        <el-table-column prop="goodsDue" label="到期日期"  width="100"> </el-table-column>
        <el-table-column prop="goodsIntro" label="商品描述" width="140"> </el-table-column>
        <el-table-column prop="goodsRead" label="浏览量" width="70"> </el-table-column>
        <el-table-column prop="goodsStatus" label="商品状态" width="50"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  </div>
</template>

<script>
export default {
  name: "owngoods",
  data() {
    return {
      tabPosition: "left",
      tableData: [],
    };
  },
  methods: {
    getOwnGood() {
      this.$axios({
        url: "http://192.168.1.144:8080/api/user/getPulishMsg",
        method: "GET",
        headers: { Authorization: this.$store.state.user.userInfo.token },
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
      this.delgoods(row.id);
    },
    delgoods(id){
      console.log(id);
      this.$axios({
        url: "http://192.168.1.144:8080/api/goods/deleteGoods?goodsId=" + id,
        method: "POST",
        data: JSON.stringify(id),
        headers: { Authorization: this.$store.state.user.userInfo.token },
      })
        .then((res) => {
          if (res.data.msg == "成功") {
            this.$message.success("删除成功！");
            this.getOwnGood();
          }
        })
        .catch((e) => {
          this.$message(e);
        });
    }
  },
  mounted() {
    this.getOwnGood();
  },
};
</script>

<style>
</style>