package suiwu.bishe.com.suiwu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.activity.BanShuiZNActivity;
import suiwu.bishe.com.suiwu.activity.ChuKouTSLActivity;
import suiwu.bishe.com.suiwu.adapter.DividerGridItemDecoration;
import suiwu.bishe.com.suiwu.adapter.ServiceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment {


    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    Unbinder unbinder;
    private ArrayList<Integer> picList;
    private ArrayList<Integer> titleList;

    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    startActivity(new Intent(getActivity(), BanShuiZNActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getActivity(), ChuKouTSLActivity.class));
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        picList = new ArrayList<>();
        picList.add(R.drawable.bszn);
        picList.add(R.drawable.ydbsdh);
        picList.add(R.drawable.bsdt);
        picList.add(R.drawable.cktsl);
        picList.add(R.drawable.swsws);
        picList.add(R.drawable.xydj);
        picList.add(R.drawable.fpcx);

        titleList = new ArrayList<>();
        titleList.add(R.string.bszn);
        titleList.add(R.string.ydbsdh);
        titleList.add(R.string.bsdt);
        titleList.add(R.string.cktsl);
        titleList.add(R.string.swsws);
        titleList.add(R.string.xydj);
        titleList.add(R.string.fpcx);
    }

    @Override
    public void onResume() {
        super.onResume();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        rvContent.setLayoutManager(layoutManager);
        rvContent.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        ServiceAdapter serviceAdapter = new ServiceAdapter(picList, titleList, handler);
        rvContent.setAdapter(serviceAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
