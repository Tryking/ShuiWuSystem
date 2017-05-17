package suiwu.bishe.com.suiwu.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.activity.NewsViewActivity;
import suiwu.bishe.com.suiwu.adapter.DividerItemDecoration;
import suiwu.bishe.com.suiwu.adapter.NewsAdapter;
import suiwu.bishe.com.suiwu.bean.NewsBean;
import suiwu.bishe.com.suiwu.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsAdapter.OnItemClickListener {


    @BindView(R.id.main_recyclerView)
    RecyclerView mainRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    Unbinder unbinder;
    Context mContext;

    private RequestQueue requestQueue;
    private NewsAdapter newsAdapter;
    private ArrayList<NewsBean.ResultBean.DataBean> newsBeenList;

    public NewsFragment(Context context) {
        // Required empty public constructor
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsBeenList = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("test", "开始Resume");
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("加载", "开始更新");
                requestData();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        newsAdapter = new NewsAdapter(getActivity(), newsBeenList);
        mainRecyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickListener(this);
        Log.e("size:", newsBeenList.size() + "");
        if (newsBeenList.size() == 0) {
            // TODO: 2017/5/11 防止测试的用完100条数据，先注释
            Log.e("开始更新:", newsBeenList.size() + "");
            swipeRefresh.setRefreshing(true);
            requestData();
        }
    }

    private void requestData() {
        requestQueue = Volley.newRequestQueue(getActivity());
        String url = "http://v.juhe" +
                ".cn/toutiao/index?type=caijing&key=e6dd51377ebf8a1c1c9c45f9ff1c4372";
        Log.e("请求ＵＲＬ", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response
                .Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String jsonStr = response.toString();
                NewsBean newsBean = JSON.parseObject(jsonStr, NewsBean.class);
                Log.e("成功", newsBean.toString());
                Message msg = new Message();
                msg.what = Constant.NewsRequestSuccess;
                msg.obj = newsBean;
                handler.sendMessage(msg);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("失败", error.toString());
                handler.sendEmptyMessage(Constant.NewsRequestFail);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.NewsRequestSuccess:
                    NewsBean newsBean = (NewsBean) msg.obj;
                    newsBeenList.clear();
                    newsBeenList.addAll(newsBean.getResult().getData());
                    newsAdapter.notifyDataSetChanged();
                    break;
                case Constant.NewsRequestFail:
                    break;
            }
            if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                swipeRefresh.setRefreshing(false);
            }
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(String newsUrl) {
//        Toast.makeText(getActivity(), "点击：" + newsUrl, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), NewsViewActivity.class);
        intent.putExtra("url", newsUrl);
        startActivity(intent);
    }
}
