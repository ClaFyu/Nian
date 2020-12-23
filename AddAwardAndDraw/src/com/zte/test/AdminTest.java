package com.zte.test;

import com.zte.dao.AdminDao;
import com.zte.model.Admin;

public class AdminTest {
    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
        Admin admin = new Admin("123456", "3454a337e455ed96aaffb3b81a7a27f4509c08de");

        System.out.println(adminDao.comparePassword(admin));
    }
}
