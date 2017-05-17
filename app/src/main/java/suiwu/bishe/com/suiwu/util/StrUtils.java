package suiwu.bishe.com.suiwu.util;

import java.util.ArrayList;
import java.util.List;

import suiwu.bishe.com.suiwu.database.Product;

/**
 * Created by Tryking on 2017/5/17.
 */

public class StrUtils {
    public static ArrayList<Product> ReadProduct(String productStr) {
        StringBuilder builder = new StringBuilder();
        int tag = 0;
        Product product = new Product();
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < productStr.length(); i++) {
            if (productStr.charAt(i) == '\n') {
                product.setTaxRate(Double.parseDouble(builder.toString()));
                products.add(product);
                product = new Product();
                tag = 0;
                builder.setLength(0);
            } else if (productStr.charAt(i) == '\t') {
                switch (tag) {
                    case 0:
                        product.setCoding(builder.toString());
                        break;
                    case 1:
                        product.setName(builder.toString());
                        break;
                    case 2:
                        product.setUint(builder.toString());
                        break;
                }
                builder.setLength(0);
                tag++;
            } else {
                builder.append(productStr.charAt(i));
            }
        }
        return products;
    }
}
