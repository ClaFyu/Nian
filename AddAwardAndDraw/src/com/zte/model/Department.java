package com.zte.model;

public class Department {
//    private String abbreviation;    // 部门标识，即简称，两个字符，"00"应表示全员，在此无意义
//    private String fullname;        // 部门全称，用于在网页上进行描述
//    private int totalnum;           // 部门总人数
//    private int restnum;            // 部门未获奖人数（不影响部门内部抽奖）
    private String abbr;
    private String name;
    private int numberOfMember;

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfMember() {
        return numberOfMember;
    }

    public void setNumberOfMember(int numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public Department(String abbr, String name, int numberOfMember) {
        this.abbr = abbr;
        this.name = name;
        this.numberOfMember = numberOfMember;
    }
}
