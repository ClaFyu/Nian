package com.zte.test;

import com.zte.dao.AwardDao;
import com.zte.dao.MemberDao;
import com.zte.model.Award;
import com.zte.model.FrontAllAwardInfoNow;
import com.zte.util.ConfigUtil;
import com.zte.util.common.Tuple;

import java.util.ArrayList;
import java.util.List;

public class DrawTest {
    public static void main(String[] args) {
//        MemberDao memberDao = new MemberDao();
//
//        System.out.println(memberDao.returnStatus("02", 3));

//        List<FrontAllAwardInfoNow> a = new ArrayList<>();
//        FrontAllAwardInfoNow frontAllAwardInfoNow = new FrontAllAwardInfoNow();

//        for (String abbr: ConfigUtil.awardLuckyList.keySet()) {
//            frontAllAwardInfoNow.setAbbr(abbr);
//            frontAllAwardInfoNow.setName(ConfigUtil.awardLuckyList.get(abbr).getFirst());
//            frontAllAwardInfoNow.setRestNum(ConfigUtil.awardLuckyList.get(abbr).getSecond().getFirst());
//            a.add(frontAllAwardInfoNow);
//        }

        ConfigUtil.resetAllList();

        for (String abbr: ConfigUtil.awardLuckyList.keySet()) {
            System.out.println("名称：" + ConfigUtil.awardLuckyList.get(abbr).getFirst());
            System.out.println("获奖人");
            for (Tuple<String, String> tuple: ConfigUtil.awardLuckyList.get(abbr).getThird()) {
                System.out.println(tuple.getFirst() + "\t" + tuple.getSecond());
            }
            System.out.println("\n");
            System.out.println(ConfigUtil.luckyMember.size());
        }
    }
}
