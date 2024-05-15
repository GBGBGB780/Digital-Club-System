package com.chinahitech.shop.bean.notAddedToDatabase;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LivesModel {
    private String province;
    private String city;
    private String adcode;
    private String weather ;
    private String temperature ;
    private String winddirection;
    private String windpower;
    private String humidity;
    private String reporttime ;

//    public String getProvince() {
//        return province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getAdcode() {
//        return adcode;
//    }
//
//    public void setAdcode(String adcode) {
//        this.adcode = adcode;
//    }
//
//    public String getWeather() {
//        return weather;
//    }
//
//    public void setWeather(String weather) {
//        this.weather = weather;
//    }
//
//    public String getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(String temperature) {
//        this.temperature = temperature;
//    }
//
//    public String getWinddirection() {
//        return winddirection;
//    }
//
//    public void setWinddirection(String winddirection) {
//        this.winddirection = winddirection;
//    }
//
//    public String getWindpower() {
//        return windpower;
//    }
//
//    public void setWindpower(String windpower) {
//        this.windpower = windpower;
//    }
//
//    public String getHumidity() {
//        return humidity;
//    }
//
//    public void setHumidity(String humidity) {
//        this.humidity = humidity;
//    }
//
//    @Override
//    public String toString() {
//        return "LivesModel{" +
//                "province='" + province + '\'' +
//                ", city='" + city + '\'' +
//                ", adcode='" + adcode + '\'' +
//                ", weather='" + weather + '\'' +
//                ", temperature='" + temperature + '\'' +
//                ", winddirection='" + winddirection + '\'' +
//                ", windpower='" + windpower + '\'' +
//                ", humidity='" + humidity + '\'' +
//                ", reporttime='" + reporttime + '\'' +
//                '}';
//    }
//
//    public String getReporttime() {
//        return reporttime;
//    }
//
//    public void setReporttime(String reporttime) {
//        this.reporttime = reporttime;
//    }
}
