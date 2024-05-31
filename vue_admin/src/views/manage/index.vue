<template>
  <div class="card-container">
    <div v-for="manage in manages" :key="manage.id" class="card">
      <h2 class="card-title">{{ manage.name }}</h2>
      <div class="card-content">
        <!-- <p>社团名称: {{ manage.name }}</p> -->
        <p>社长: {{ manage.leader }}</p>
        <p>详细介绍: {{ manage.description }}</p>
        <p class="card-text">图片: {{ manage.image }}</p>
        <p class="card-text">附件: {{ manage.attachment }}</p>
        <div class="button-container">
          <!-- <el-button type="primary" @click="redirectToGroupDetail" class="edit-button">编辑信息</el-button> -->
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import { mapGetters } from 'vuex'
import { getManages } from "@/api/manage.js";
  
export default {
  name: 'Manage',
  computed: {
  ...mapGetters([
    'name'
  ]),
},
  data() {
    return {
      manages: [],
    };
  },
  created: function () {
    getManages(this.name).then((response) => {
      this.manages = response.data.items;
    })
  },
  methods: {
    redirectToGroupDetail(groupName) {
      this.$router.push({
      name: 'Groupdetail',
      params: {
          groupName: groupName
        }
      });
      console.log(this.$route.params)
    }
  }
}
</script>
  
<style>
.card-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.card {
  border: 10px solid #333333;
  padding: 180px;
  margin: 40px;
  width: 60%;
  height: 250%;
  text-align: left;
}

.card-title {
  margin-top: -70px;
  text-align: center;
  font-size: 40px;
  margin-bottom: 50px;
}

.card-content {
  text-align: left;
  font-size: 25px;
}

.card-text {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  max-width: 500px; /* 根据需要调整最大宽度 */
}

.button-container {
  text-align: right;
  margin-top: 50px;
}

.edit-button {
  margin-left: 10px;
}
</style>