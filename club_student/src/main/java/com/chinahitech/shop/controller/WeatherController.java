package com.chinahitech.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.chinahitech.shop.bean.notAddedToDatabase.LivesModel;
import com.chinahitech.shop.bean.notAddedToDatabase.WeatherModel;
import com.chinahitech.shop.utils.HttpsUtil;
import com.chinahitech.shop.utils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
@RestController
@CrossOrigin
public class WeatherController {
    @RequestMapping("/getweather")
    public Result getWeather() {
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?city=440402&key=76bb5f7e39f7b4b1c497bbad05bc44aa";
        String s = HttpsUtil.get(url);
//        System.out.println(s);
        JSONObject object = JSONObject.parseObject(s);
        WeatherModel jb = (WeatherModel)JSONObject.toJavaObject(object, WeatherModel.class);
        LivesModel livesModel = jb.getLives().get(0);
//        System.out.println(livesModel);
        int c =Integer.valueOf(livesModel.getTemperature());
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(new Date());
        String remark = null;
        if (c<=26 && c>=18){
            remark="今天温度适宜";
        }else if (c>26){
            remark="今天温度较高,注意防晒";
        }else if(c<18){
            remark="今天温度较低,注意保暖";
        }
        String zong = "日期: "+livesModel.getReporttime()+"  "+currSun+"\n"+
                "所在城市: "+livesModel.getCity()+"\n"+
                "天气: " + livesModel.getWeather()+"\n"+
                "温度: "+ livesModel.getTemperature()+"  "+remark+"\n"+
                "风向: "+ livesModel.getWinddirection()+"风"+"\n"+
                "风级: "+livesModel.getWindpower();
//        System.out.println(zong);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("weather", livesModel.getWeather());
        responseData.put("temperature", livesModel.getTemperature());
        responseData.put("remark", remark);

        return Result.ok().data(responseData);
    }


}
