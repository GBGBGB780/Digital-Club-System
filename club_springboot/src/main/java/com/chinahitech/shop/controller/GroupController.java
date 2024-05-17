package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chinahitech.shop.utils.Result;
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
import java.util.HashMap;
import java.util.List;
import com.chinahitech.shop.utils.JwtUtils;
import java.util.Map;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {
    @Autowired
    private GroupService groupService;

    // 学生端
    @Value("${upload-dir}")
    private String uploadDir;

    @RequestMapping("/all")
    public Result getAll(String searchinfo){
        List<Group> groups = groupService.query(searchinfo);
        System.out.println(groups);
        return Result.ok().data("items",groups);
    }

    // 社团详情（学生端）
    @PostMapping("/studentDetail")
    public Result getStudentDetail(String groupname){
        System.out.println(groupname);
        Group group = groupService.getByName(groupname);
        return Result.ok().data("group",group);
    }

    @RequestMapping("/getvideo")
    public Result getVideo() {
        try {
            // 获取视频文件的相对路径
            String relativePath = "videotest.mp4";

            // 构造视频URL
            String videoUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/")
                    .path(relativePath)
                    .toUriString();
            System.out.println(videoUrl);
            return Result.ok().data("url", videoUrl);
        } catch (Exception e) {
            return Result.error().message("获取视频失败");
        }
    }

    @RequestMapping("/top")
    public Result getTop() {
        List<Group> groups = groupService.queryTop();
        System.out.println(groups);
        return Result.ok().data("item", groups);
    }
    // end



    // 管理员端
    
//    // 登录系统
//    @PostMapping("/login")
//    public Result login(String groupName, String password){
////        System.out.println(manager);
////        Group dbManager = groupService.getByName(manager.getName());
////        if (dbManager != null && dbManager.getPassword().equals(manager.getPassword())) {
////            // 验证通过，生成 token 返回给前端
////            String token = JwtUtils.generateToken(manager.getName());
////            return Result.ok().data("token", token);
////        } else {
////            // 验证失败，返回错误信息
////            return Result.error().message("用户名或密码不正确");
////        }
//
//        Group group = groupService.login(groupName, password);
//        if (group.getPassword() != null){
//            // 验证通过，生成 token 返回给前端
//            String token = JwtUtils.generateToken(group.getName());
////        System.out.println(groupName + password);
//            return Result.ok().data("token", token);
//        } else {
//            // 验证失败，返回错误信息
//            return Result.error().message("用户名或密码不正确");
//        }
//    }
//
//    @GetMapping("/info")  // "token:xxx"
//    public Result info(String token){
//        String username = JwtUtils.getClaimsByToken(token).getSubject();
//        String url = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2021%2F1120%2F783a7b4ej00r2tvvx002fd200hs00hsg00hs00hs.jpg&thumbnail=660x2147483647&quality=80&type=jpg";
//        return Result.ok().data("name",username).data("avatar",url);
//    }
//
//    // 退出系统
//    @PostMapping("/logout")  // "token:xxx"
//    public Result logout(){
//        return Result.ok();
//    }

    // 我的社团
//    @PostMapping("/myclub")
//    public Result getMyclub(String groupname){
//        List<Group> myclubs = groupService.queryGroup(groupname);
//        System.out.println(groupname);
//        return Result.ok().data("items",myclubs);
//    }

    // 社团详情（管理端）
    @PostMapping("/managerDetail")
    public Result getManagerDetail(String groupname){
        System.out.println(groupname);
        Group group = groupService.getByName(groupname);
        return Result.ok().data("group",group);
    }

    // 社团简介更改
    @PostMapping("/modifydescription")
    public Result  modifyDescription(String groupname, String description,String attachment,String image){
        System.out.println(groupname);
        System.out.println(description);
        groupService.updateDescription(groupname, description, attachment,image);
        return Result.ok();
    }

//    // 社团编辑详情->更新密码
//    @PostMapping("/modifypassword")
//    public Result  modifyPassword(String groupname, String password){
//        System.out.println(groupname);
//        System.out.println(password);
//        groupService.updatePassword(groupname, password);
//        return Result.ok();
//    }

    @PostMapping("/uploadzip")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {

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

    @PostMapping("/submitzip")
    public ResponseEntity<Map<String, String>> submitZip(@RequestParam("attachment") String attachment, @RequestParam("name") String name) {
        try {
            groupService.updateAttachment(name, attachment);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Successfully updated attachment.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(createErrorResponse("Failed to update the attachment."));
        }
    }
    @PostMapping("/uploadphoto")
    public ResponseEntity<Map<String, String>> uploadPhoto(@RequestParam("file") MultipartFile file) {

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

    @PostMapping("/submitphoto")
    public ResponseEntity<Map<String, String>> submitPhoto(@RequestParam("image") String image, @RequestParam("name") String name) {
        try {
            groupService.updateImage(name, image);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Successfully updated image.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(createErrorResponse("Failed to update the image."));
        }
    }
    @PostMapping("/getattachment")//能直接下载文件，而不是在新标签页中打开的比较难搞，涉及到http报文，暂时不搞了
    // 这个是在新标签页中打开，对于zip完全没问题
    public ResponseEntity<Map<String, Object>> getAttachment(@RequestParam("id") String id) {
        Group group = groupService.getGroupById(id);
        Map<String, Object> response = new HashMap<>();
        if (group != null) {
            response.put("code", 20000);
            response.put("success", true);
            response.put("attachment", group.getAttachment());
        } else {
            response.put("code", 50000);
            response.put("success", false);
            response.put("message", "Group not found");
        }
        return ResponseEntity.ok(response);
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
    //end
}
