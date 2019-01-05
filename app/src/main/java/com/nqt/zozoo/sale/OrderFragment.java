package com.nqt.zozoo.sale;

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
import com.nqt.zozoo.adapter.orderadapter.ListFoodAdapter;
import com.nqt.zozoo.adapter.orderadapter.ListGroupFoodAdapter;
import com.nqt.zozoo.adapter.orderadapter.OnClickOrder;
import com.nqt.zozoo.adapter.orderadapter.ListOrderFoodAdapter;
import com.nqt.zozoo.database.TableDatabase;
import com.nqt.zozoo.database.FoodDatabase;
import com.nqt.zozoo.database.FoodOrderDatabase;
import com.nqt.zozoo.database.GroupFoodDatabase;
import com.nqt.zozoo.database.OrderDatabase;
import com.nqt.zozoo.database.OrderListFoodDatabase;
import com.nqt.zozoo.database.FloorDatabase;
import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.Food;
import com.nqt.zozoo.utils.FoodOrder;
import com.nqt.zozoo.utils.GroupFood;
import com.nqt.zozoo.utils.Order;
import com.nqt.zozoo.utils.OrderListFood;
import com.nqt.zozoo.utils.Floor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 12/12/2018.
 */

public class OrderFragment extends Fragment implements OnClickOrder, View.OnClickListener {
    private static final String TABLE_STATUS = "table_status";
    private static final String TABLE_NAME = "table_name";

    private boolean tableStatus;
    private String nameTable;
    private HashMap<String, String> saveSoLuong;
    private String maOrder;
    private List<String> maDelete;
    private int tongTien;
    private int tongSoLuongMon;
    private int tongSoLuongDo;

    private Table ban;
    private Floor tang;

    private GroupFoodDatabase nhomMonAnDatabase;
    private FloorDatabase tangDatabase;
    private FoodDatabase monAnDatabase;
    private OrderDatabase orderDatabase;
    private OrderListFoodDatabase orderMonListDatabase;
    private TableDatabase banDatabase;
    private FoodOrderDatabase monOrderDatabase;

    private List<GroupFood> nhomMonAnList;
    private List<Table> banList;
    private List<Food> monAnList;
    private List<FoodOrder> monDaOrderList;
    private Order order;
    private List<OrderListFood> orderMonList;
    private List<OrderListFood> orderMonListOld;
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
    private TextView txtSoLuongMon;
    private TextView txtSoLuongDo;
    private TextView txtTongTien;


    private ListFoodAdapter danhSachMonAdapter;
    private ListOrderFoodAdapter orderListAdapter;

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


        nhomMonAnDatabase = new GroupFoodDatabase(mContext);
        nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();

        monAnDatabase = new FoodDatabase(mContext);
        monAnList = monAnDatabase.getAllMonAn();

        orderMonListDatabase = new OrderListFoodDatabase(mContext);

        orderMonList = new ArrayList<>();
        maDelete = new ArrayList<>();
        saveSoLuong = new HashMap<>();
        orderDatabase = new OrderDatabase(mContext);

        monOrderDatabase = new FoodOrderDatabase(mContext);

        tangDatabase = new FloorDatabase(mContext);
        banDatabase = new TableDatabase(mContext);


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
        txtSoLuongMon = view.findViewById(R.id.txt_order_so_luong_mon);
        txtSoLuongDo = view.findViewById(R.id.txt_order_so_luong_do);
        txtTongTien = view.findViewById(R.id.txt_order_tong_tien);

        Context context = view.getContext();
        final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("");
        txtTitle.setText("Order");

        LinearLayoutManager layoutManagerMonAn = new LinearLayoutManager(context);
        rcvMonAn.setLayoutManager(layoutManagerMonAn);
        danhSachMonAdapter = new ListFoodAdapter(monAnList, this);
        rcvMonAn.setAdapter(danhSachMonAdapter);

        LinearLayoutManager layoutManagerNhomMon = new LinearLayoutManager(context);
        rcvNhomMonAn.setLayoutManager(layoutManagerNhomMon);
        rcvNhomMonAn.setAdapter(new ListGroupFoodAdapter(nhomMonAnList, this));

        LinearLayoutManager layoutManagerOrder = new LinearLayoutManager(context);
        rcvOrder.setLayoutManager(layoutManagerOrder);

        ban = banDatabase.getBan(nameTable);
        tang = tangDatabase.getTang(ban.getMaTang());


        if (tableStatus) {


            // khi bàn có table là true, bàn đang được sử dụng
            order = orderDatabase.getOrder(nameTable);
            maOrder = order.getMaOrder();
            tongSoLuongDo = Integer.parseInt(order.getSoLuongDo());
            tongSoLuongMon = Integer.parseInt(order.getSoLuongMon());
            tongTien = Integer.parseInt(order.getTongTien());
            orderMonList = orderMonListDatabase.getOrderListWithCodeOrder(maOrder);

            for (OrderListFood list : orderMonList) {
                saveSoLuong.put(list.getMaMonAn(), String.valueOf(list.getSoLuong()));
            }

            orderListAdapter = new ListOrderFoodAdapter(orderMonList, this);
            rcvOrder.setAdapter(orderListAdapter);

        }
        List<Order> list = orderDatabase.getAllOrder();
        if (list == null || list.size() == 0) {
            maOrder = "OD" + 1500;
        } else if (order == null) {
            maOrder = "OD" + ((Iterables.getLast(orderDatabase.getAllOrder()).getMaOrder().substring(2) + 10));
        } else {
            maOrder = order.getMaOrder();
        }

        txtViTriBan.setText((ban.getTenBan() + " - " + tang.getTenTang()));

        willDataChange(tongSoLuongMon, tongSoLuongDo, tongTien);

        imgBack.setOnClickListener(this);
        btnTatCa.setOnClickListener(this);
        btnHuyBo.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        btnXoaHet.setOnClickListener(this);
        return view;
    }

    private void willDataChange(int tongSoLuongMon, int tongSoLuongDo, int tongTien) {
        txtSoLuongDo.setText(String.valueOf(tongSoLuongDo));
        txtSoLuongMon.setText(String.valueOf(tongSoLuongMon));
        txtTongTien.setText(regexCommafy(String.valueOf(tongTien)));
    }

    private static String regexCommafy(String inputNum) {
        String regex = "(\\d)(?=(\\d{3})+$)";
        String[] splittedNum = inputNum.split("\\.");
        if (splittedNum.length == 2) {
            return splittedNum[0].replaceAll(regex, "$1,") + "." + splittedNum[1];
        } else {
            return inputNum.replaceAll(regex, "$1,");
        }
    }

    @Override
    public void OnListenerClickMonAn(Food monAn, int position) {

        OrderListFood orderDanhSachMon = new OrderListFood();
        String maMonAn = monAn.getMaMonAn() + orderMonList.size();
        if (orderMonList.size() != 0) {
            OrderListFood ord = Iterables.getLast(orderMonList);
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
        orderListAdapter = new ListOrderFoodAdapter(orderMonList, this);
        rcvOrder.setAdapter(orderListAdapter);
        willDataChange(tongSoLuongMon = orderMonList.size(),
                tongSoLuongDo = tongSoLuongDo + orderDanhSachMon.getSoLuong(),
                tongTien = tongTien + Integer.parseInt(orderDanhSachMon.getGiaTien()));

    }

    @Override
    public void OnListenerClickNhomMonAn(final GroupFood nhomMonAn, int position) {
        List<Food> newMonAnList = removeItemDEFNhom(monAnList, nhomMonAn.getMaNhonMonAn());
        danhSachMonAdapter = new ListFoodAdapter(newMonAnList, this);
        rcvMonAn.setAdapter(danhSachMonAdapter);
    }

    @Override
    public void OnClickRemoveItem(OrderListFood orderDanhSachMon, int position) {
        maDelete.add(orderDanhSachMon.getMaMonAn());
        orderMonList.remove(orderDanhSachMon);
        orderListAdapter = new ListOrderFoodAdapter(orderMonList, this);
        rcvOrder.setAdapter(orderListAdapter);
        willDataChange(tongSoLuongMon = orderMonList.size(),
                tongSoLuongDo = tongSoLuongDo - orderDanhSachMon.getSoLuong(),
                tongTien = tongTien - Integer.parseInt(orderDanhSachMon.getGiaTien()));
    }

    @Override
    public void OnClickTangSoLuong(OrderListFood orderDanhSachMon, int position) {
        for (OrderListFood order : orderMonList) {
            if (order.equalsMaMonAn(orderDanhSachMon)) {
                int soLuong = orderDanhSachMon.getSoLuong();
                tongSoLuongDo = tongSoLuongDo + 1;
                tongTien = tongTien + (Integer.parseInt(orderDanhSachMon.getGiaTien()) / orderDanhSachMon.getSoLuong());
                order.setSoLuong(soLuong);
                order.setGiaTien(order.getGiaTien());
                willDataChange(tongSoLuongMon, tongSoLuongDo, tongTien);
            }
        }
    }

    @Override
    public void OnClickGiamSoLuong(OrderListFood orderDanhSachMon, int position) {
        for (OrderListFood order : orderMonList) {
            if (order.equalsMaMonAn(orderDanhSachMon)) {
                int soLuong = orderDanhSachMon.getSoLuong();
                tongSoLuongDo = tongSoLuongDo - 1;
                tongTien = tongTien - (Integer.parseInt(orderDanhSachMon.getGiaTien()) / orderDanhSachMon.getSoLuong());
                order.setSoLuong(soLuong);
                order.setGiaTien(order.getGiaTien());
                willDataChange(tongSoLuongMon, tongSoLuongDo, tongTien);
            }
        }
    }

    public List<Food> removeItemDEFNhom(List<Food> monAns, String manhom) {
        List<Food> newListMonAn = new ArrayList<>();
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
                rcvMonAn.setAdapter(new ListFoodAdapter(monAnList, this));
                break;
            case R.id.btn_order_huy_bo:
                getActivity().onBackPressed();
                break;
            case R.id.btn_order_xoa_het:
                orderMonList.clear();
                orderListAdapter = new ListOrderFoodAdapter(orderMonList, this);
                rcvOrder.setAdapter(orderListAdapter);
                break;
            case R.id.btn_order_luu:
                monDaOrderList = monOrderDatabase.getMonOrderWithMaBan(nameTable);
                orderMonListOld = orderMonListDatabase.getOrderListWithCodeOrder(maOrder);
                if (checkEqualsList(orderMonList, orderMonListOld)) {
                    Toast.makeText(getContext(), "Không có gì để lưu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (orderMonListOld.size() != 0) {
                    for (OrderListFood orderDanhSachMon : orderMonListOld) {
                        orderMonListDatabase.deleteOrderListWithMaMonAn(maOrder, orderDanhSachMon.getMaMonAn());
                    }
                }
                if (monDaOrderList.size() != 0) {
                    for (FoodOrder monOrder : monDaOrderList) {
                        monOrderDatabase.deleteMonOrder(nameTable, maOrder, monOrder.getMaMon());
                    }
                }
                if (orderMonList.size() != 0) {
                    doOnclickLuuButton();
                    for (OrderListFood monOrder : orderMonList) {
                        FoodOrder mon = new FoodOrder();
                        mon.setMaOrder(maOrder);
                        mon.setMaBan(nameTable);
                        mon.setMaMon(monOrder.getMaMonAn());
                        mon.setSoLuongOrder(String.valueOf(monOrder.getSoLuong()));
                        String viTriBan = ban.getTenBan() + " - " + tang.getTenTang();
                        mon.setTenBanOrder(viTriBan);
                        mon.setTenMonOrder(monOrder.getTenMonAn());
                        monOrderDatabase.addMonOrder(mon);
                        orderMonListDatabase.addOrderList(monOrder);
                    }
                    Toast.makeText(getContext(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
                    getActivity().recreate();
                    getActivity().onBackPressed();
                } else {
                    int statusTable = 0;
                    banDatabase.updateStatusBan(statusTable, nameTable);
                    Toast.makeText(getContext(), "Hủy đơn thành công!", Toast.LENGTH_SHORT).show();
                    getActivity().recreate();
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
            order.setSoLuongDo(String.valueOf(tongSoLuongDo));
            order.setSoLuongMon(String.valueOf(tongSoLuongMon));
            order.setTongTien(String.valueOf(tongTien));
            order.setTimeUpdate(String.valueOf(time));
            orderDatabase.updateOrder(order, Integer.parseInt(order.getId()));
        } else {
            Order order = new Order();
            order.setMaBan(nameTable);
            //TODO: Thêm người order
            order.setMaOrder(maOrder);
            order.setSoLuongDo(String.valueOf(tongSoLuongDo));
            order.setSoLuongMon(String.valueOf(tongSoLuongMon));
            order.setTongTien(String.valueOf(tongTien));
            long time = System.currentTimeMillis();
            order.setTimeCreate(String.valueOf(time));
            orderDatabase.addOrder(order);

            int statusTable = 1;
            banDatabase.updateStatusBan(statusTable, nameTable);
        }
    }

    private boolean checkEqualsList(List<OrderListFood> orderDanhSachMons, List<OrderListFood> orderDanhSachMonOld) {
        if (orderDanhSachMonOld.size() == 0 && orderDanhSachMons.size() != 0 ||
                orderDanhSachMonOld.size() != 0 && orderDanhSachMons.size() == 0 ||
                orderDanhSachMonOld.size() != orderDanhSachMons.size()) {
            return false;
        } else {
            for (int i = 0; i < orderDanhSachMonOld.size(); i++) {
                if (!orderDanhSachMonOld.get(i).equalsMonAn(orderDanhSachMons.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}