package com.chinahitech.shop.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Group extends Organization{
//    private String id;
    @JsonProperty("username")
    private String name;
    private String leader;
    private String image;
    private String description;
    private String attachment;
//    private String password;
    private int hot;

    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", leader='" + leader + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", attachment='" + attachment + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }
}
