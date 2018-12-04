package com.nqt.zozoo.dummy;

import android.app.Fragment;
import android.view.View;

import com.nqt.zozoo.banhang.quanlyban.SoBanFragment;

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

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(BanHangItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static BanHangItem createDummyItem(int position) {
        return new BanHangItem(String.valueOf(position));
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
        public Fragment fragment;

        public BanHangItem(String id) {
            this.id = id;
        }

    }
}
