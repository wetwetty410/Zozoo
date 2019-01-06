package com.nqt.zozoo.adapter.addfoodadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.database.GroupFoodDatabase;
import com.nqt.zozoo.utils.GroupFood;

import java.util.List;

public class ViewGroupFoodAdapter extends RecyclerView.Adapter<ViewGroupFoodAdapter.ViewHolder> {
    private List<GroupFood> groupFood;
    private GroupFoodDatabase groupFoodDatabase;
    private Context context;
    private OnClickAddFoodListener onClickAddFoodListener;

    public ViewGroupFoodAdapter(OnClickAddFoodListener onClickAddFoodListener, Context context) {
        this.context = context;
        this.onClickAddFoodListener = onClickAddFoodListener;
        groupFoodDatabase = new GroupFoodDatabase(context);
        groupFood = groupFoodDatabase.getAllNhomMonAn();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtNameGroup.setText(groupFood.get(position).getTenNhonMonAn());
    }


    @Override
    public int getItemCount() {
        return groupFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtNameGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtNameGroup = itemView.findViewById(R.id.txt_group);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickAddFoodListener.OnClickGroupListener(groupFood.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
