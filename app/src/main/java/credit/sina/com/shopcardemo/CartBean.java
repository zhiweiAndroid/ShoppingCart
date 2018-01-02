package credit.sina.com.shopcardemo;

/**
 * Created by sinafenqi on 2017/12/13.
 */

public class CartBean {

    public boolean isSelected;
    private double money;
    private String status;

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    private String orderdate;

    public CartBean(boolean isSelected, String orderdate, double money, String status, String date) {
        this.isSelected = isSelected;
        this.money = money;
        this.status = status;
        this.date = date;
        this.orderdate=orderdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
