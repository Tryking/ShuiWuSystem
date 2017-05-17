package suiwu.bishe.com.suiwu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.activity.NewsViewActivity;
import suiwu.bishe.com.suiwu.adapter.TreatyAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreatyFragment extends Fragment {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    Unbinder unbinder;

    public TreatyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(layoutManager);

        TreatyAdapter treatyAdapter = new TreatyAdapter();
        rvContent.setAdapter(treatyAdapter);
        treatyAdapter.setOnItemClickListener(new TreatyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String treatyUrl) {
                Intent intent = new Intent(getActivity(), NewsViewActivity.class);
                intent.putExtra("url", treatyUrl);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_treaty, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
