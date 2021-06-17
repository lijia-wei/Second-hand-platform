<template>
  <div class="tagset">
    <div class="addtag">
      <el-input class ="in" v-model="tagsName" placeholder="标签名称"></el-input>
      <el-input class ="in" v-model="tagsMsg" placeholder="标签描述"></el-input>
      <el-button
        type="warning"
        @click="addtags()">添加</el-button>
    </div>

    <el-table :data="tagList" border style="width: 100%">
      <el-table-column prop="tagsName" label="物品类别标签"> </el-table-column>
      <el-table-column prop="id" label="编号"> </el-table-column>
      <el-table-column
        label="操作"
        v-if="this.$store.state.user.userInfo.username == '罗慧颖'"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "tagset",
  data() {
    return {
      tagList: [],
      tagsName: "",
      tagsMsg: "",
    };
  },
  methods: {
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
    addtags() {
      let param= {
        tagsName: this.tagsName,
        tagsMsg: this.tagsMsg,
      }
      this.$axios({
        url: "http://192.168.1.144:8080/api/tags/insertTag",
        method: "POST",
        data: JSON.stringify(param),
        headers: { Authorization: this.$store.state.user.userInfo.token },
      })
        .then((res) => {
          if (res.data.msg == "成功") {
            this.getGoodTag();
            this.tagsName="";
            this.tagsMsg="";
          }
        })
        .catch((e) => {
          this.$message(e);
        });
    },
    handleDelete(index, row) {
      this.deltag(row.id);
    },
    deltag(id){
      this.$axios({
        url: "http://192.168.1.144:8080/api/tags/deleteTags?id=" + id,
        method: "GET",
        data: id,
        headers: { Authorization: this.$store.state.user.userInfo.token },
      })
        .then((res) => {
          if (res.data.msg == "成功") {
            this.$message.success("删除成功！");
            this.getGoodTag();
          }
        })
        .catch((e) => {
          this.$message(e);
        });
    }
  },
  mounted() {
    this.getGoodTag();
  },
};
</script>

<style>
.in {
  width: 30%;
  margin: 1em;
}
</style>