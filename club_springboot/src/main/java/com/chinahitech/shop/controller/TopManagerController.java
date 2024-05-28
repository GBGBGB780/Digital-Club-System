package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.bean.notAddedToDatabase.RegisterUser;
import com.chinahitech.shop.defineException.EmailException;
import com.chinahitech.shop.service.EmailService;
import com.chinahitech.shop.service.TopManagerService;
import com.chinahitech.shop.utils.JwtUtils;
import com.chinahitech.shop.utils.RedisUtils;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/topManager")
@CrossOrigin
public class TopManagerController {
    @Autowired
    private TopManagerService topManagerService;

    @PostMapping("/login")
    // querystring: username=zhangsan&password=123   User user,String username,String password
    // json: {username:zhangsan,password:123}
    // 如果前端传递的数据是json格式，必须使用对象接收，同时需要添加@RequestBody
    public Result login(String userId, String password){
        User user = topManagerService.login(userId, password);
//        User dbStudent = topManagerService.getByStunumber(student.getStunumber());
//        if (dbStudent != null && dbStudent.getPassword().equals(student.getPassword())) {
        if (user.getPassword() == null) {
            // 验证失败，返回错误信息
            return Result.error().message("用户名或密码不正确");
        } else if (user.getStatus() < 1) {
            // 权限不足，返回错误信息
            return Result.error().message("用户权限不足");
        } else {
            // 验证通过，生成 token 返回给前端
            String token = JwtUtils.generateToken(user.getUserId());
//        System.out.println(userId + password);
            return Result.ok().data("token", token);
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterUser user) {
//        System.out.println(user);
        String stunumber = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String valicode = user.getValicode();

//        System.out.println(stunumber);
//        System.out.println(password);
//        System.out.println(email);
//        System.out.println(valicode);

        String correctValicode = RedisUtils.get(email).toString();

//        System.out.println("this" + correctValicode);

        if (Objects.equals(correctValicode, valicode)){
            topManagerService.addManager(stunumber, password, email);
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

    //用户密码修改
    @PostMapping("/modifypass")
    public Result modifypassword(String userId, String password){
        System.out.println(userId);
        System.out.println(password);
        topManagerService.updatePassword(userId, password);
        return Result.ok();
    }

    //用户电话修改
    @PostMapping("/modifyphone")
    public Result modifyphone(String userId, String phone){
        System.out.println(userId);
        System.out.println(phone);
        topManagerService.updatePhone(userId, phone);
        return Result.ok();
    }

    //用户简介修改
    @PostMapping("/modifydescription")
    public Result modifydescription(String userId, String description){
        System.out.println(userId);
        System.out.println(description);
        topManagerService.updateDescription(userId, description);
        return Result.ok();
    }

    //用户昵称修改
    @PostMapping("/modifynickname")
    public Result modifynickname(String userId, String nickname){
        System.out.println(userId);
        System.out.println(nickname);
        topManagerService.updateNickname(userId, nickname);
        return Result.ok();
    }

    //用户校区,学院和专业信息修改
    @PostMapping("/modifymajor")
    public Result modifymajor(String userId, String campus, String school, String major){
        System.out.println(userId);
        System.out.println(campus);
        System.out.println(school);
        System.out.println(major);
        topManagerService.updateMajor(userId, campus, school, major);
        return Result.ok();
    }

    //用户资料显示
    @PostMapping("/profile")
    public Result getProfile(String userId){
        System.out.println(userId);
        User user = topManagerService.getByUserId(userId);
        System.out.println(user);
        return Result.ok().data("user", user);
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

    //添加用户
    @PostMapping("/addUser")
    public Result addUser(@RequestBody RegisterUser user) {
//        System.out.println(user);
        String stunumber = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String valicode = user.getValicode();

//        System.out.println(stunumber);
//        System.out.println(password);
//        System.out.println(email);
//        System.out.println(valicode);

        String correctValicode = RedisUtils.get(email).toString();

//        System.out.println("this" + correctValicode);

        if (Objects.equals(correctValicode, valicode)){
            topManagerService.addUser(stunumber, password, email);
            return Result.ok().message("注册成功");
        } else {
            return Result.error().message("注册出错!");
        }
    }

    //查看用户信息
    @RequestMapping("/getAllUsers")
    public Result getAllUsers(String searchinfo){
        List<User> Users = topManagerService.getAllUsers(searchinfo);
        System.out.println(Users);
        return Result.ok().data("items",Users);
    }

    //修改用户信息
    @PostMapping("/modifyUserInfo")
    public Result modifyUserInfo(User user){
        System.out.println(user);
        topManagerService.updateUserInfo(user);
        return Result.ok();
    }

    //删除用户
    @RequestMapping("/deleteUser")
    public Result deleteUser(User user){
        System.out.println(user.getUserName());
        topManagerService.deleteUser(user);
        return Result.ok();
    }
}
