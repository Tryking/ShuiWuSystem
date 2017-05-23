package suiwu.bishe.com.suiwu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.activity.BillShowActivitiy;
import suiwu.bishe.com.suiwu.activity.LoginActivity;
import suiwu.bishe.com.suiwu.fragment.NewsFragment;
import suiwu.bishe.com.suiwu.fragment.ServiceFragment;
import suiwu.bishe.com.suiwu.fragment.TreatyFragment;
import suiwu.bishe.com.suiwu.util.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar navBar;
    @BindView(R.id.slidePane)
    SlidingPaneLayout slidePane;
    @BindView(R.id.ll_menu)
    LinearLayout llMenu;
    @BindView(R.id.ll_main)
    RelativeLayout llMain;
    @BindView(R.id.iv_portrait)
    ImageView ivPortrait;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_quit)
    TextView tvQuit;
    @BindView(R.id.tv_view_bill)
    TextView tvViewBill;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("税务管理");
        actionBar.setDisplayHomeAsUpEnabled(false);

        slidePaneSet();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        NewsFragment newsFragment = new NewsFragment(this);
        TreatyFragment treatyFragment = new TreatyFragment();
        ServiceFragment serviceFragment = new ServiceFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(newsFragment);
        fragments.add(treatyFragment);
        fragments.add(serviceFragment);

        fragmentTransaction.replace(R.id.main_content, fragments.get(0));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        navBar
                .addItem(new BottomNavigationItem(R.drawable.tab_news, "税闻"))
                .addItem(new BottomNavigationItem(R.drawable.tab_treaty, "政策"))
                .addItem(new BottomNavigationItem(R.drawable.tab_service, "服务"))
                .initialise();
        navBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, fragments.get(position));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void slidePaneSet() {
        slidePane.setParallaxDistance(200);
        slidePane.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                llMenu.setScaleY(slideOffset / 2 + 0.5f);
                llMenu.setScaleX(slideOffset / 2 + 0.5f);
                llMain.setScaleY(1 - slideOffset / 5);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
        tvQuit.setOnClickListener(this);
        tvViewBill.setOnClickListener(this);
        tvAccount.setText(getSharedPreferences("data", MODE_PRIVATE).getString(Constant
                .PREF_ACCOUNT_NAME, ""));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_quit:
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putBoolean(Constant.PREF_LOGIN_SUCCESS, false);
                edit.apply();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.tv_view_bill:
//                Toast.makeText(getApplicationContext(), "查看账单", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, BillShowActivitiy.class));
                break;
        }
    }
}
