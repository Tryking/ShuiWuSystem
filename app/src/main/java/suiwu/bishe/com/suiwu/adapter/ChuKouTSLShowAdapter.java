package suiwu.bishe.com.suiwu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.database.Product;

/**
 * Created by Tryking on 2017/5/17.
 */

public class ChuKouTSLShowAdapter extends RecyclerView.Adapter<ChuKouTSLShowAdapter.ViewHolder> {
    private List<Product> productList;

    public ChuKouTSLShowAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chukoutsl_show, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.coding.setText(productList.get(position).getCoding());
        holder.name.setText(productList.get(position).getName());
        holder.uint.setText("计量单位：" + productList.get(position).getUint());
        holder.taxRate.setText(String.valueOf(productList.get(position).getTaxRate()) + "%");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coding)
        TextView coding;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.uint)
        TextView uint;
        @BindView(R.id.taxRate)
        TextView taxRate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
