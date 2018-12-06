package com.nqt.zozoo.banhang.quanlyban;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 12/3/2018.
 */

public class SoBanContent {
    public static List<SoBan> soBans = new ArrayList<>();
    public static Map<String, SoBan> soBanMap = new HashMap<>();
    public static int numItem = 10;

    static {
        for (int i = 1; i <= numItem; i++) {
            addItem(creatSoBan(i));
        }
    }

    private static void addItem(SoBan soBan) {
        soBans.add(soBan);
        soBanMap.put(soBan.id, soBan);
    }

    private static SoBan creatSoBan(int position) {
        return new SoBan(String.valueOf(position), "z" + position, "10");
    }

    public static class SoBan {
        public String id;
        public String tenBan;
        public String soBan;

        public SoBan(String id, String tenBan, String soBan) {
            this.id = id;
            this.tenBan = tenBan;
            this.soBan = soBan;
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTenBan() {
            return tenBan;
        }

        public void setTenBan(String tenBan) {
            this.tenBan = tenBan;
        }

        public String getSoBan() {
            return soBan;
        }

        public void setSoBan(String soBan) {
            this.soBan = soBan;
        }
    }
}
