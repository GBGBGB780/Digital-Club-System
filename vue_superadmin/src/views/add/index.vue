<template>
    <div style="display: flex;margin-top: 10%;">
        <div class="uploadbox1">
            <div style="text-align: center;margin-top: 5px;">批量添加学生</div>
            <div class="size-icon">
                <i class="el-icon-upload" />
            </div>
            <div style="text-align: center;">
                <el-upload
                    class="upload-demo"
                    ref="uploadFile"     
                    action="string"
                    :limit="1"
                    :on-exceed="handleExceed"
                    :before-upload="beforeFileUpload"
                    :auto-upload="false"
                    :on-success='onSuccess'
                    :on-error="onError"
                    :http-request="UploadRequest">
                    <el-button style="width: 200px" type="primary">选择文件</el-button>
                </el-upload>
            </div>
            <div style="text-align: center;margin-top: 20px;">
                <el-button type="primary" @click="submitUpload">确认上传</el-button>
            </div>
            <div style="text-align: center;margin-top: 20px;">
                <a @click="downloadexcel">点击下载模板文件</a>  
            </div>
        </div>

        <div class="uploadbox2">
            <div style="text-align: center;margin-top: 5px;">批量添加管理员</div>
            <div class="size-icon">
                <i class="el-icon-upload" />
            </div>
            <div style="text-align: center;">
                <el-upload
                    class="upload-demo"
                    ref="uploadFile1"     
                    action="string"
                    :limit="1"
                    :on-exceed="handleExceed"
                    :before-upload="beforeFileUpload"
                    :auto-upload="false"
                    :on-success='onSuccess'
                    :on-error="onError"
                    :http-request="UploadRequest">
                    <el-button style="width: 200px" type="primary">选择文件</el-button>
                </el-upload>
            </div>
            <div style="text-align: center;margin-top: 20px;">
                <el-button type="primary" @click="submitUpload">确认上传</el-button>
            </div>
            <div style="text-align: center;margin-top: 20px;">
                <a @click="downloadexcel">点击下载模板文件</a>
            </div>
        </div>

        <div class="uploadbox3">
            <div style="text-align: center;margin-top: 5px;">批量添加社团</div>
            <div class="size-icon">
                <i class="el-icon-upload" />
            </div>
            <div style="text-align: center;">
                <el-upload
                    class="upload-demo"
                    ref="uploadFile2"     
                    action="string"
                    :limit="1"
                    :on-exceed="handleExceed"
                    :before-upload="beforeFileUpload"
                    :auto-upload="false"
                    :on-success='onSuccess'
                    :on-error="onError"
                    :http-request="UploadRequest">
                    <el-button style="width: 200px" type="primary">选择文件</el-button>
                </el-upload>
            </div>
            <div style="text-align: center;margin-top: 20px;">
                <el-button type="primary" @click="submitUpload">确认上传</el-button>
            </div>
            <div style="text-align: center;margin-top: 20px;">
                <a @click="downloadexcel">点击下载模板文件</a>
            </div>
        </div>
    </div>
</template>

<script>
import { uploadfile1,download1 } from '@/api/user'
export default{ 
    data (){ 
        return { 
        //   actionUrl: BASE_URL_Manage+'/webManage/upload', // 上传地址 
        }
    },
    methods:{ 
    	//确认上传
    	submitUpload() { 
            this.$refs.uploadFile.submit()
      	},
        submitUpload1() { 
            this.$refs.uploadFile.submit()
      	},
        submitUpload2() { 
            this.$refs.uploadFile.submit()
      	},
      	 //文件上传之前
        beforeFileUpload(file){ 
            const File = file.name.replace(/.+\./,"");
            if (['xlsx','xls'].indexOf(File.toLowerCase())===-1 ) { 
   
                this.$message.error('只支持上传xlsx、xls文件格式!');
                this.$refs.uploadFile.clearFiles();
                return false;
            }            
        },
         //文件上传成功
        onSuccess(){ 
            this.$message.success("文件上传成功");       
        },
        //上传失败
        onError(){ 
             this.$refs.upload.clearFiles();
             this.$message.error("文件上传失败,请联系管理员")
        },
        handleExceed(){
            this.$message({
                type:'warning',
                message:'超出最大上传文件数量的限制！'
            });return
        },
        UploadRequest(item){
            let formData = new FormData();
            formData.append('file', item.file);
            console.log(item)
            uploadfile1(formData).then(response => {
            })
            // .catch((error)=>{
            //     console.log(error)
            // })
        },
        downloadexcel(){
            download1("模板.xlsx").then(response => {
            }).catch((error)=>{
                console.log(error)
            })
        }
    }
}
</script>

<style scoped>
.uploadbox1{
    margin: auto;
    width: 400px;
    height: 500px;
    border: 1px dashed #11222e;
    border-radius: 10px;
}
.uploadbox2{
    margin: auto;
    width: 400px;
    height: 500px;
    border: 1px dashed #11222e;
    border-radius: 10px;
}
.uploadbox3{
    margin: auto;
    width: 400px;
    height: 500px;
    border: 1px dashed #11222e;
    border-radius: 10px;
}
.size-icon{
  font-size: 200px;
  text-align: center;
  color: #76afd8;
}
</style>