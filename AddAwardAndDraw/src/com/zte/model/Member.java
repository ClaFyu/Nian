package com.zte.model;

public class Member {
    // department 和 did 构成复合主键，是由于两者共同才能构成简单的员工唯一标识
//    private String department;      // 员工部门，采用简称，与 Department 中 abbreviation 属性同义
//    private String did;             // 员工在部门中的序号，使用长度为 3 的字符串
//    private String name;            // 员工姓名+工号
//    private String bigAward;        // 员工已获全员奖项，采用简称，为了方便采用 Award 表中长度为4的描述方式，可为空，下同
//    private String littleAward;     // 员工已获部门奖项，采用简称，长度为4
    private int id;
    private String name;
    private String jobNumber;
    private String department;
    private String flagOfAward;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFlagOfAward() {
        return flagOfAward;
    }

    public void setFlagOfAward(String flagOfAward) {
        this.flagOfAward = flagOfAward;
    }
}
