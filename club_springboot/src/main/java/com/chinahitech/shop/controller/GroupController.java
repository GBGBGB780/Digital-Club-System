package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.bean.StuApp;
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

    //申请新建社团
    @PostMapping("/addGroup")
    public Result addGroup(@RequestBody Group group){
        group.setIsAccepted(false);
        groupService.insert(group);
        return Result.ok();
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
    public ResponseEntity<Map<String, Object>> getAttachment(@RequestParam("id") int id) {
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

    //超级管理员端

    // 申请列表
    @RequestMapping("/allApps")
    public Result getAllApps(String searchinfo){
        List<Group> groups = groupService.getAllApp(searchinfo);
        System.out.println(groups);
        return Result.ok().data("items",groups);
    }

    // 申请列表->详情
    @PostMapping("/appDetail")
    public Result getAppDetail(String groupname){
        System.out.println(groupname);
        Group group = groupService.getAppByName(groupname);
        return Result.ok().data("group",group);
    }

    //社团审批

    //接受申请
    @PostMapping("/accept")
    public Result accept(int groupId){
        System.out.println(groupId);
        // System.out.println(isaccepted);
        groupService.confirmApplication(groupId);
        return Result.ok();
    }

    //拒绝申请
    @PostMapping("/reject")
    public Result reject(int groupId){
        System.out.println(groupId);
        // System.out.println(isaccepted);
        groupService.denyApplication(groupId);
        return Result.ok();
    }
}
