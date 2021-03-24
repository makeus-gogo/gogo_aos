package com.sixthank.gogo.src.main.home.models;

public class WorryResponse {
    private long worryNo;
    private String imgUrl;
    private String content;

    public WorryResponse(String content) {
        this.content = content;
    }

    public long getWorryNo() {
        return worryNo;
    }

    public void setWorryNo(long worryNo) {
        this.worryNo = worryNo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
