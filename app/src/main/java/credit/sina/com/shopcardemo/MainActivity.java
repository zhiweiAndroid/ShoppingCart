package credit.sina.com.shopcardemo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ShoppingCartAdapter.OnItemClickListener {

    @BindView(R.id.tv_cart_total_money)
    TextView tvCartTotalMoney;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.cb_cart_all)
    CheckBox cbCartAll;
    private ShoppingCartAdapter mAdapter;
    private double mTotalPrice=0.0;
    private List<CartBean> cartBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        cartBeans.add(new CartBean(false, "1/10", 5000, "1", "2017-9-17日应还"));
        cartBeans.add(new CartBean(false, "2/10", 3000, "2", "2017-10-17日应还"));
        cartBeans.add(new CartBean(false, "3/10", 6000, "3", "2017-11-17日应还"));
        cartBeans.add(new CartBean(false, "4/10", 4000, "1", "2017-12-17日应还"));
        cartBeans.add(new CartBean(false, "5/10", 4000, "1", "2017-12-17日应还"));
        cartBeans.add(new CartBean(false, "6/10", 4000, "1", "2017-12-17日应还"));
        cartBeans.add(new CartBean(false, "7/10", 4000, "1", "2017-12-17日应还"));
        cartBeans.add(new CartBean(false, "8/10", 1000, "2", "2018-1-17日应还"));
        cartBeans.add(new CartBean(false, "9/10", 1000, "2", "2018-1-17日应还"));
        cartBeans.add(new CartBean(false, "10/10", 1000, "2", "2018-1-17日应还"));

        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(0);
        dividerLine.setColor(ContextCompat.getColor(this, R.color.white));
        recycleview.addItemDecoration(dividerLine);
        recycleview.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(manager);
        if (mAdapter == null) {
            mAdapter = new ShoppingCartAdapter(this, cartBeans);
        }
        mAdapter.setOnItemClickListener(this);
        recycleview.setAdapter(mAdapter);
        tvCartTotalMoney.setText("￥" + mTotalPrice);

    }

    @OnClick({R.id.tv_return_money, R.id.cb_cart_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_return_money:

                break;
            case R.id.cb_cart_all:
                checkAll();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "条目点击", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delete(View view, int position) {
        Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkGroup(int position, boolean isChecked) {
        cartBeans.get(position).isSelected = isChecked;
        if (isAllCheck())
            cbCartAll.setChecked(true);
        else
            cbCartAll.setChecked(false);
        mAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 全选和反选
     */
    private void checkAll() {
        if (cartBeans.size() != 0) {
            if (cbCartAll.isChecked()) {
                for (int i = 0; i < cartBeans.size(); i++) {
                    cartBeans.get(i).isSelected = true;
                }
                mAdapter.notifyDataSetChanged();
            } else {
                for (int i = 0; i < cartBeans.size(); i++) {
                    cartBeans.get(i).isSelected = false;
                }
                mAdapter.notifyDataSetChanged();
            }
        }
        calculate();
    }

    /**
     * 判断是否全部选中
     */
    private boolean isAllCheck() {
        for (CartBean cartBean : cartBeans) {
            if (!cartBean.isSelected)
                return false;
        }
        return true;
    }

    private void calculate() {
        mTotalPrice = 0.00;
        for (CartBean cartBean : cartBeans) {
            if (cartBean.isSelected) {
                mTotalPrice += cartBean.getMoney();
            }
        }
        tvCartTotalMoney.setText("￥" + mTotalPrice);

    }


}
