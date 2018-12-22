package com.nqt.zozoo.adapter;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nqt.zozoo.R;
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
    private int soBans;
    private List<Ban> banList;
    private Context context;
    private int widthScreen;
    private int heightScreen;
    private Display display;
    private OnListFragmentInteractionListener mListener;

    public SoBanRecyclerViewAdapter(List<Ban> banList, int items, OnListFragmentInteractionListener listener, Context mContext) {
        soBans = items;
        mListener = listener;
        context = mContext;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        widthScreen = display.getWidth();
        heightScreen = display.getHeight();
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
        holder.txtThuTuBan.setBackgroundResource(R.drawable.ic_table_style_green);
        int indexWidth = (int) (widthScreen * (6 / 5.5)) - (int) widthScreen / 6;
        int indexHeight = (int) (heightScreen * (6 / 5.5)) - (int) heightScreen / 6;
        holder.txtThuTuBan.setWidth(indexWidth / 6);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int marginWidth = (widthScreen - indexWidth) / 10;
        int marginHeight = (heightScreen - indexHeight) / 20;
        layoutParams.setMargins(marginWidth, marginHeight, marginWidth, marginHeight);
        holder.txtThuTuBan.setLayoutParams(layoutParams);
        try {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction = ((BanHangActivity) context).getSupportFragmentManager().beginTransaction();
                    transaction.add(OrderFragment.newInstance(context,true,"zz" ), "Order");
                    transaction.addToBackStack(null);
                    transaction.commit();
                    Log.d(TAGE, "onClick:OrderFragMent ");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

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
