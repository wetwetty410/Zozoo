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
import android.widget.Toast;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.orderadapter.DanhSachMonAdapter;
import com.nqt.zozoo.adapter.orderadapter.NhomMonAnAdapter;
import com.nqt.zozoo.adapter.orderadapter.OnClickOrderFragment;
import com.nqt.zozoo.adapter.orderadapter.OrderListAdapter;
import com.nqt.zozoo.database.BanDatabase;
import com.nqt.zozoo.database.MonAnDatabase;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.database.OrderDatabase;
import com.nqt.zozoo.database.OrderListDatabase;
import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.NhomMonAn;
import com.nqt.zozoo.utils.Order;
import com.nqt.zozoo.utils.OrderList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 12/12/2018.
 */

public class OrderFragment extends Fragment implements OnClickOrderFragment, View.OnClickListener {
    private static final String TABLE_STATUS = "table_status";
    private static final String TABLE_NAME = "table_name";

    private boolean tableStatus;
    private String nameTable;
    private HashMap<String, String> saveSoLuong;
    private String maOrder;

    private NhomMonAnDatabase nhomMonAnDatabase;
    private MonAnDatabase monAnDatabase;
    private OrderDatabase orderDatabase;
    private OrderListDatabase orderMonListDatabase;
    private BanDatabase banDatabase;

    private List<NhomMonAn> nhomMonAnList;
    private List<MonAn> monAnList;
    private Order order;
    private List<OrderList> orderMonList;
    private List<OrderList> orderMonListOld;
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
        orderMonListOld = orderMonListDatabase.getAllOrderList();

        orderMonList = new ArrayList<>();
        saveSoLuong = new HashMap<>();
        orderDatabase = new OrderDatabase(mContext);

        banDatabase = new BanDatabase(mContext);


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
        btnXoaHet = view.findViewById(R.id.btn_order_xoa_het);

        Context context = view.getContext();
        final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("");
        txtTitle.setText("Order");

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
            maOrder = order.getMaOrder();

            orderMonList = orderMonListDatabase.getOrderListWithCodeOrder(maOrder);

            for (OrderList list : orderMonList)
                saveSoLuong.put(list.getMaMonAn(), String.valueOf(list.getSoLuong()));

            orderListAdapter = new OrderListAdapter(orderMonList, this);
            rcvOrder.setAdapter(orderListAdapter);

        } else {
//            order = Iterables.getLast(orderDatabase.getAllOrder(),null);
        }
        List<Order> list = orderDatabase.getAllOrder();
        if (list == null || list.size() == 0) {
            maOrder = "OD" + 1500;
        }
        imgBack.setOnClickListener(this);
        btnTatCa.setOnClickListener(this);
        btnHuyBo.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        btnXoaHet.setOnClickListener(this);
        return view;
    }

    @Override
    public void OnListenerClickMonAn(MonAn monAn, int position) {
        try {
            OrderList orderList = new OrderList();
            String maMonAn = monAn.getMaMonAn() + orderMonList.size();
            orderList.setMaMonAn(monAn.getMaMonAn() + orderMonList.size());
            orderList.setTenMonAn(monAn.getTenMonAn());
            orderList.setGiaTien(String.valueOf(monAn.getDonGia()));
            orderList.setMaOrder(maOrder);

            if (!saveSoLuong.containsKey(maMonAn)) {
                saveSoLuong.put(maMonAn, "1");
            }
            orderList.setSoLuong(Integer.parseInt(saveSoLuong.get(maMonAn)));
            orderMonList.add(orderList);
            orderListAdapter = new OrderListAdapter(orderMonList, this);
            rcvOrder.setAdapter(orderListAdapter);
        } catch (Exception e) {
            Toast.makeText(getContext(), "FAIL", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void OnListenerClickNhomMonAn(final NhomMonAn nhomMonAn, int position) {
        List<MonAn> newMonAnList = removeItemDEFNhom(monAnList, nhomMonAn.getMaNhonMonAn());
        danhSachMonAdapter = new DanhSachMonAdapter(newMonAnList, this);
        rcvMonAn.setAdapter(danhSachMonAdapter);
    }

    @Override
    public void OnClickRemoveItem(OrderList orderList, int position) {
        orderMonList.remove(orderList);
        orderListAdapter = new OrderListAdapter(orderMonList, this);
        rcvOrder.setAdapter(orderListAdapter);
    }

    @Override
    public void OnClickTangSoLuong(OrderList orderList, int position) {
        for (OrderList order : orderMonList) {
            if (order.equalsMaMonAn(orderList)) {
                order.setSoLuong(orderList.getSoLuong());
            }
        }
    }

    @Override
    public void OnClickGiamSoLuong(OrderList orderList, int position) {
        for (OrderList order : orderMonList) {
            if (order.equalsMaMonAn(orderList)) {
                order.setSoLuong(orderList.getSoLuong());
            }
        }
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
            case R.id.btn_order_xoa_het:
                orderMonList.clear();
                orderListAdapter = new OrderListAdapter(orderMonList, this);
                rcvOrder.setAdapter(orderListAdapter);
                break;
            case R.id.btn_order_luu:
                doOnclickLuuButton();
                break;
            case R.id.img_order_backstack:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }

    private void doOnclickLuuButton() {
        if (tableStatus) {
            // TODO:Update Người Order
            long time = System.currentTimeMillis();
            order.setTimeUpdate(String.valueOf(time));
            orderDatabase.updateOrder(order, Integer.parseInt(order.getId()));
            for (OrderList list : orderMonList) {
                orderMonListDatabase.updateOrderList(list, list.getMaMonAn());
            }
        } else {
            Order order = new Order();
            order.setMaBan(nameTable);
            //TODO: Thêm người order
            order.setMaOrder(maOrder);
            long time = System.currentTimeMillis();
            order.setTimeCreate(String.valueOf(time));
            orderDatabase.addOrder(order);

            int statusTable = 1;
            banDatabase.updateStatusBan(statusTable, nameTable);

            for (OrderList list : orderMonList) {
                orderMonListDatabase.updateOrderList(list, list.getMaMonAn());
            }
        }
        Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_LONG).show();
        getActivity().recreate();
        getActivity().onBackPressed();
    }
}