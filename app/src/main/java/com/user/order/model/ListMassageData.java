package com.user.order.model;


public class ListMassageData {

    private Integer idSender;
    private String msg;
    private String img;
    private Long time;

    public ListMassageData(Integer idSender, String msg, String img, Long time) {
        this.idSender = idSender;
        this.msg = msg;
        this.img = img;
        this.time = time;
    }

    public ListMassageData() {
    }

    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
