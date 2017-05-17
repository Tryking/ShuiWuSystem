package suiwu.bishe.com.suiwu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.adapter.BanShuiZNAdapter;
import suiwu.bishe.com.suiwu.adapter.DividerItemDecoration;

public class BanShuiZNActivity extends AppCompatActivity {

    @BindView(R.id.content)
    RecyclerView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_shui_zn);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        content.setLayoutManager(layoutManager);
        content.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        BanShuiZNAdapter banShuiZNAdapter = new BanShuiZNAdapter(handler);
        content.setAdapter(banShuiZNAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("办税指南");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    startActivity(new Intent(BanShuiZNActivity.this, ShuiWuDJActivity.class));
                    break;
            }
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
