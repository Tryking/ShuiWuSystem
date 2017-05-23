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
import suiwu.bishe.com.suiwu.bean.Bill;

/**
 * Created by Tryking on 2017/5/23.
 */

public class BillShowAdapter extends RecyclerView.Adapter<BillShowAdapter.ViewHolder> {
    private List<Bill> billList;

    public BillShowAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bill_show, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.company.setText("公司：" + billList.get(position).getGongsi());
        holder.money.setText("金额：" + String.valueOf(billList.get(position).getJine()));
        holder.ID.setText("ID：" + String.valueOf(billList.get(position).getID()));
        holder.legalPerson.setText("法人：" + billList.get(position).getFaren());
        holder.taxRate.setText("税率：" + String.valueOf(billList.get(position).getShuilv()));
        holder.accounts.setText("账目：" + billList.get(position).getZhangmu());
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.company)
        TextView company;
        @BindView(R.id.money)
        TextView money;
        @BindView(R.id.ID)
        TextView ID;
        @BindView(R.id.legal_person)
        TextView legalPerson;
        @BindView(R.id.tax_rate)
        TextView taxRate;
        @BindView(R.id.accounts)
        TextView accounts;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
