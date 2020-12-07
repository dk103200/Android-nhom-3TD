package com.shopee.live;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.shopee.MainActivity;
import com.shopee.R;
import com.shopee.home.BannerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class LiveFragment extends Fragment {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private BannerAdapter photoAdapter;
    private List<Integer> mListPhoto;
    private Timer mTimer;
    RecyclerView mList1;
    List<App> appList;

    private View view;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_live, container, false);
        mainActivity = (MainActivity) getActivity();

        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        mListPhoto = getListPhoto();
        photoAdapter = new BannerAdapter(mainActivity, mListPhoto);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        autoSlideImage();
        mList1 = view.findViewById(R.id.list1);
        appList = new ArrayList<>();

        appList.add(new App(R.drawable.watchingtv, "Tất cả"));
        appList.add(new App(R.drawable.gift, "Freeship"));
        appList.add(new App(R.drawable.schedule, "Sắp diễn ra"));
        appList.add(new App(R.drawable.live, "Idol Review"));
        appList.add(new App(R.drawable.videocallingapp, "Mới nhất"));
        appList.add(new App(R.drawable.backarrow, "Xem lại"));

        LinearLayoutManager manager1 = new LinearLayoutManager(mainActivity);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList1.setLayoutManager(manager1);


        CustomAdaptor adaptor1 = new CustomAdaptor(mainActivity, appList);
        mList1.setAdapter(adaptor1);
        return view;
    }

    private List<Integer> getListPhoto() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.shopee1);
        list.add(R.drawable.shopee2);
        list.add(R.drawable.shopee3);
        list.add(R.drawable.shopee4);
        list.add(R.drawable.shopee5);
        list.add(R.drawable.shopee6);

        return list;
    }

    private void autoSlideImage() {
        if (mListPhoto == null || mListPhoto.isEmpty() || viewPager == null) {
            return;
        }

        // Init Timer
        if (mTimer == null) {
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 400, 2500);
    }
}

