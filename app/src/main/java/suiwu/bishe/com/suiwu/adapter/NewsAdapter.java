package suiwu.bishe.com.suiwu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.bean.NewsBean;

/**
 * Created by Tryking on 2017/5/10.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsBean.ResultBean.DataBean> news;
    private Context context;

    public NewsAdapter(Context context, List<NewsBean.ResultBean.DataBean> news) {
        this.news = news;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.newsPic.setImageResource(R.mipmap.ic_launcher);
        Glide.with(context).load(news.get(position).getThumbnail_pic_s())
                .into(holder.newsPic);
        holder.newsTitle.setText(news.get(position).getTitle());
        holder.newsTime.setText(news.get(position).getDate());
        holder.llLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(news.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (news == null) {
            return 0;
        }
        return news.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_link)
        LinearLayout llLink;
        @BindView(R.id.news_pic)
        ImageView newsPic;
        @BindView(R.id.news_title)
        TextView newsTitle;
        @BindView(R.id.news_time)
        TextView newsTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(String newsUrl);
    }
}
