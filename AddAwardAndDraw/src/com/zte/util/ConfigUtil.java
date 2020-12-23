package com.zte.util;

import com.zte.dao.AwardDao;
import com.zte.dao.DepartmentDao;
import com.zte.dao.MemberDao;
import com.zte.model.Award;
import com.zte.model.Department;
import com.zte.util.common.Triplet;
import com.zte.util.common.Tuple;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigUtil {
    // 部门信息全局变量，自 Python 添加部门信息后就不会进行改变
    public static Map<String, Tuple<String, Integer>> departmentInfo = new HashMap<>();
    // 部门人数比例全局变量，用于对未分配人数对奖项进行比例分配
    public static Map<String, Double> memberRate = new HashMap<>();
    // 获奖名单，在奖项设置时确定，奖项抽取后写入数据库
    // 含义分别为：奖项简称、奖项全称、剩余被展示人数、被抽取人姓名+工号、被抽取人部门
    public static Map<String, Triplet<String, Triplet<Integer, Integer, Integer>, List<Tuple<String, String>>>> awardLuckyList = new HashMap<>();
    // 中奖人总表，设置原因是因为在真正完成抽奖前，后台会将预选人员抽出放进 awardLuckyList 中，而其结构稍微有点复杂，不太好在抽取的时候做到已选的检测
    // 虽然可以在数据库中添加一个属性，但很难保证运行效率，故添加一个预选总表，可以在预抽取和真实抽取的时候都可以使用
    // 其可以存储数据库中员工的 id 减少空间
    public static List<Integer> luckyMember = new ArrayList<>();

    // 将一个整数修改为长度为2或3的字符串，长度不足则填充0
    public static String numToStrN(int i, int length) {
        DecimalFormat df = null;

        if (length == 2) {
            df = new DecimalFormat("00");
        } else if (length == 3) {
            df = new DecimalFormat("000");
        } else {
            df = new DecimalFormat();
        }

        return df.format(i);
    }

    // 姓名+工号分割函数
    public static String[] splitNameAndNumber(String person) {
        String[] strings = {"0", "0"};
        int i = 0;

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]+|\\d+");
        Matcher m = p.matcher(person);

        while (m.find()) {
            strings[i++] = m.group();
        }

        return strings;
    }

    private static void updateDepartmentInfoAndMember() {
        DepartmentDao departmentDao = new DepartmentDao();
        List<Department> list = departmentDao.searchAll();

        if (list.size() != 0) {
            double sum = 0;

            for (Department department: list) {
                departmentInfo.put(department.getAbbr(),
                        new Tuple<>(department.getName(), department.getNumberOfMember()));
                memberRate.put(department.getAbbr(),
                        (double) department.getNumberOfMember());
                sum += department.getNumberOfMember();
            }

            for (String departmentName: memberRate.keySet()) {
                memberRate.put(departmentName, memberRate.get(departmentName) / sum);
            }
        }
    }

    private static void updateAwardList() {
        AwardDao awardDao = new AwardDao();
        List<Award> list = awardDao.searchAll();

        if (list.size() != 0) {
            for (Award award: list) {
                if (award.getRestNum() > 0) {
                    Random random = new Random();
                    MemberDao memberDao = new MemberDao();
                    List<Tuple<String, String>> people = new ArrayList<>();

                    int[] person = new int[2];
                    person[0] = award.getRestNumFor01();
                    person[1] = award.getRestNumFor02();

                    int persons = departmentInfo.get("01").getSecond() + departmentInfo.get("02").getSecond();

                    for (int i = 0; i < person[0] ;i++) {
                        int randomNum = random.nextInt(persons) + 1;

                        while (luckyMember.contains(randomNum)) {
                            randomNum = random.nextInt(persons) + 1;
                        }

                        String result = memberDao.returnStatus("01", randomNum, persons);

                        if (result == null) {
                            return ;
                        } else {
                            people.add(new Tuple<>(result, "01"));
                        }
                    }

                    for (int i = 0; i < person[1] ;i++) {
                        int randomNum = random.nextInt(persons) + 1;

                        while (luckyMember.contains(randomNum)) {
                            randomNum = random.nextInt(persons) + 1;
                        }

                        String result = memberDao.returnStatus("02", randomNum, persons);

                        if (result == null) {
                            return ;
                        } else {
                            people.add(new Tuple<>(result, "02"));
                        }
                    }

                    Collections.shuffle(people);
                    awardLuckyList.put(award.getAbbr(),
                            new Triplet<>(award.getName(),
                                    new Triplet<>(award.getRestNum(),
                                            award.getRestNumFor01(),
                                            award.getRestNumFor02()),
                                    people));
                } else {
                    // 在此奖无剩余人数时，按照规则写点基本信息进去，抽奖也是不可能成功抽这个奖的
                    awardLuckyList.put(award.getAbbr(),
                            new Triplet<>(award.getName(),
                                    new Triplet<>(0, 0, 0),
                                    null));
                }
            }
        }
    }

    public static void resetAllList() {
        // 刷新所有已有的变量
        departmentInfo.clear();
        memberRate.clear();
        awardLuckyList.clear();

        // 重新按照数据库的数据和定义的规则进行填充
        updateDepartmentInfoAndMember();
        updateAwardList();
    }
}
