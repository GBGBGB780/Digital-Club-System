package com.chinahitech.shop.service;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;

//上传文件
@Controller
public class Upload {

//    @Autowired
//    SalaryService salaryService;
    private final String XLSX = ".xlsx";
    private final String XLS=".xls";

    @GetMapping("/toupload")
    public String index() {
        return "Upload.html";
    }


    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Model model) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to Upload");
            return "uploadStatus.html";
        }
        try {
            // Get the file and save it
            String tempfilename = file.getOriginalFilename();
//            System.out.println(tempfilename);

//            String path = request.getSession().getServletContext().getRealPath("/uploads/");
            String path = "C:/Users/minislother/Desktop/test-excel/";//所需打开文件的路径
//            System.out.println(path);
            String x = path + tempfilename;
//            System.out.println(x);
            String fileType  = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            // 把文件的名称设置唯一值，uuid
//            String uuid = UUID.randomUUID().toString().replace("-", "");
//            String filename = uuid+fileType;
//            System.out.println(fileType);
            if(fileType.equals(XLSX) || fileType.equals(XLS)){
                try {
                    File f1 = new File(x);
//                    String pathname = file.getName();
//                    JSONArray jsonArray = utils.readExcel(f1);
//                    String s = JSON.toJSONString(jsonArray);
//                    System.out.println(s);
//                    utils.writeExcel(f1,x,s);
//                    file.transferTo(new File(path,filename));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("message", "You successfully uploaded '" + tempfilename + "'");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "uploadStatus.html";
    }

}
