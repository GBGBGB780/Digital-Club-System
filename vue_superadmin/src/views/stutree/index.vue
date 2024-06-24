<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />
    <el-input @keyup.enter.native="createtree" v-model="searchInfo" placeholder="检测" clearable style="width: 10%;"/>
         <el-button icon="el-icon-search" @click="createtree" />
    <el-input @keyup.enter.native="createtree2" v-model="searchInfo" placeholder="检测" clearable style="width: 10%;"/>
         <el-button icon="el-icon-search" @click="createtree2" />

    <el-tree
      ref="tree2"
      :data="data2"
      :props="treeprops"
      :filter-node-method="filterNode"
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
      data2: [],
      data1:[], 
      treeprops: 
        { 
          label: 'name', 
          children: 'children'
        },
      array1: [
        { index: 1, name: 'Node 1', parentId: null },
        { index: 2, name: 'Node 2', parentId: null },
        { index: 3, name: 'Node 3', parentId: 1 },
        { index: 4, name: 'Node 4', parentId: 1 },
        { index: 5, name: 'Node 5', parentId: 2 }
      ],
      array2: [
        { index: 6, name: 'Node 6', parentId: null },
        { index: 7, name: 'Node 7', parentId: 6 },
        { index: 8, name: 'Node 8', parentId: 6 }
      ],
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
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
    this.grouplist.forEach(item => {
      item.index = this.index++;
    });
    this.studentlist.forEach(item => {
      item.index = this.index++;
    });
    console.log(this.grouplist);
    console.log(this.studentlist);
    this.data2 = arrayToTree([...this.grouplist,...this.studentlist], {
    parentProperty: 'groupId',
    customID: 'index'
    });
    console.log(this.data2);
    },
    createtree2() {
      const combinedArray = [...this.array1,...this.array2];
      this.data2 = arrayToTree(combinedArray, {
        parentProperty: 'parentId',
        customID: 'index'
      });
      console.log(this.array1);
      console.log(this.array1);
      console.log(this.data2);
    }
  }
}
</script>

