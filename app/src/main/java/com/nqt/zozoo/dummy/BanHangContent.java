package com.nqt.zozoo.dummy;

import android.app.Fragment;
import android.view.View;

import com.nqt.zozoo.banhang.BanHangActivity;
import com.nqt.zozoo.banhang.BanHangPagerAdapter;
import com.nqt.zozoo.banhang.quanlyban.SoBanContent;
import com.nqt.zozoo.banhang.quanlyban.SoBanContent.SoBan;
import com.nqt.zozoo.banhang.quanlyban.SoBanFragment;
import com.nqt.zozoo.database.MyDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class BanHangContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<BanHangItem> ITEMS = new ArrayList<BanHangItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, BanHangItem> ITEM_MAP = new HashMap<String, BanHangItem>();

    static {
        // Add some sample items.
        MyDatabase myDatabase = new MyDatabase(new BanHangActivity().getApplicationContext());
        List<SoBan> soBans = myDatabase.getAllDanhSachBan();
        for (int i = 1; i <= soBans.size(); i++) {
            addItem(createBanHangItem(i, soBans.get(i).getTenBan(), Integer.parseInt(soBans.get(i).getSoBan())));
        }
    }

    private static void addItem(BanHangItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static BanHangItem createBanHangItem(int position, String tenBan, int soBan) {
        return new BanHangItem(String.valueOf(position), tenBan, SoBanFragment.newInstance(soBan));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class BanHangItem {
        public String id;
        public String tenBan;
        public SoBanFragment fragment;

        public BanHangItem(String id, String tenBan, SoBanFragment fragment) {
            this.id = id;
            this.tenBan = tenBan;
            this.fragment = fragment;
        }

    }
}
