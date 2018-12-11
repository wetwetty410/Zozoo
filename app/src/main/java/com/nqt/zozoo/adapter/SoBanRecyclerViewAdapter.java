package com.nqt.zozoo.adapter;

import android.content.Context;
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
import com.nqt.zozoo.utils.Ban;

/**
 * Created by USER on 12/3/2018.
 */

public class SoBanRecyclerViewAdapter extends RecyclerView.Adapter<SoBanRecyclerViewAdapter.ViewHolder> {

    private int soBans;
    private Context context;
    private Display display;
    private BanHangSoBanFragment.OnListFragmentInteractionListener mListener;

    public SoBanRecyclerViewAdapter(int items, BanHangSoBanFragment.OnListFragmentInteractionListener listener, Context mContext) {
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

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.txtThuTuBan.setText(String.valueOf(position + 1));
        Log.d("BH", "width" + display.getWidth());
        Log.d("BH", "height" + display.getHeight());
        holder.txtThuTuBan.setBackgroundResource(R.drawable.ic_table_style_green);
        holder.txtThuTuBan.setWidth((int) (display.getWidth() - ((display.getWidth() / (5 * 0.1) * 5)) / 6));
        holder.txtThuTuBan.setHeight((int) (display.getHeight() - ((display.getHeight() / (5 * 0.1) * 5)) / 6));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onListFragmentInteraction(holder.ban);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return soBans;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtThuTuBan;
        private Ban ban;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtThuTuBan = itemView.findViewById(R.id.txt_thu_tu_ban);
        }
    }

}
