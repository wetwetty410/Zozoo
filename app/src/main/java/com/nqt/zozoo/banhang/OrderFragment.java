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

import com.google.common.collect.Iterables;
import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.orderadapter.DanhSachMonAdapter;
import com.nqt.zozoo.adapter.orderadapter.NhomMonAnAdapter;
import com.nqt.zozoo.adapter.orderadapter.OnClickOrderFragment;
import com.nqt.zozoo.adapter.orderadapter.OrderListAdapter;
import com.nqt.zozoo.database.BanDatabase;
import com.nqt.zozoo.database.MonAnDatabase;
import com.nqt.zozoo.database.MonOrderDatabase;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.database.OrderDatabase;
import com.nqt.zozoo.database.OrderListDatabase;
import com.nqt.zozoo.database.TangDatabase;
import com.nqt.zozoo.utils.Ban;
import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.MonOrder;
import com.nqt.zozoo.utils.NhomMonAn;
import com.nqt.zozoo.utils.Order;
import com.nqt.zozoo.utils.OrderDanhSachMon;
import com.nqt.zozoo.utils.Tang;

import java.util.ArrayList;
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
    private List<String> maDelete;

    private NhomMonAnDatabase nhomMonAnDatabase;
    private TangDatabase tangDatabase;
    private MonAnDatabase monAnDatabase;
    private OrderDatabase orderDatabase;
    private OrderListDatabase orderMonListDatabase;
    private BanDatabase banDatabase;
    private MonOrderDatabase monOrderDatabase;

    private List<NhomMonAn> nhomMonAnList;
    private List<Ban> banList;
    private List<MonAn> monAnList;
    private List<MonOrder> monDaOrderList;
    private Order order;
    private List<OrderDanhSachMon> orderMonList;
    private List<OrderDanhSachMon> orderMonListOld;
    private static Context mContext;

    private Toolbar toolbar;
    private ImageView imgBack;
    private TextView txtTitle;
    private Button btnTatCa;
    private Button btnHuyBo;
    private Button btnLuu;
    private Button btnXoaHet;
    private TextView txtViTriBan;
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
        maDelete = new ArrayList<>();
        saveSoLuong = new HashMap<>();
        orderDatabase = new OrderDatabase(mContext);

        monOrderDatabase = new MonOrderDatabase(mContext);
        monDaOrderList = monOrderDatabase.getAllMonOrder();

        tangDatabase = new TangDatabase(mContext);
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
        txtViTriBan = view.findViewById(R.id.txt_order_vi_tri_ban);

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

            for (OrderDanhSachMon list : orderMonList)
                saveSoLuong.put(list.getMaMonAn(), String.valueOf(list.getSoLuong()));

            orderListAdapter = new OrderListAdapter(orderMonList, this);
            rcvOrder.setAdapter(orderListAdapter);

        }
        List<Order> list = orderDatabase.getAllOrder();
        if (list == null || list.size() == 0) {
            maOrder = "OD" + 1500;
        } else {
            maOrder = "OD" + ((Iterables.getLast(orderDatabase.getAllOrder()).getMaOrder().substring(2)) + 10);
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

        OrderDanhSachMon orderDanhSachMon = new OrderDanhSachMon();
        String maMonAn = monAn.getMaMonAn() + orderMonList.size();
        if (orderMonList.size() != 0) {
            OrderDanhSachMon ord = Iterables.getLast(orderMonList);
            if (ord != null) {
                orderDanhSachMon.setMaMonAn(monAn.getMaMonAn().substring(0, 2) + (Integer.parseInt(ord.getMaMonAn().substring(2)) + 10));
            }
        } else {
            orderDanhSachMon.setMaMonAn(maMonAn);
        }
        orderDanhSachMon.setTenMonAn(monAn.getTenMonAn());
        orderDanhSachMon.setGiaTien(String.valueOf(monAn.getDonGia()));
        orderDanhSachMon.setMaOrder(maOrder);

        if (!saveSoLuong.containsKey(maMonAn)) {
            saveSoLuong.put(maMonAn, "1");
        }
        orderDanhSachMon.setSoLuong(Integer.parseInt(saveSoLuong.get(maMonAn)));
        orderMonList.add(orderDanhSachMon);
        orderListAdapter = new OrderListAdapter(orderMonList, this);
        rcvOrder.setAdapter(orderListAdapter);

    }

    @Override
    public void OnListenerClickNhomMonAn(final NhomMonAn nhomMonAn, int position) {
        List<MonAn> newMonAnList = removeItemDEFNhom(monAnList, nhomMonAn.getMaNhonMonAn());
        danhSachMonAdapter = new DanhSachMonAdapter(newMonAnList, this);
        rcvMonAn.setAdapter(danhSachMonAdapter);
    }

    @Override
    public void OnClickRemoveItem(OrderDanhSachMon orderDanhSachMon, int position) {
        maDelete.add(orderDanhSachMon.getMaMonAn());
        orderMonList.remove(orderDanhSachMon);
        orderListAdapter = new OrderListAdapter(orderMonList, this);
        rcvOrder.setAdapter(orderListAdapter);
    }

    @Override
    public void OnClickTangSoLuong(OrderDanhSachMon orderDanhSachMon, int position) {
        for (OrderDanhSachMon order : orderMonList) {
            if (order.equalsMaMonAn(orderDanhSachMon)) {
                int soLuong = orderDanhSachMon.getSoLuong();
                order.setSoLuong(soLuong);
                order.setGiaTien(order.getGiaTien());
            }
        }
    }

    @Override
    public void OnClickGiamSoLuong(OrderDanhSachMon orderDanhSachMon, int position) {
        for (OrderDanhSachMon order : orderMonList) {
            if (order.equalsMaMonAn(orderDanhSachMon)) {
                int soLuong = orderDanhSachMon.getSoLuong();
                order.setSoLuong(soLuong);
                order.setGiaTien(order.getGiaTien());
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
                orderMonListOld=orderMonListDatabase.getAllOrderList();
                if (checkEqualsList(orderMonList, orderMonListOld)) {
                    Toast.makeText(getContext(), "Không có gì để lưu", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (orderMonListOld.size() != 0) {
                    for (OrderDanhSachMon orderDanhSachMon : orderMonListOld) {
                        orderMonListDatabase.deleteOrderList(maOrder);
                    }
                    for (MonOrder monOrder : monDaOrderList) {
                        monOrderDatabase.deleteMonOrder(nameTable, maOrder, monOrder.getMaMon());
                    }
                }


                if (orderMonList.size() != 0) {
                    doOnclickLuuButton();
                    for (OrderDanhSachMon monOrder : orderMonList) {
                        MonOrder mon = new MonOrder();
                        mon.setMaOrder(maOrder);
                        mon.setMaBan(nameTable);
                        mon.setMaMon(monOrder.getMaMonAn());
                        mon.setSoLuongOrder(String.valueOf(monOrder.getSoLuong()));
                        Ban ban = banDatabase.getBan(nameTable);
                        Tang tang = tangDatabase.getTang(ban.getMaTang());
                        String viTriBan = ban.getTenBan() + " - " + tang.getTenTang();
                        mon.setTenBanOrder(viTriBan);
                        mon.setTenMonOrder(monOrder.getTenMonAn());
                        monOrderDatabase.addMonOrder(mon);
                        orderMonListDatabase.addOrderList(monOrder);
                    }
                    getActivity().recreate();
                    Toast.makeText(getContext(), "Lưu thành công!", Toast.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                } else {
                    getActivity().recreate();
                    Toast.makeText(getContext(), "Hủy đơn thành công!", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                }


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
        }
    }

    private boolean checkEqualsList(List<OrderDanhSachMon> orderDanhSachMons, List<OrderDanhSachMon> orderDanhSachMonOld) {
        for (OrderDanhSachMon orderOld : orderDanhSachMonOld) {
            for (OrderDanhSachMon order : orderDanhSachMons) {
                if (!order.getMaMonAn().equals(orderOld.getMaMonAn())) {
                    return true;
                }
            }
        }
        return false;
    }
}