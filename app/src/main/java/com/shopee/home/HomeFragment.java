package com.shopee.home;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shopee.MainActivity;
import com.shopee.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private View view;
    private MainActivity mainActivity;
    private List<Integer> listPhoto;
    private Timer timer;
    private ImageView imgBarcode;

    ArrayList<Category> listCategories;
    ArrayList<TopSale> listTopSales;
    ArrayList<FlashSale> listFlashSales;
    ArrayList<Category> listDanhMuc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle_indicator);


        listPhoto = getListPhoto();
        BannerAdapter bnAdapter = new BannerAdapter(mainActivity, listPhoto);
        viewPager.setAdapter(bnAdapter);

        circleIndicator.setViewPager(viewPager);
        bnAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());


        final IntentIntegrator integrator = new IntentIntegrator(mainActivity);
        imgBarcode = view.findViewById(R.id.barcode);
        imgBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrator.initiateScan();
            }
        });


        autoSlideImage();

        initCategory();

        initTopSale();

        initFlashSale();

        initDanhMuc();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(mainActivity, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                try {
//                    Picasso.with(mainActivity).load(result.getContents()).into();
                    JSONObject jsonObject = new JSONObject(result.getContents());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private List<Integer> getListPhoto() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.banner1);
        list.add(R.drawable.banner2);
        list.add(R.drawable.banner3);
        list.add(R.drawable.banner4);
        list.add(R.drawable.banner5);
        list.add(R.drawable.banner6);
        list.add(R.drawable.banner7);
        list.add(R.drawable.banner8);
        list.add(R.drawable.banner9);
        return list;
    }

    private void autoSlideImage() {
        if (listPhoto == null || listPhoto.isEmpty() || viewPager == null) {
            return;
        }
        // Init Timer
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = listPhoto.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 400, 3000);
    }

    public void initCategory() {
        RecyclerView rcv_Category = view.findViewById(R.id.rcv_category);
        rcv_Category.setHasFixedSize(true);
        GridLayoutManager gird = new GridLayoutManager(mainActivity, 2, GridLayoutManager.HORIZONTAL, false);
        rcv_Category.setLayoutManager(gird);

        listCategories = new ArrayList<>();

        listCategories.add(new Category(R.drawable.ic_now, "NowFood - Bữa Ngon 0đ"));
        listCategories.add(new Category(R.drawable.ic_deals, "Deal Gần Bạn - Chỉ từ 1K"));
        listCategories.add(new Category(R.drawable.world, "Hàng Quốc Tế - Tri Ân"));
        listCategories.add(new Category(R.drawable.xtra, "Freeship Xtra"));
        listCategories.add(new Category(R.drawable.beauty, "Beauty Club - Mê Làm Đẹp"));
        listCategories.add(new Category(R.drawable.xu_xtra, "Hoàn Xu Đơn Bất Kỳ"));
        listCategories.add(new Category(R.drawable.diamond, "Shopee Premium"));
        listCategories.add(new Category(R.drawable.brand, "Brand Discount"));
        listCategories.add(new Category(R.drawable.mum, "Shopee Mum's Club"));
        listCategories.add(new Category(R.drawable.discount50, "Săn Siêu Sale"));
        listCategories.add(new Category(R.drawable.device, "Tech Zone - Siêu Thị Điện Thoại"));
        listCategories.add(new Category(R.drawable.book, "Book Club - Sách Sale Sốc"));
        listCategories.add(new Category(R.drawable.ic_smartphone, "Nạp Thẻ & Dịch Vụ"));
        listCategories.add(new Category(R.drawable.ic_mall, "Shopee Mall"));
        listCategories.add(new Category(R.drawable.ic_one, "Deal Sốc Từ 1K"));
        listCategories.add(new Category(R.drawable.ic__d, "Vận May 1Đ"));
        listCategories.add(new Category(R.drawable.ic_delivery, "Miễn Phí Vẫn Chuyển"));
        listCategories.add(new Category(R.drawable.ic_everyday, "Săn Xu Mỗi Ngày"));
        listCategories.add(new Category(R.drawable.ic_mart, "Shopee Mart"));
        listCategories.add(new Category(R.drawable.ic_delivery, "Mã Giảm Giá"));
        listCategories.add(new Category(R.drawable.ic_partner, "Ưu Đãi Đối Tác"));
        listCategories.add(new Category(R.drawable.ic_star, "KOL Club"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(listCategories, mainActivity, R.layout.item_categories);
        rcv_Category.setAdapter(categoryAdapter);
    }

    public void initTopSale() {
        RecyclerView rcv_topsale = view.findViewById(R.id.rcv_topsale);
        rcv_topsale.setHasFixedSize(true);
        GridLayoutManager gird = new GridLayoutManager(mainActivity, 1, GridLayoutManager.HORIZONTAL, false);
        rcv_topsale.setLayoutManager(gird);

        listTopSales = new ArrayList<>();

        listTopSales.add(new TopSale(R.drawable.product1, "đ 19.000"));
        listTopSales.add(new TopSale(R.drawable.product2, "đ 4.000"));
        listTopSales.add(new TopSale(R.drawable.product3, "đ 185.000"));
        listTopSales.add(new TopSale(R.drawable.product4, "đ 99.000"));
        listTopSales.add(new TopSale(R.drawable.product5, "đ 11.000"));
        listTopSales.add(new TopSale(R.drawable.product6, "đ 125.000"));

        TopSaleAdapter topSaleAdapter = new TopSaleAdapter(listTopSales, mainActivity, R.layout.item_topsale);
        rcv_topsale.setAdapter(topSaleAdapter);

    }

    private void initFlashSale() {
        RecyclerView rcv_flashsale = view.findViewById(R.id.rcv_flashsale);
        rcv_flashsale.setHasFixedSize(true);
        GridLayoutManager gird = new GridLayoutManager(mainActivity, 1, GridLayoutManager.HORIZONTAL, false);
        rcv_flashsale.setLayoutManager(gird);

        listFlashSales = new ArrayList<>();

        listFlashSales.add(new FlashSale(R.drawable.flashsale1, "đ 4.000", 2));
        listFlashSales.add(new FlashSale(R.drawable.flashsale2, "đ 185.000", 1));
        listFlashSales.add(new FlashSale(R.drawable.flashsale75, "đ 75.000", 3));
        listFlashSales.add(new FlashSale(R.drawable.flashsale179, "đ 179.000", 2));
        listFlashSales.add(new FlashSale(R.drawable.flashsale252, "đ 252.000", 4));
        listFlashSales.add(new FlashSale(R.drawable.flashsale455, "đ 455.000", 3));

        FlashSaleAdapter flashSaleAdapter = new FlashSaleAdapter(listFlashSales, mainActivity, R.layout.item_flashsale);
        rcv_flashsale.setAdapter(flashSaleAdapter);
    }

    public void initDanhMuc() {
        RecyclerView rcv_Category2 = view.findViewById(R.id.rcv_category2);
        rcv_Category2.setHasFixedSize(true);
        GridLayoutManager gird = new GridLayoutManager(mainActivity, 2, GridLayoutManager.HORIZONTAL, false);
        rcv_Category2.setLayoutManager(gird);

        listDanhMuc = new ArrayList<>();

        listDanhMuc.add(new Category(R.drawable.muc1, "Thời Trang Nam"));
        listDanhMuc.add(new Category(R.drawable.muc11, "Thời Trang Nữ"));
        listDanhMuc.add(new Category(R.drawable.muc2, "Điện Thoại & Phụ Kiện"));
        listDanhMuc.add(new Category(R.drawable.muc12, "Mẹ & Bé"));
        listDanhMuc.add(new Category(R.drawable.muc3, "Thiết Bị Điện Tử"));
        listDanhMuc.add(new Category(R.drawable.muc13, "Nhà Cửa & Đời Sống"));
        listDanhMuc.add(new Category(R.drawable.muc4, "Máy Tính & Laptop"));
        listDanhMuc.add(new Category(R.drawable.muc14, "Sức Khỏe & Sắc Đẹp"));
        listDanhMuc.add(new Category(R.drawable.muc5, "Máy Ảnh & Máy Quay Phim"));
        listDanhMuc.add(new Category(R.drawable.muc15, "Giày Dép Nữ"));
        listDanhMuc.add(new Category(R.drawable.muc6, "Đồng Hồ"));
        listDanhMuc.add(new Category(R.drawable.muc16, "Túi Ví"));
        listDanhMuc.add(new Category(R.drawable.muc7, "Giày Dép Nam"));
        listDanhMuc.add(new Category(R.drawable.muc17, "Phụ Kiện Thời Trang"));
        listDanhMuc.add(new Category(R.drawable.muc8, "Thiết Bị Điện Gia Dụng"));
        listDanhMuc.add(new Category(R.drawable.muc18, "Bách Hóa Online"));
        listDanhMuc.add(new Category(R.drawable.muc9, "Thể Thao & Du Lịch"));
        listDanhMuc.add(new Category(R.drawable.muc19, "Voucher & Dịch Vụ"));
        listDanhMuc.add(new Category(R.drawable.muc10, "Ô tô - Xe Máy - Xe Đạp"));
        listDanhMuc.add(new Category(R.drawable.muc20, "Nhà Sách Online"));

        CategoryAdapter mucAdapter = new CategoryAdapter(listDanhMuc, mainActivity, R.layout.item_muc);
        rcv_Category2.setAdapter(mucAdapter);
    }


}
