package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.StuApp;
import com.chinahitech.shop.service.StuAppService;
import com.chinahitech.shop.utils.Result;
import com.chinahitech.shop.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/application")
@CrossOrigin
public class StuAppController {
    @Autowired
    private StuAppService stuAppService;
    @Autowired
    private GroupService groupService;

    //学生端
    @Value("${upload-dir}")
    private String uploadDir;

    @RequestMapping("/all")
    public Result getAll() {
        List<StuApp> StuApps = stuAppService.query();
        System.out.println(StuApps);
        return Result.ok().data("items", StuApps);
    }

    @PostMapping("/myapps")
    public Result getMyapps(String stunumber) {
        List<StuApp> StuApps = stuAppService.queryMyapp(stunumber);
        System.out.println(stunumber);
        return Result.ok().data("items", StuApps);
    }

    @PostMapping("/submit")
    public Result submit(@RequestBody StuApp stuApp) {
        stuApp.setCreateTime(new Date());
        System.out.println(stuApp);
        stuAppService.insert(stuApp);
        groupService.addHot(stuApp.getGroupName());
        return Result.ok();
    }
    //



    // 管理员端

    // 申请列表
    @PostMapping("/recvapps")
    public Result getRecvapps(String groupname){
        List<StuApp> StuApps = stuAppService.queryRecvapp(groupname);
        System.out.println(groupname);
        return Result.ok().data("items", StuApps);
    }

    // 申请列表->详情
    @PostMapping("/recvapp")
    public Result getRecvapp(Integer id) {
        StuApp StuApp = stuAppService.queryDetailapp(id);
        String isAcceptedStr = stuAppService.findIsAccepted(id); // 直接获取特定id的isAccepted值
        System.out.println(id);
        return Result.ok().data("items", StuApp).data("isAccepted", isAcceptedStr);
    }

    //更新附件
    @PostMapping("/updateAttachment")
    public Result updateAttachment(@RequestParam("applicationid") int applicationid,
                                   @RequestParam("attachment") String attachmentUrl) {
        stuAppService.updateAttachment(applicationid, attachmentUrl);
        return Result.ok().data("applicationid", applicationid).data("attachment", attachmentUrl);
    }

    @PostMapping("/uploadzip")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String contentType = file.getContentType();
        if (!(contentType.equals("application/zip") || contentType.equals("application/x-zip-compressed"))) {
            return ResponseEntity.badRequest().body(createErrorResponse("Only zip files are allowed."));
        }

        String fileName = generateUniqueFileName(file.getOriginalFilename());

        try {
            Path targetLocation = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .pathSegment("upload")
                    .pathSegment(fileName)
                    .toUriString();

            Map<String, String> response = new HashMap<>();
            response.put("fileUrl", fileUrl);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(createErrorResponse("Failed to upload the file."));
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        return timestamp + "_" + originalFilename;
    }

    private Map<String, String> createErrorResponse(String errorMessage) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);
        return errorResponse;
    }

    //接受申请
    @PostMapping("/accept")
    public Result acceptApplication(Integer applicationid){
        System.out.println(applicationid);
        // System.out.println(isaccepted);
        stuAppService.confirmApplication(applicationid);
        return Result.ok();
    }

    //拒绝申请
    @PostMapping("/reject")
    public Result rejectApplication(Integer applicationid){
        System.out.println(applicationid);
        // System.out.println(isaccepted);
        stuAppService.denyApplication(applicationid);
        return Result.ok();
    }
}
