package suiwu.bishe.com.suiwu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;

public class SheLiDJActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ll_danwei)
    LinearLayout llDanwei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_li_dj);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("设立登记");
        actionBar.setDisplayHomeAsUpEnabled(true);

        llDanwei.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_danwei:
                startActivity(new Intent(SheLiDJActivity.this, DanWeiNSRSLDJActivity.class));
                break;
        }
    }
}
