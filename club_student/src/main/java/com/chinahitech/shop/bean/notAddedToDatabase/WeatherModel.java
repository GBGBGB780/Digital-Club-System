package com.chinahitech.shop.bean.notAddedToDatabase;
import java.util.List;

public class WeatherModel {
    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<LivesModel> lives;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public List<LivesModel> getLives() {
        return lives;
    }

    public void setLives(List<LivesModel> lives) {
        this.lives = lives;
    }
}
