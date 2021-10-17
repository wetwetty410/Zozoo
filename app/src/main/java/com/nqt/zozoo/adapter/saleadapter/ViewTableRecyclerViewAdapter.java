package com.nqt.zozoo.adapter.saleadapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.addflooradapter.CallBackManagerFloor;
import com.nqt.zozoo.sale.OrderCallBack;
import com.nqt.zozoo.sale.SaleActivity;
import com.nqt.zozoo.sale.OrderFragment;
import com.nqt.zozoo.utils.Table;

import java.util.List;

import static com.nqt.zozoo.sale.LocationTableFragment.OnListFragmentInteractionListener;

/**
 * Created by USER on 12/3/2018.
 */

public class ViewTableRecyclerViewAdapter extends RecyclerView.Adapter<ViewTableRecyclerViewAdapter.ViewHolder> implements OrderCallBack {

    private static final String TAGE = "SoBanAdapter";
    private List<Table> banList;
    private Context context;
    private int widthScreen;
    private int heightScreen;
    private boolean mIsThemBan;
    private Display display;
    private OnListFragmentInteractionListener mListener;
    private CallBackManagerFloor mThemListener;
    private String nameTable;

    public ViewTableRecyclerViewAdapter(List<Table> bans, OnListFragmentInteractionListener listener, Context mContext) {
        banList = bans;
        mListener = listener;
        context = mContext;
        widthScreen = Resources.getSystem().getDisplayMetrics().widthPixels;
        heightScreen = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public ViewTableRecyclerViewAdapter(List<Table> bans, CallBackManagerFloor listener, Context mContext, boolean isThemBan) {
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
                .inflate(R.layout.item_sales_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        nameTable = banList.get(position).getTenBan();
        holder.txtThuTuBan.setText(nameTable);
        final boolean statusBan = banList.get(position).getStatusBan() == 1;
        if (mIsThemBan) {
            holder.txtThuTuBan.setBackgroundResource(R.drawable.ic_table_free);
        } else if (statusBan) {
            holder.txtThuTuBan.setBackgroundResource(R.drawable.ic_table_busy);
        } else {
            holder.txtThuTuBan.setBackgroundResource(R.drawable.ic_table_free);

        }
        int indexWidth = (int) (convertDpToPixel(widthScreen, context) - (convertDpToPixel(5, context) * 6));
        holder.txtThuTuBan.setWidth(indexWidth / 4);
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


    @Override
    public int getItemCount() {
        return banList.size();
    }

    @Override
    public void onTableStatusChange(String nametable) {
        for (int i = 0; i < banList.size(); i++) {
            Table table = banList.get(i);
            if (table.getTenBan().equals(nametable)) {
                notifyItemChanged(i);
            }
        }
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
                        FragmentTransaction transaction = ((SaleActivity) context).getSupportFragmentManager().beginTransaction();
                        transaction.replace(android.R.id.content, OrderFragment.newInstance(statusBan, nameBan));
                        transaction.addToBackStack(null);
                        transaction.commit();
                        Log.d(TAGE, "onClick:OrderFragMent ");
                    }
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mIsThemBan) {
                        openMenu(v, banList.get(getAdapterPosition()), getAdapterPosition());
                    }
                    return false;
                }
            });
        }

        private void openMenu(View view, final Table ban, final int position) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.menu_manager_edit_table, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_doi_ten_ban:
                            mThemListener.OnClickDoiTenBan(ban, position);
                            break;
                        case R.id.menu_xoa_ban:
                            mThemListener.OnClickXoaBan(ban, position);
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    }
}