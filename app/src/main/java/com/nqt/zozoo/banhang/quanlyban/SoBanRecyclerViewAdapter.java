package com.nqt.zozoo.banhang.quanlyban;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.banhang.BanHangSoBanFragment;
import com.nqt.zozoo.banhang.quanlyban.SoBanContent.SoBan;

import java.util.List;

/**
 * Created by USER on 12/3/2018.
 */

public class SoBanRecyclerViewAdapter extends RecyclerView.Adapter<SoBanRecyclerViewAdapter.ViewHolder> {

    private List<String> soBans;
    private Context context;
    private Display display;
    private BanHangSoBanFragment.OnListFragmentInteractionListener mListener;

    public SoBanRecyclerViewAdapter(List<String> items, BanHangSoBanFragment.OnListFragmentInteractionListener listener, Context mContext) {
        soBans = items;
        mListener = listener;
        context = mContext;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_so_ban, parent, false);
        return new ViewHolder(view);
    }

    private float dpToPx(int dp) {
        return (float) (dp * (1 / 160));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.txtThuTuBan.setText(String.valueOf(position));
        Log.d("BH", "width" + display.getWidth());
        Log.d("BH", "width" + display.getHeight());
        holder.txtThuTuBan.setBackgroundResource(R.drawable.ic_table_style_green);
        holder.txtThuTuBan.setWidth(display.getWidth() / 6);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onListFragmentInteraction(holder.soBan);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.parseInt(soBans.get(0));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtThuTuBan;
        private SoBan soBan;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtThuTuBan = itemView.findViewById(R.id.txt_thu_tu_ban);
        }
    }

}
