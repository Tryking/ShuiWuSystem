package suiwu.bishe.com.suiwu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;

public class ShuiWuDJActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_sldj)
    TextView tvSldj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shui_wu_dj);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("税务登记");
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvSldj.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sldj:
                startActivity(new Intent(ShuiWuDJActivity.this, SheLiDJActivity.class));
                break;
        }
    }
}
