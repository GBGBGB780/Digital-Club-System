package com.chinahitech.shop.controller;

import com.chinahitech.shop.aop.RepeatLimit;
import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.service.ActivityService;

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
import java.util.*;

import com.chinahitech.shop.utils.JwtUtils;

@RestController
@RequestMapping("/activity")
@CrossOrigin
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    // 学生端
    @Value("${upload-dir}")
    private String uploadDir;

    @RepeatLimit
    @RequestMapping("/all")
    public Result getAll(String searchInfo){
        List<Activity> activities = activityService.query(searchInfo);
        System.out.println(activities);
        return Result.ok().data("items",activities);
    }

    // 活动详情（学生端）
    @RepeatLimit
    @PostMapping("/studentDetail")
    public Result getStudentDetail(String groupName, String activityName){
        System.out.println(activityName);
        Activity activity = activityService.getActivityByNameAndGroupName(activityName, groupName);
        return Result.ok().data("activity", activity);
    }

    @RepeatLimit
    @RequestMapping("/getVideo")
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

    @RepeatLimit
    @RequestMapping("/top")
    public Result getTop() {
        List<Activity> activities = activityService.queryTop();
        System.out.println(activities);
        return Result.ok().data("item", activities);
    }
    // end



    // 管理员端
    @RepeatLimit
    @GetMapping("/info")  // "token:xxx"
    public Result info(String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2021%2F1120%2F783a7b4ej00r2tvvx002fd200hs00hsg00hs00hs.jpg&thumbnail=660x2147483647&quality=80&type=jpg";
        return Result.ok().data("name",username).data("avatar",url);
    }

    // 我管理的社团的活动
    @RepeatLimit
    @PostMapping("/myActivity")
    public Result getMyActivities(String groupName){
        List<Activity> activities = activityService.getActivityByGroupName(groupName);
        System.out.println(groupName);
        return Result.ok().data("items", activities);
    }

    // 活动详情（管理端）
    @RepeatLimit
    @PostMapping("/managerDetail")
    public Result getManagerDetail(String groupName, String activityName){
        System.out.println(activityName);
        Activity activity = activityService.getActivityByNameAndGroupName(activityName, groupName);
        return Result.ok().data("activity", activity);
    }

    // 活动简介修改
    @RepeatLimit
    @PostMapping("/modifyDescription")
    public Result  modifyDescription(String groupName, String activityName, String description, String attachment, String image){
        System.out.println(activityName);
        System.out.println(description);
        activityService.updateDescription(groupName, activityName, description, attachment,image);
        return Result.ok();
    }

    //活动其他信息修改
    @RepeatLimit
    @PostMapping("/modifyInfo")
    public Result modifyInfo(Activity activity){
        System.out.println(activity.getName());
        activityService.modifyInfo(activity);
        return Result.ok();
    }

    //申请增加活动
    @RepeatLimit
    @PostMapping("/addActivity")
    public Result addActivity(Activity activity){
        System.out.println(activity.getName());
        activity.setIsAccepted(null);
        activityService.addActivity(activity);
        return Result.ok();
    }

    //申请删除活动
    @RepeatLimit
    @PostMapping("/deleteActivity")
    public Result deleteActivity(Activity activity){
        System.out.println(activity.getName());
        activity.setIsAccepted(null);
        activityService.deleteActivity(activity);
        return Result.ok();
    }

    @RepeatLimit
    @PostMapping("/uploadZip")
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

    @RepeatLimit
    @PostMapping("/submitZip")
    public ResponseEntity<Map<String, String>> submitZip(@RequestParam("groupId") String groupName, @RequestParam("attachment") String attachment, @RequestParam("name") String name) {
        try {
            activityService.updateAttachment(groupName, name, attachment);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Successfully updated attachment.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(createErrorResponse("Failed to update the attachment."));
        }
    }

    @RepeatLimit
    @PostMapping("/uploadPhoto")
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

    @RepeatLimit
    @PostMapping("/submitPhoto")
    public ResponseEntity<Map<String, String>> submitPhoto(@RequestParam("groupName") String groupName, @RequestParam("image") String image, @RequestParam("name") String name) {
        try {
            activityService.updateImage(groupName, name, image);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Successfully updated image.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(createErrorResponse("Failed to update the image."));
        }
    }

    @RepeatLimit
    @PostMapping("/getAttachment")//能直接下载文件，而不是在新标签页中打开的比较难搞，涉及到http报文，暂时不搞了
    // 这个是在新标签页中打开，对于zip完全没问题
    public ResponseEntity<Map<String, Object>> getAttachment(@RequestParam("id") int id) {
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

    //超级管理员端

    // 申请列表
    @RepeatLimit
    @RequestMapping("/allApps")
    public Result getAllApps(String searchInfo){
        List<Activity> activities = activityService.getAllApp(searchInfo);
        System.out.println(activities);
        return Result.ok().data("items",activities);
    }
    // 申请列表->详情
    @RepeatLimit
    @PostMapping("/appDetail")
    public Result getAppDetail(String groupName, String activityName){
        System.out.println(activityName);
        Activity activity = activityService.getAppByNameAndGroupName(activityName, groupName);
        return Result.ok().data("activity",activity);
    }

    //社团审批

    //接受申请
    @RepeatLimit
    @PostMapping("/accept")
    public Result accept(int activityId){
        System.out.println(activityId);
        // System.out.println(isaccepted);
        activityService.confirmApplication(activityId);
        return Result.ok();
    }

    //拒绝申请
    @RepeatLimit
    @PostMapping("/reject")
    public Result reject(int activityId){
        System.out.println(activityId);
        // System.out.println(isaccepted);
        activityService.denyApplication(activityId);
        return Result.ok();
    }
}
