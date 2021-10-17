package com.nqt.zozoo.sale;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.salesfoodscookingadapter.SalesTableCookingAdapter;
import com.nqt.zozoo.adapter.salesfoodscookingadapter.OnClickOrderViewFood;
import com.nqt.zozoo.database.FoodOrderDatabase;
import com.nqt.zozoo.utils.Food;

import java.util.List;

public class ViewOrderFoodFragment extends Fragment {
    private static final String PAGE = "page";
    private static final String TITLE = "title";
    private OnClickOrderViewFood mListener;
    private RecyclerView rcvMonAn;
    private FoodOrderDatabase monOrderDatabase;
    private List<String> monOrderList;

    public static ViewOrderFoodFragment newInstance(int page, String title) {
        Bundle args = new Bundle();
        ViewOrderFoodFragment fragment = new ViewOrderFoodFragment();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monOrderDatabase = new FoodOrderDatabase(getContext());
        monOrderList = monOrderDatabase.getBanOrder();
        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales_table_cooking, container, false);
        rcvMonAn = view.findViewById(R.id.rcv_ban_order_mon);
        Context context = view.getContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rcvMonAn.setLayoutManager(layoutManager);
        rcvMonAn.setAdapter(new SalesTableCookingAdapter(monOrderList, mListener,context));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LocationTableFragment.OnListFragmentInteractionListener) {
        //    mListener = (OnClickMonOrderFragment) context;
        } else {
            throw new RuntimeException(context.toString());
        }

    }
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Food monAn);
    }
}
