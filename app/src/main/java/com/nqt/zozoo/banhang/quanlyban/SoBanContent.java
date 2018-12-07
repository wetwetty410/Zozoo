package com.nqt.zozoo.banhang.quanlyban;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nqt.zozoo.banhang.BanHangActivity;
import com.nqt.zozoo.banhang.BanHangSoBanFragment;
import com.nqt.zozoo.database.MyDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 12/3/2018.
 */

public class SoBanContent {
    public List<SoBan> soBans;
    public Map<String, SoBan> soBanMap;
    public int numItem;
    public MyDatabase myDatabase;

    public SoBanContent() {
    }

    public SoBanContent(Context context) {
        myDatabase = new MyDatabase(context);
        soBans = myDatabase.getAllDanhSachBan();
        numItem = soBans.size();
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
