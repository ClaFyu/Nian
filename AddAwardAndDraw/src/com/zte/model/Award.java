package com.zte.model;

public class Award {
//    private String globalflag;      // 奖项标识，即简称，使用长度为 4 的字符串，第两字符为部门简称，后两字符为在相应范围内的序号
//    private String description;     // 奖项全称，用于在网页上进行提示
//    private int moneyperperson;     // 奖项个人金额
//    private String leadername;      // 设立奖项的人
//    private int restnum;            // 奖项原始人数

    private String abbr;
    private String name;
    private String leader;
    private String leaderJobNumber;
    private int moneyPerPerson;
    private int totalNumberOfMember;
    private int restNum;
    private int restNumFor01;
    private int restNumFor02;

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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeaderJobNumber() {
        return leaderJobNumber;
    }

    public void setLeaderJobNumber(String leaderJobNumber) {
        this.leaderJobNumber = leaderJobNumber;
    }

    public int getMoneyPerPerson() {
        return moneyPerPerson;
    }

    public void setMoneyPerPerson(int moneyPerPerson) {
        this.moneyPerPerson = moneyPerPerson;
    }

    public int getTotalNumberOfMember() {
        return totalNumberOfMember;
    }

    public void setTotalNumberOfMember(int totalNumberOfMember) {
        this.totalNumberOfMember = totalNumberOfMember;
    }

    public int getRestNum() {
        return restNum;
    }

    public void setRestNum(int restNum) {
        this.restNum = restNum;
    }

    public int getRestNumFor01() {
        return restNumFor01;
    }

    public void setRestNumFor01(int restNumFor01) {
        this.restNumFor01 = restNumFor01;
    }

    public int getRestNumFor02() {
        return restNumFor02;
    }

    public void setRestNumFor02(int restNumFor02) {
        this.restNumFor02 = restNumFor02;
    }

    public Award(String abbr, String name, String leader, String leaderJobNumber, int moneyPerPerson, int totalNumberOfMember, int restNum, int restNumFor01, int restNumFor02) {
        this.abbr = abbr;
        this.name = name;
        this.leader = leader;
        this.leaderJobNumber = leaderJobNumber;
        this.moneyPerPerson = moneyPerPerson;
        this.totalNumberOfMember = totalNumberOfMember;
        this.restNum = restNum;
        this.restNumFor01 = restNumFor01;
        this.restNumFor02 = restNumFor02;
    }
}
