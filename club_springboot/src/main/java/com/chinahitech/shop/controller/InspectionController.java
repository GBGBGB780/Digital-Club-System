package com.chinahitech.shop.controller;

import com.chinahitech.shop.aop.RepeatLimit;
import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.bean.Inspection;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/inspection")
@CrossOrigin
public class InspectionController {
    @Autowired
    private InspectionService inspectionService;

    @Value("${upload-dir}")
    private String uploadDir;

    // 管理员端

    //显示自己管理的社团的年检申请
    @RepeatLimit
    @RequestMapping("/findAllByGroup")
    public Result findAllByGroup(String groupName) {
        List<Inspection> inspections = inspectionService.findAllByGroup(groupName);
//        System.out.println(inspections);
        return Result.ok().data("items", inspections);
    }

    //通过年份查找自己管理的社团的年检申请
    @RepeatLimit
    @RequestMapping("/findAllByGroupAndYear")
    public Result findAllByGroupAndYear(String groupName, Year year) {
        List<Inspection> inspections = inspectionService.findAllByGroupAndYear(groupName, year);
//        System.out.println(inspections);
        return Result.ok().data("items", inspections);
    }

    //提交年检申请
    @RepeatLimit
    @PostMapping("/addInspection")
    public Result addInspection(Inspection inspection){
//        System.out.println(inspection.getGroupName());
        inspection.setIsAccepted(null);
        inspectionService.addInspection(inspection);
        return Result.ok();
    }

    //编辑年检申请
    @RepeatLimit
    @PostMapping("/modifyInfo")
    public Result modifyInfo(Inspection inspection){
//        System.out.println(inspection.getGroupName());
        inspectionService.modifyAttachment(inspection.getId(), inspection.getGroupName(), inspection.getAttachment());
//        System.out.println(inspection);
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
    public ResponseEntity<Map<String, String>> submitZip(@RequestParam("inspectionId") int inspectionId, @RequestParam("attachment") String attachment, @RequestParam("groupName") String groupName) {
        try {
            inspectionService.modifyAttachment(inspectionId, groupName, attachment);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Successfully updated attachment.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(createErrorResponse("Failed to update the attachment."));
        }
    }

    @RepeatLimit
    @PostMapping("/getAttachment")//能直接下载文件，而不是在新标签页中打开的比较难搞，涉及到http报文，暂时不搞了
    // 这个是在新标签页中打开，对于zip完全没问题
    public ResponseEntity<Map<String, Object>> getAttachment(@RequestParam("id") int id) {
        Inspection inspection = inspectionService.getInspectionById(id);
        Map<String, Object> response = new HashMap<>();
        if (inspection != null) {
            response.put("code", 20000);
            response.put("success", true);
            response.put("attachment", inspection.getAttachment());
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

    // 超级管理员端

    // 显示全部年检申请（可通过输入社团名搜索）
    @RepeatLimit
    @RequestMapping("/all")
    public Result findAll(String searchInfo) {
        List<Inspection> inspections = inspectionService.findAll(searchInfo);
//        System.out.println(inspections);
        return Result.ok().data("items", inspections);
    }

    //通过年份查找
    @RepeatLimit
    @RequestMapping("/findAllByYear")
    public Result findAllByYear(Year year) {
        List<Inspection> inspections = inspectionService.findAllByYear(year);
//        System.out.println(inspections);
        return Result.ok().data("items", inspections);
    }

    //编辑反馈
    @RepeatLimit
    @PostMapping("/addFeedback")
    public Result addFeedback(Inspection inspection){
//        System.out.println(inspection.getGroupName());
        inspection.setIsAccepted(null);
        inspectionService.addFeedback(inspection.getId(), inspection.getGroupName(), inspection.getFeedback());
        return Result.ok();
    }

    //接受申请
    @RepeatLimit
    @PostMapping("/accept")
    public Result accept(int inspectionId){
//        System.out.println(inspectionId);
        // System.out.println(isaccepted);
        inspectionService.confirmApplication(inspectionId);
        return Result.ok();
    }

    //拒绝申请
    @RepeatLimit
    @PostMapping("/reject")
    public Result reject(int inspectionId){
//        System.out.println(inspectionId);
        // System.out.println(isaccepted);
        inspectionService.denyApplication(inspectionId);
        return Result.ok();
    }
}
