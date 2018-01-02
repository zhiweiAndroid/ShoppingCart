package credit.sina.com.shopcardemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sinafenqi on 2017/12/13.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {

    private Context mContext;
    private List<CartBean> mDatas;

    private OnItemClickListener mOnItemClickListener;

    private LayoutInflater mInflater;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void delete(View view, int position);

        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public ShoppingCartAdapter(Context context, List<CartBean> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ShoppingCartViewHolder shoppingCartViewHolder = new ShoppingCartViewHolder
                (mInflater.inflate(R.layout.item_car_view, parent, false));
        return shoppingCartViewHolder;
    }

    @Override
    public void onBindViewHolder(final ShoppingCartViewHolder holder, final int position) {


        holder.isSelected.setChecked(mDatas.get(position).isSelected());
        holder.tvMoney.setText("应还"+mDatas.get(position).getMoney()+"元");
        holder.tvOrderDate.setText(mDatas.get(position).getOrderdate());
        holder.rlitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, position);
                }
            }
        });

        holder.isSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.isSelected.setChecked(!mDatas.get(position).isSelected);
//                元素的位置以及是否被选中
                holder.isSelected.setChecked(!mDatas.get(position).isSelected);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.checkGroup(position, holder.isSelected.isChecked());
                }
            }
        });

        holder.avDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.delete(view, position);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class ShoppingCartViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_is_selected)
        CheckBox isSelected;
        @BindView(R.id.tv_order_date)
        TextView tvOrderDate;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.delete)
        TextView avDelete;
        @BindView(R.id.rl_item)
        RelativeLayout rlitem;
        public ShoppingCartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }


}
