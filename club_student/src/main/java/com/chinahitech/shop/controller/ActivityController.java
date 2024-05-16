package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/activity")
@CrossOrigin
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    // 学生端
    @Value("${upload-dir}")
    private String uploadDir;

    @RequestMapping("/all")
    public Result getAll(String searchinfo){
        List<Activity> activities = activityService.query(searchinfo);
        System.out.println(activities);
        return Result.ok().data("items",activities);
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
        List<Activity> activities = activityService.queryTop();
        System.out.println(activities);
        return Result.ok().data("item", activities);
    }
    // end



    // 管理员端

    // 登录系统
    @PostMapping("/login")
    public Result login(@RequestBody Activity manager){
        System.out.println(manager);
        Activity dbManager = activityService.getByName(manager.getName());
        if (dbManager != null && dbManager.getPassword().equals(manager.getPassword())) {
            // 验证通过，生成 token 返回给前端
            String token = JwtUtils.generateToken(manager.getName());
            return Result.ok().data("token", token);
        } else {
            // 验证失败，返回错误信息
            return Result.error().message("用户名或密码不正确");
        }
    }

    @GetMapping("/info")  // "token:xxx"
    public Result info(String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2021%2F1120%2F783a7b4ej00r2tvvx002fd200hs00hsg00hs00hs.jpg&thumbnail=660x2147483647&quality=80&type=jpg";
        return Result.ok().data("name",username).data("avatar",url);
    }

    // 退出系统
    @PostMapping("/logout")  // "token:xxx"
    public Result logout(){
        return Result.ok();
    }

    // 我的活动
    @PostMapping("/myactivity")
    public Result getMyActivities(String name){
        List<Activity> activities = activityService.queryActivity(name);
        System.out.println(name);
        return Result.ok().data("items",activities);
    }

    // 我的活动细节
    @PostMapping("/detail")
    public Result getActivityDetail(String name){
        System.out.println(name);
        Activity activity = activityService.getByName(name);
        return Result.ok().data("activity",name);
    }

    // 活动编辑详情->更新详情
    @PostMapping("/modifydescription")
    public Result  modifyDescription(String name, String description,String attachment,String image){
        System.out.println(name);
        System.out.println(description);
        activityService.updateDescription(name, description, attachment,image);
        return Result.ok();
    }

    // 活动编辑详情->更新密码
    @PostMapping("/modifypassword")
    public Result  modifyPassword(String name, String password){
        System.out.println(name);
        System.out.println(password);
        activityService.updatePassword(name, password);
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
            activityService.updateAttachment(name, attachment);

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
            activityService.updateImage(name, image);

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
        Activity activity = activityService.getActivityById(id);
        Map<String, Object> response = new HashMap<>();
        if (activity != null) {
            response.put("code", 20000);
            response.put("success", true);
            response.put("attachment", activity.getAttachment());
        } else {
            response.put("code", 50000);
            response.put("success", false);
            response.put("message", "Activity not found");
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