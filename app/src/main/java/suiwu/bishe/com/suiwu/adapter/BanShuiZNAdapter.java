package suiwu.bishe.com.suiwu.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;

/**
 * Created by Tryking on 2017/5/10.
 */

public class BanShuiZNAdapter extends RecyclerView.Adapter<BanShuiZNAdapter.ViewHolder> {
    String[] banShuiZNStr = new String[]{"税务登记", "税务认定", "发票办理", "申报纳税", "证明办理"};
    int[] banShuiZNImg = new int[]{R.drawable.zn_swdj,
            R.drawable.zn_1, R.drawable.zn_2, R.drawable.zn_3, R.drawable.zn_4};
    Handler handler;


    public BanShuiZNAdapter(Handler handler) {
        this.handler = handler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banshuizn, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(banShuiZNStr[position]);
        holder.ivIcon.setImageResource(banShuiZNImg[position]);
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return banShuiZNStr.length;
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
