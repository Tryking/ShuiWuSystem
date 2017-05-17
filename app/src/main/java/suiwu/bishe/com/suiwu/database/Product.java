package suiwu.bishe.com.suiwu.database;

import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Tryking on 2017/5/17.
 */

public class Product extends DataSupport implements Serializable {
    private int id;
    private String coding;
    private String name;
    private String uint;
    private double taxRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUint() {
        return uint;
    }

    public void setUint(String uint) {
        this.uint = uint;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", coding='" + coding + '\'' +
                ", name='" + name + '\'' +
                ", uint='" + uint + '\'' +
                ", taxRate=" + taxRate +
                '}';
    }
}
