package com.nqt.zozoo.adapter.saleadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.sale.LocationTableFragment.OnListFragmentInteractionListener;
import com.nqt.zozoo.database.TableDatabase;
import com.nqt.zozoo.database.FloorDatabase;
import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.Floor;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class LocationTableRecyclerViewAdapter extends RecyclerView.Adapter<LocationTableRecyclerViewAdapter.ViewHolder> {

    private ViewTableRecyclerViewAdapter soBanRecyclerViewAdapter;
    private Context context;
    private TableDatabase banDatabase;
    private FloorDatabase tangDatabase;
    private List<Table> banList;
    private List<Floor> tangList;
    private final OnListFragmentInteractionListener mListener;

    public LocationTableRecyclerViewAdapter(OnListFragmentInteractionListener listener, Context mContext) {
        mListener = listener;
        context = mContext;

        banDatabase = new TableDatabase(context);
        tangDatabase = new FloorDatabase(context);
        tangList = tangDatabase.getAllTang();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ban_hang_so_ban, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.itemView;
        holder.txtTiteSoBan.setText(tangList.get(position).getTenTang());
        String maTang = tangList.get(position).getMaTang();

        // Lấy thông tin số bàn trong một tầng từ CSDL
        banList = banDatabase.getSoBan(maTang);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        holder.recyclerView.setLayoutManager(gridLayoutManager);

        //       Gắn item cho RecycleView bằng các adapter
        soBanRecyclerViewAdapter = new ViewTableRecyclerViewAdapter(banList, mListener, context);
        holder.recyclerView.setAdapter(soBanRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return tangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView txtTiteSoBan;
        public RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            itemView = view;
            txtTiteSoBan = view.findViewById(R.id.txt_title_so_ban);
            recyclerView = view.findViewById(R.id.danh_sach_ban_item_list);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
