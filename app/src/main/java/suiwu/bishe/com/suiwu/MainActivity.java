package suiwu.bishe.com.suiwu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.fragment.NewsFragment;
import suiwu.bishe.com.suiwu.fragment.ServiceFragment;
import suiwu.bishe.com.suiwu.fragment.TreatyFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar navBar;

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
//        actionBar.setDisplayHomeAsUpEnabled(true);

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

}
