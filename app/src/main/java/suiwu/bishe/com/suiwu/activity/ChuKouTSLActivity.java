package suiwu.bishe.com.suiwu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.database.Product;

public class ChuKouTSLActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.tv_9)
    TextView tv9;
    @BindView(R.id.tv_10)
    TextView tv10;
    private ArrayList<Product> searchProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_kou_tsl);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        // TODO: 2017/5/17 如果要隐藏添加按钮，把这个注释打开。
//        btAdd.setVisibility(View.GONE);
        searchProducts = new ArrayList<>();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("出口退税率查询");
        actionBar.setDisplayHomeAsUpEnabled(true);

//        etSearch.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (keyEvent.getKeyCode() == keyEvent.KEYCODE_ENTER) {
//                  //在这里处理会处理两次，按下一次，抬起一次
//                }
//                return false;
//            }
//        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    //Toast.makeText(ChuKouTSLActivity.this, "搜索", Toast.LENGTH_LONG).show();
                    doSearch();
                    return true;
                }
                return false;
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChuKouTSLActivity.this, AddProductActivity.class));
            }
        });
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tv10.setOnClickListener(this);
    }

    private void doSearch() {
        List<Product> products = DataSupport.findAll(Product.class);
        for (Product product : products) {
            Log.e("数据库数据:", product.toString());
            if (product.getName().contains(etSearch.getText().toString()) || product.getCoding()
                    .contains(etSearch.getText().toString())) {
                searchProducts.add(product);
            }
        }

        if (searchProducts.size() == 0) {
            Toast.makeText(ChuKouTSLActivity.this, "无数据", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(ChuKouTSLActivity.this, ChuKouTSLShowActivity
                    .class);
            intent.putExtra("productInfo", searchProducts);
            startActivity(intent);
            searchProducts.clear();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        etSearch.setText(((TextView) view).getText().toString());
        doSearch();
    }
}
