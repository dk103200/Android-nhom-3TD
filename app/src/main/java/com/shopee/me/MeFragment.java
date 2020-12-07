package com.shopee.me;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shopee.CustomSheet;
import com.shopee.MainActivity;
import com.shopee.R;
import com.shopee.feed.List_Menu;

import java.util.ArrayList;

public class MeFragment extends Fragment {
    private View view, viewSign;
    private MainActivity mainActivity;
    TextView btn_login, btn_signup;
    private FragmentActivity myContext;

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        viewSign = inflater.inflate(R.layout.fragment_login, container, false);
        init_ListCaNhan();

        btn_login = view.findViewById(R.id.bt_dangNhap);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openLogin();
            }
        });

        btn_signup = view.findViewById(R.id.bt_dangKy);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog signupDialog = new BottomSheetDialog(myContext);
                signupDialog.setContentView(R.layout.fragment_login);

                ImageView back = signupDialog.findViewById(R.id.back);

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signupDialog.dismiss();
                    }

                });


                signupDialog.show();

            }
        });

        return view;
    }

    public void init_ListCaNhan() {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_CaNhan);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<List_Menu> arrayList = new ArrayList<>();

        arrayList.add(new List_Menu(R.drawable.donmua, "Đơn mua"));
        arrayList.add(new List_Menu(R.drawable.smartphone, "Đơn nạp thẻ và Dịch vụ"));
        arrayList.add(new List_Menu(R.drawable.like, "Đã thích"));
        arrayList.add(new List_Menu(R.drawable.clock, "Đã xem gần đây"));
        arrayList.add(new List_Menu(R.drawable.vi, "Ví shopee"));
        arrayList.add(new List_Menu(R.drawable.xu, "Shopee xu"));
        arrayList.add(new List_Menu(R.drawable.danhgia, "Đánh gái của tôi"));
        arrayList.add(new List_Menu(R.drawable.voucher, "Ví Voucher"));
        arrayList.add(new List_Menu(R.drawable.taikhoan, "Thiết lập tài khoản"));
        arrayList.add(new List_Menu(R.drawable.trogiup, "Trung tâm trợ giúp"));
        arrayList.add(new List_Menu(R.drawable.trochuyen, "Trò Chuyện Với Shopee"));

        AdapterOfListMenu adapter = new AdapterOfListMenu(arrayList, mainActivity, R.layout.row_canhan);
        recyclerView.setAdapter(adapter);

    }


    public void openLogin() {
//
//        final BottomSheetDialog loginDialog = new BottomSheetDialog(mainActivity);
//        View loginView = LayoutInflater.from(myContext.getApplicationContext()).inflate(
//                R.layout.fragment_login,   findViewById(R.id.login_dialog)
//        );
//        loginView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginDialog.dismiss();
//
//            }
//        });
//        loginDialog.setContentView(loginView);
//        loginDialog.show();
//        CustomSheet customSheet = new CustomSheet(R.layout.fragment_login);
//        customSheet.show(myContext.getSupportFragmentManager(), "custom_sheet");
    }

    public void openSignup() {

//        CustomSheet customSheet = new CustomSheet(R.layout.fragment_login);
//        customSheet.show(myContext.getSupportFragmentManager(), "custom_sheet");
    }
}
