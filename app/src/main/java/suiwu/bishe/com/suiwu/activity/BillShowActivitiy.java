package suiwu.bishe.com.suiwu.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.adapter.BillShowAdapter;
import suiwu.bishe.com.suiwu.adapter.DividerItemDecoration;
import suiwu.bishe.com.suiwu.bean.Bill;
import suiwu.bishe.com.suiwu.util.Constant;

public class BillShowActivitiy extends AppCompatActivity {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.ll_no_bill)
    LinearLayout llNoBill;
    private RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    private SharedPreferences pref;
    private ArrayList<Bill> bills;
    private BillShowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_show_activitiy);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("账单查看");
        actionBar.setDisplayHomeAsUpEnabled(true);

        requestQueue = Volley.newRequestQueue(this);

        bills = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(layoutManager);
        rvContent.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        adapter = new BillShowAdapter(bills);
        rvContent.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在获取账单");
        progressDialog.setMessage("请稍等...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        //执行网络操作
        String url = Constant.getBillPath;
        pref = getSharedPreferences("data", MODE_PRIVATE);
        String accountName = pref.getString(Constant.PREF_ACCOUNT_NAME, "");
        url = url + "?username=" + accountName;
        Log.e("url：", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response
                .Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("成功", response.toString());
                String jsonStr = response.toString();
                List<Bill> billList = JSON.parseArray(jsonStr, Bill.class);
                bills.clear();
                bills.addAll(billList);
                if (bills.isEmpty()) {
                    llNoBill.setVisibility(View.VISIBLE);
                    rvContent.setVisibility(View.GONE);
                } else {
                    llNoBill.setVisibility(View.GONE);
                    rvContent.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "网络异常，请检查", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
