<template>
	<div>
		<el-upload
		    class="upload-demo"
		     ref="uploadFile"     
		     :action="actionUrl"
		     :before-upload="beforeFileUpload"
		     :auto-upload="false"
		     :on-success='onSuccess'
		     :on-error="onError">
		     <el-button size="small" type="primary">选择文件</el-button>
		</el-upload>
		<el-button type="primary" @click="submitUpload">确认上传</el-button>
	</div>
</template>

<script>
// import { BASE_URL_Manage } from 'config'
export default { 
     
    data () { 
        return { 
        //   actionUrl: BASE_URL_Manage+'/webManage/upload', // 上传地址 
        }
    },
    methods:{ 
    	//确认上传
    	submitUpload() { 
        	this.$refs.uploadFile.submit();
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
             this.$message.error("文件上传失败")
        }
    }
}
</script>