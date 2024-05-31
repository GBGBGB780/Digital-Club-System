package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.bean.notAddedToDatabase.RegisterUser;
import com.chinahitech.shop.defineException.EmailException;
import com.chinahitech.shop.service.EmailService;
import com.chinahitech.shop.service.StuService;
import com.chinahitech.shop.utils.JwtUtils;
import com.chinahitech.shop.utils.RedisUtils;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StuController {
    @Autowired
    private StuService stuService;

    @PostMapping("/login")
    // querystring: username=zhangsan&password=123   User user,String username,String password
    // json: {username:zhangsan,password:123}
    // 如果前端传递的数据是json格式，必须使用对象接收，同时需要添加@RequestBody
    public Result login(String stuNumber, String password){
        User stu = stuService.login(stuNumber, password);
//        User dbStudent = stuService.getByStunumber(student.getStunumber());
//        if (dbStudent != null && dbStudent.getPassword().equals(student.getPassword())) {
        if (stu.getPassword() != null){
            // 验证通过，生成 token 返回给前端
            String token = JwtUtils.generateToken(stu.getUserId());
//        System.out.println(stunumber + password);
            return Result.ok().data("token", token);
        } else {
            // 验证失败，返回错误信息
            return Result.error().message("用户名或密码不正确");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterUser student) {
//        System.out.println(student);
        String stunumber = student.getUsername();
        String password = student.getPassword();
        String email = student.getEmail();
        String valicode = student.getValicode();

//        System.out.println(stunumber);
//        System.out.println(password);
//        System.out.println(email);
//        System.out.println(valicode);

        String correctValicode = RedisUtils.get(email).toString();

//        System.out.println("this" + correctValicode);

        if (Objects.equals(correctValicode, valicode)){
            stuService.addStudent(stunumber, password, email);
            return Result.ok().message("注册成功");
        } else {
            return Result.error().message("注册出错!");
        }
    }

    @PostMapping("/validateEmail")
    public Result validateEmail(String email) throws Exception {
//        String email = emailInfo.getEmail();
        System.out.println(email);
        EmailService sEmail;
        try{
            sEmail = new EmailService(email);
        } catch(EmailException err){
            return Result.error().message(err.expMessage());
        }
        sEmail.sendEmail();
        return Result.ok().message("邮箱发送成功!");
    }

    @PostMapping("/modifyPass")
    public Result modifyPassword(String stuNumber, String password){
        System.out.println(stuNumber);
        System.out.println(password);
        stuService.updatePassword(stuNumber, password);
        return Result.ok();
    }

    @PostMapping("/modifyPhone")
    public Result modifyPhone(String stuNumber, String phone){
        System.out.println(stuNumber);
        System.out.println(phone);
        stuService.updatePhone(stuNumber, phone);
        return Result.ok();
    }

    @PostMapping("/modifyDescription")
    public Result modifyDescription(String stuNumber, String description){
        System.out.println(stuNumber);
        System.out.println(description);
        stuService.updateDescription(stuNumber, description);
        return Result.ok();
    }

    @PostMapping("/modifyNickname")
    public Result modifyNickname(String stuNumber, String nickname){
        System.out.println(stuNumber);
        System.out.println(nickname);
        stuService.updateNickname(stuNumber, nickname);
        return Result.ok();
    }

    @PostMapping("/profile")
    public Result getProfile(String stuNumber){
        System.out.println(stuNumber);
        User student = stuService.getByStunumber(stuNumber);
        System.out.println(student);
        return Result.ok().data("student", student);
    }


    @GetMapping("/info")  // "token:xxx"
    public Result info(String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2021%2F1120%2F783a7b4ej00r2tvvx002fd200hs00hsg00hs00hs.jpg&thumbnail=660x2147483647&quality=80&type=jpg";
        return Result.ok().data("name",username).data("avatar",url);
    }

    @PostMapping("/logout")  // "token:xxx"
    public Result logout(){
        return Result.ok();
    }
}