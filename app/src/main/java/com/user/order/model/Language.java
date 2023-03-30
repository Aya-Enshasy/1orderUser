package com.user.order.model;

public class Language {

    private String name;
    private String langCode;
    private int flag;

    public Language(String name, String langCode, int flag) {
        this.name = name;
        this.langCode = langCode;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
