package com.nqt.zozoo.banhang;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.orderadapter.DanhSachMonAdapter;
import com.nqt.zozoo.adapter.orderadapter.NhomMonAnAdapter;
import com.nqt.zozoo.adapter.orderadapter.OnClickOrderFragment;
import com.nqt.zozoo.adapter.orderadapter.OrderListAdapter;
import com.nqt.zozoo.database.MonAnDatabase;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.database.OrderDatabase;
import com.nqt.zozoo.database.OrderListDatabase;
import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.NhomMonAn;
import com.nqt.zozoo.utils.Order;
import com.nqt.zozoo.utils.OrderList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/12/2018.
 */

public class OrderFragment extends Fragment implements OnClickOrderFragment, View.OnClickListener {
    private static final String TABLE_STATUS = "table_status";
    private static final String TABLE_NAME = "table_name";

    private boolean tableStatus;
    private String nameTable;

    private NhomMonAnDatabase nhomMonAnDatabase;
    private MonAnDatabase monAnDatabase;
    private OrderDatabase orderDatabase;
    private OrderListDatabase orderMonListDatabase;

    private List<NhomMonAn> nhomMonAnList;
    private List<MonAn> monAnList;
    private Order order;
    private List<OrderList> orderMonList;
    private static Context mContext;

    private Toolbar toolbar;
    private ImageView imgBack;
    private TextView txtTitle;
    private Button btnTatCa;
    private Button btnHuyBo;
    private Button btnLuu;
    private Button btnXoaHet;
    private RecyclerView rcvNhomMonAn;
    private RecyclerView rcvMonAn;
    private RecyclerView rcvOrder;

    private DanhSachMonAdapter danhSachMonAdapter;
    private OrderListAdapter orderListAdapter;

    public OrderFragment() {
    }

    public static OrderFragment newInstance(Context context, boolean tableStatus, String nameTable) {
        mContext = context;
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        args.putBoolean(TABLE_STATUS, tableStatus);
        args.putString(TABLE_NAME, nameTable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nhomMonAnDatabase = new NhomMonAnDatabase(mContext);
        nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();

        monAnDatabase = new MonAnDatabase(mContext);
        monAnList = monAnDatabase.getAllMonAn();

        orderMonListDatabase = new OrderListDatabase(mContext);

        orderMonList = new ArrayList<>();
        orderDatabase = new OrderDatabase(mContext);

        if (getArguments() != null) {
            tableStatus = getArguments().getBoolean(TABLE_STATUS);
            nameTable = getArguments().getString(TABLE_NAME);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        toolbar = view.findViewById(R.id.tlb_fragment_order);
        imgBack = view.findViewById(R.id.img_order_backstack);
        txtTitle = view.findViewById(R.id.txt_order_title);
        rcvNhomMonAn = view.findViewById(R.id.rcv_order_nhom_thuc_an);
        rcvMonAn = view.findViewById(R.id.rcv_order_mon_an);
        rcvOrder = view.findViewById(R.id.rcv_order_list);
        btnTatCa = view.findViewById(R.id.btn_order_tat_ca_mon);
        btnHuyBo = view.findViewById(R.id.btn_order_huy_bo);
        btnLuu = view.findViewById(R.id.btn_order_luu);


        Context context = view.getContext();
        final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("");
        txtTitle.setText("Order");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.onBackPressed();
            }
        });


        LinearLayoutManager layoutManagerMonAn = new LinearLayoutManager(context);
        rcvMonAn.setLayoutManager(layoutManagerMonAn);
        danhSachMonAdapter = new DanhSachMonAdapter(monAnList, this);
        rcvMonAn.setAdapter(danhSachMonAdapter);

        LinearLayoutManager layoutManagerNhomMon = new LinearLayoutManager(context);
        rcvNhomMonAn.setLayoutManager(layoutManagerNhomMon);
        rcvNhomMonAn.setAdapter(new NhomMonAnAdapter(nhomMonAnList, this));

        LinearLayoutManager layoutManagerOrder = new LinearLayoutManager(context);
        rcvOrder.setLayoutManager(layoutManagerOrder);

        if (tableStatus) {
            // khi bàn có table là true, bàn đang được sử dụng
            order = orderDatabase.getOrder(nameTable);
            orderMonList = orderMonListDatabase.getOrderListWithCodeOrder(order.getMaOrder());
            orderListAdapter = new OrderListAdapter(orderMonList);
            rcvOrder.setAdapter(orderListAdapter);

            for (OrderList orderList : orderMonList) {
                for (MonAn monAn : monAnList) {
                    if (monAn.getMaMonAn().equals(orderList.getMaMonAn())) {
                        monAnList.remove(monAn);
                    }
                }
            }

        } else {
//            order = Iterables.getLast(orderDatabase.getAllOrder(),null);
        }

        btnTatCa.setOnClickListener(this);
        btnHuyBo.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        return view;
    }

    @Override
    public void OnListenerClickMonAn(MonAn monAn, int position) {
        String maOrder = "";
        monAnList.remove(monAn);
        rcvMonAn.setAdapter(new DanhSachMonAdapter(monAnList, this));
        OrderList orderList = new OrderList();
        orderList.setMaMonAn(monAn.getMaMonAn());
        orderList.setTenMonAn(monAn.getTenMonAn());
        orderList.setGiaTien(String.valueOf(monAn.getDonGia()));
        if (order != null) {
            maOrder = "OD" + (Integer.parseInt(order.getId()) - 1500) + 1500 + 1;
        } else {
            maOrder = "OD" + 1500;
        }
        orderList.setMaOrder(maOrder);
        orderList.setSoLuong(1);
        orderMonList.add(orderList);
        orderListAdapter = new OrderListAdapter(orderMonList);
        rcvOrder.setAdapter(orderListAdapter);
    }

    @Override
    public void OnListenerClickNhomMonAn(final NhomMonAn nhomMonAn, int position) {
        List<MonAn> newMonAnList = removeItemDEFNhom(monAnList, nhomMonAn.getMaNhonMonAn());
        danhSachMonAdapter = new DanhSachMonAdapter(newMonAnList, this);
        rcvMonAn.setAdapter(danhSachMonAdapter);
    }

    @Override
    public void OnClickRemoveItem(MonAn monAn, int position) {

    }

    public List<MonAn> removeItemDEFNhom(List<MonAn> monAns, String manhom) {
        List<MonAn> newListMonAn = new ArrayList<>();
        for (int i = 0; i < monAns.size(); i++) {
            if (monAns.get(i).getNhomMonAn().equals(manhom)) {
                newListMonAn.add(monAns.get(i));
            }
        }
        return newListMonAn;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_tat_ca_mon:
                rcvMonAn.setAdapter(new DanhSachMonAdapter(monAnList, this));
                break;
            case R.id.btn_order_huy_bo:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }
}