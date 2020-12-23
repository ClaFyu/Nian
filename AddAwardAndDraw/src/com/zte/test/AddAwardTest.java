package com.zte.test;

import com.zte.dao.AwardDao;
import com.zte.model.Award;

public class AddAwardTest {
    public static void main(String[] args) {
        AwardDao awardDao = new AwardDao();
        Award award = new Award("00", "一等奖", "", "", 200, 20, 20, 10, 10);

        System.out.println(awardDao.addAward(award));
    }
}
