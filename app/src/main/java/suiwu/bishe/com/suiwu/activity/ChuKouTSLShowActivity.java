package suiwu.bishe.com.suiwu.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.adapter.ChuKouTSLShowAdapter;
import suiwu.bishe.com.suiwu.adapter.DividerItemDecoration;
import suiwu.bishe.com.suiwu.adapter.NewsAdapter;

public class ChuKouTSLShowActivity extends AppCompatActivity {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private ArrayList productInfo;
    private ChuKouTSLShowAdapter chuKouTSLShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_kou_tslshow);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("出口退税率");
        actionBar.setDisplayHomeAsUpEnabled(true);

        productInfo = (ArrayList) getIntent().getSerializableExtra("productInfo");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(layoutManager);
        rvContent.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        chuKouTSLShowAdapter = new ChuKouTSLShowAdapter(productInfo);
        rvContent.setAdapter(chuKouTSLShowAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
