package suiwu.bishe.com.suiwu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;

/**
 * Created by Tryking on 2017/5/11.
 */

public class TreatyAdapter extends RecyclerView.Adapter<TreatyAdapter.ViewHolder> {
    String[] treatys = new String[]{
            "关于《国家税务总局关于进一步明确营改增有关征管问题的公告》的解读",
            "关于发布《千户集团名册管理办法》公告的解读",
            "关于发布《特别纳税调查调整及相互协商程序管理办法》有关问题的解答",
            "关于《国家税务总局关于加强海关进口增值税抵扣管理的公告》的解读",
            "关于《国家税务总局关于修订企业所得税2个规范性文件的公告》的解读",
            "关于《国家税务总局关于启用全国增值税发票查验平台的公告》的解读",
            "关于《国家税务总局关于土地价款扣除时间等增值税征管问题的公告》的解读",
            "关于《国家税务总局关于发行2016年印花税票的公告》的解读",
            "关于《国家税务总局关于<中华人民共和国政府和马来西亚政府关于对所得避免双重征税和防止偷漏税的协定的换函>生效执行的公告》的解读",
            "关于《国家税务总局关于公布符合条件的销售熊猫普制金币纳税人名单（第七批）的公告》的解读",
            "关于《国家税务总局关于启用增值税普通发票（卷票）有关事项的公告》的解读",
            "关于《国家税务总局关于企业所得税有关问题的公告》的解读"};
    String[] times = new String[]{
            "[04-25]", "[04-05]", "[04-01]", "[02-20]", "[01-04]", "[01-04]",
            "[01-04]", "[01-04]", "[12-28]", "[12-28]", "[12-23]", "[12-15]"};
    String[] treatyUrls = new String[]{
            "http://www.chinatax.gov.cn/n810341/n810760/c2572947/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2540598/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2538658/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2477896/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2435363/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2435236/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2435196/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2435181/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2425146/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2425103/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2420176/content.html",
            "http://www.chinatax.gov.cn/n810341/n810760/c2410110/content.html"
    };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_treaty, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(treatys[position]);
        holder.tvTime.setText(times[position]);
        holder.llLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(treatyUrls[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return treatys.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.ll_link)
        LinearLayout llLink;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(TreatyAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    private TreatyAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(String treatyUrl);
    }
}
