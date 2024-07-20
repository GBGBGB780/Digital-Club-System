<template>
  <div style="margin-left: 1%;">
    <el-row>
      <el-input v-model="searchInfo" placeholder="搜索活动" clearable @keyup.enter.native="handleSearch" />
      <el-button icon="el-icon-search" @click="handleSearch" />
      <el-button @click="clearSearch">清空搜索</el-button>
    </el-row>
    <el-table :data="searcchRes">
      <el-table-column prop=name label="活动名" :span="6">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column prop=id label="活动编号" :span="3">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column prop=organizer label="组织者" :span="6">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.organizer }}</span>
        </template>
      </el-table-column>
      <el-table-column prop=time label="申请时间" :span="6" sortable>
        <template slot-scope="scope">
          <span class="text">{{ formatDate(scope.row.time) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="isAccepted" label="申请状态" :span="3">
        <template slot-scope="scope">
          <span class="text success-text">已通过&emsp;</span>
          <el-button  @click.native.prevent="handleEdit(scope.$index)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="修改活动" :visible.sync="dialogVisible" width="48%" :before-close="handleClose">
      <el-form :model="ruleForm" label-width="80px">
        <el-form-item label="名称" required>
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="组织者" prop="organizer" required>
          <el-input v-model="ruleForm.organizer"></el-input>
        </el-form-item>
        <el-form-item label="时间" prop="time" required>
          <el-date-picker v-model="ruleForm.time" type="datetime" placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="地点" prop="place">
          <el-input v-model="ruleForm.place"></el-input>
        </el-form-item>
        <el-form-item label="安排" prop="arrange">
          <el-input v-model="ruleForm.arrange"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="desc">
          <el-input v-model="ruleForm.description" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getMyApps, modifyInfo } from '@/api/activities.js'

export default {
  name: 'PendingActivity',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      searchInfo: '',
      activities: [],
      searcchRes: [],
      dialogVisible: false,
      curActivity: null,
      ruleForm: {
        name: '',
        organizer: '',
        type: 0,
        place: '',
        time: '',
        arrange: '',
        desc: '',
        attachment: '',
        image: '',
        number: 0,
        id: 0,
        groupName: this.$store.state.clubname
      },
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        organizer: [
          { required: true, message: '请输入组织者', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        place: [
          { required: true, message: '请输入活动地点', trigger: 'blur' },
          { min: 1, max: 31, message: '长度在 1 到 31 个字符', trigger: 'blur' }
        ],
        time: [
          { type: 'date', required: true, message: '请选择活动时间', trigger: 'change' }
        ],
        arrange: [
          { min: 1, max: 31, message: '长度在 1 到 31 个字符', trigger: 'blur' }
        ]
      }
    };
  },
  created: function () {
    getMyApps('', this.$store.state.clubname).then((response) => {
      this.searcchRes = response.data.items
      this.activities = response.data.items
      console.log(this.searcchRes)
    })
      .catch(error => {
        console.error(error)
      })
  },
  methods: {
    formatDate(date) {
      // 格式化日期时间
      const d = new Date(date);
      return `${d.getFullYear()}年${d.getMonth() + 1
        }月${d.getDate()}日 ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`;
    },
    handleSearch() {
      getMyApps(this.searchInfo, this.$store.state.clubname)
        .then((response) => {
          this.searcchRes = response.data.items
        })
        .catch((error) => {
          console.error(error)
        })
    },
    clearSearch() {
      this.searchInfo = ''
      this.searcchRes = this.activities
      // this.$router.go()
    },
    handleEdit(index) {
      const a = this.searcchRes[index]
      this.ruleForm.name = a.name
      this.ruleForm.organizer = a.organizer
      this.ruleForm.place = a.place
      this.ruleForm.time = a.time
      this.ruleForm.arrange = a.arrange
      this.ruleForm.desc = a.desc
      this.ruleForm.attachment = a.attachment
      this.ruleForm.image = a.image
      this.ruleForm.number = a.number
      this.ruleForm.id = a.id
      this.dialogVisible = true
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
    },
    submitForm() {
      console.log('before: ', JSON.stringify(this.searcchRes[this.curIndex]))
      console.log('after: ', JSON.stringify(this.ruleForm))
      modifyInfo(this.ruleForm).then(response => {
        console.log(response)
        this.dialogVisible = false;
        this.$message.success({
          message: '修改成功!',
          type: 'success'
        })
        this.$router.go()
      }).catch(error => {
        console.log(error)
        this.$message({
          message: error,
          type: 'info'
        })
      })
    }
  },
}
</script>

<style>
.el-table th {
  font-weight: bold;
  font-size: 18px;
}

.text {
  font-size: 14px;
  font-weight: bold;
}

.el-table .fail-row {
  background: rgb(250, 231, 231);
}

.el-table .success-row {
  background: #e1fce8;
}

.edit-button {
  margin-left: 30px;
}

.success-text {
  color: #4caf50;
}

.fail-text {
  color: #f44336;
}

.pending-text {
  color: #ff9800;
}
</style>