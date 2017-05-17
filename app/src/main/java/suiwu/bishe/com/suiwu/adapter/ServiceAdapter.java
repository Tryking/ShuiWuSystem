package suiwu.bishe.com.suiwu.adapter;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;

/**
 * Created by Tryking on 2017/5/10.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private ArrayList<Integer> picList;
    private ArrayList<Integer> titleList;
    private Handler handler;

    public ServiceAdapter(ArrayList<Integer> picList, ArrayList<Integer> titleList, Handler
            handler) {
        this.picList = picList;
        this.titleList = titleList;
        this.handler = handler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ivIcon.setImageResource(picList.get(position));
        holder.tvTitle.setText(titleList.get(position));
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Message msg = new Message();
//                msg.what=position;
                handler.sendEmptyMessage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return picList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.link)
        LinearLayout link;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
