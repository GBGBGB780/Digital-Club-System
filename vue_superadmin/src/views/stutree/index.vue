<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />

    <el-dialog :visible.sync="dialogVisible" title="学生详情" width="30%">
        <div style="font-weight: bold; font-size: 20px;">学号：{{ data1.userId }}</div>
        <div style="font-weight: bold; font-size: 20px;">姓名：{{ data1.userName }}</div>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
        </span>
      </el-dialog>

    <el-tree
      ref="tree2"
      :data="data2"
      :render-content="renderNode"
      :filter-node-method="filterNode"
      @node-click="handleNodeClick"
      class="filter-tree"
      default-expand-all
    />
  </div>
</template>

<script>
import  arrayToTree from 'array-to-tree'
import { getAllStudent } from "@/api/user.js"
import { getList } from "@/api/table.js";

export default {

  data() {
    return {
      filterText: '',
      studentlist: [],
      grouplist: [],
      majorlist: [],
      searchInfo: '',
      index: 1,
      data1:[],
      data2: [],
      dialogVisible: false,
      
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },

  mounted() {
    
  },

  created: function () {
      getAllStudent(this.searchInfo).then((response) => {
        this.studentlist = response.data.items;
      })
      .catch(error => {
        console.error(error);
      });

      getList(this.searchInfo).then((response) => {
        this.grouplist = response.data.items;
        this.createtree();
      })
      .catch(error => {
        console.error(error);
      });
      

      


    },

  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1;
    },
    createtree() {
      this.index = 1;
      this.grouplist.forEach((item, index) => {
        item.index = this.grouplist[index].id;
        this.index = this.grouplist[index].id;
      });
      console.log(this.grouplist)
      this.studentlist.forEach(item => {
        item.index = this.index++;
      });
      this.data2 = arrayToTree([...this.grouplist,...this.studentlist], {
      parentProperty: 'groupId',
      customID: 'index'
      });
      const node = {
        index:0,
        name:'社团',
        children: this.data2
      }
      this.data2 = [node];

    },

    handleNodeClick(data){
      if('userName' in data){
        this.data1 = data
        this.dialogVisible = true
      }
      
    },


    renderNode(h, { node, data }) {
      if ('userName' in data) {
        return (
          <span>
            <span>{data.userId}{data.userName}</span> 
          </span>
        );
      } else {
        return (
          <span>
            <span>{data.name}</span> 
          </span>
        );
      }
    }
  }
}
</script>

