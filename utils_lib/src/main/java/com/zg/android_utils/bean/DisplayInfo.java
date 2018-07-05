package com.zg.android_utils.bean;

public class DisplayInfo {
    private String systemObjectId;//所属系统的id
    private String displayPreLocationObjectId;//前一个设备id
    private Integer technology;//工艺，小块背景色

    public Integer getTechnology() {
        return technology;
    }

    public void setTechnology(Integer technology) {
        this.technology = technology;
    }

    private Integer displayWidth;

    public DisplayInfo(String systemObjectId, String displayPreLocationObjectId,
                       Integer technology, Integer displayWidth) {
        this.systemObjectId = systemObjectId;
        this.displayPreLocationObjectId = displayPreLocationObjectId;
        this.technology = technology;
        this.displayWidth = displayWidth;
    }

    public DisplayInfo() {
    }

    public Integer getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(Integer displayWidth) {
        this.displayWidth = displayWidth;
    }

    public String getSystemObjectId() {
        return systemObjectId;
    }


    public void setSystemObjectId(String systemObjectId) {
        this.systemObjectId = systemObjectId;
    }

    public String getDisplayPreLocationObjectId() {
        return displayPreLocationObjectId;
    }

    public void setDisplayPreLocationObjectId(String displayPreLocationObjectId) {
        this.displayPreLocationObjectId = displayPreLocationObjectId;
    }
}
