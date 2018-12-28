package com.nqt.zozoo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.thembanadapter.OnClickThemBanFragment;
import com.nqt.zozoo.banhang.BanHangActivity;
import com.nqt.zozoo.banhang.OrderFragment;
import com.nqt.zozoo.utils.Ban;

import java.util.List;

import static com.nqt.zozoo.banhang.BanHangSoBanFragment.OnListFragmentInteractionListener;

/**
 * Created by USER on 12/3/2018.
 */

public class SoBanRecyclerViewAdapter extends RecyclerView.Adapter<SoBanRecyclerViewAdapter.ViewHolder> {

    private static final String TAGE = "SoBanAdapter";
    private List<Ban> banList;
    private Context context;
    private int widthScreen;
    private int heightScreen;
    private boolean mIsThemBan;
    private Display display;
    private OnListFragmentInteractionListener mListener;
    private OnClickThemBanFragment mThemListener;

    public SoBanRecyclerViewAdapter(List<Ban> bans, OnListFragmentInteractionListener listener, Context mContext) {
        banList = bans;
        mListener = listener;
        context = mContext;
        widthScreen = Resources.getSystem().getDisplayMetrics().widthPixels;
        heightScreen = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public SoBanRecyclerViewAdapter(List<Ban> bans, OnClickThemBanFragment listener, Context mContext, boolean isThemBan) {
        banList = bans;
        mThemListener = listener;
        context = mContext;
        mIsThemBan = isThemBan;
        widthScreen = Resources.getSystem().getDisplayMetrics().widthPixels;
        heightScreen = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_so_ban, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtThuTuBan.setText(String.valueOf(position + 1));
        final boolean statusBan = banList.get(position).getStatusBan() == 1;
        if (statusBan) {
            holder.txtThuTuBan.setBackgroundResource(R.drawable.ic_table_style_red);
        } else {
            holder.txtThuTuBan.setBackgroundResource(R.drawable.ic_table_style_green);

        }
        int indexWidth = (int) (convertDpToPixel(widthScreen, context) - (convertDpToPixel(5, context) * 6));
        holder.txtThuTuBan.setWidth(indexWidth / 4);
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


    @Override
    public int getItemCount() {
        return banList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtThuTuBan;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtThuTuBan = itemView.findViewById(R.id.txt_thu_tu_ban);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mIsThemBan) {
                        final boolean statusBan = banList.get(getAdapterPosition()).getStatusBan() == 1;
                        String nameBan = banList.get(getAdapterPosition()).getMaBan();
                        FragmentTransaction transaction = ((BanHangActivity) context).getSupportFragmentManager().beginTransaction();
                        transaction.add(android.R.id.content, OrderFragment.newInstance(context, statusBan, nameBan));
                        transaction.addToBackStack(null);
                        transaction.commit();
                        Log.d(TAGE, "onClick:OrderFragMent ");
                    }
                }
            });
        }
    }
}