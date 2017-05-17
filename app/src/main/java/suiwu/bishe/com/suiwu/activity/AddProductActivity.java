package suiwu.bishe.com.suiwu.activity;

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.database.Product;

public class AddProductActivity extends AppCompatActivity {

    @BindView(R.id.et_coding)
    EditText etCoding;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_uint)
    EditText etUint;
    @BindView(R.id.et_taxRate)
    EditText etTaxRate;
    @BindView(R.id.bt_add)
    Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCoding.getText().toString().isEmpty()) {
                    Toast.makeText(AddProductActivity.this, "商品编码不能为空", Toast
                            .LENGTH_SHORT).show();
                } else if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(AddProductActivity.this, "商品名称不能为空", Toast
                            .LENGTH_SHORT).show();
                } else if (etUint.getText().toString().isEmpty()) {
                    Toast.makeText(AddProductActivity.this, "计量单位不能为空", Toast
                            .LENGTH_SHORT).show();
                } else if (etTaxRate.getText().toString().isEmpty()) {
                    Toast.makeText(AddProductActivity.this, "增值税退税率不能为空", Toast
                            .LENGTH_SHORT).show();
                } else {
                    double taxRate = -1;
                    try {
                        taxRate = Double.parseDouble(etTaxRate.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(AddProductActivity.this, "增值税退税率无效，重新输入", Toast
                                .LENGTH_SHORT).show();
                    } finally {
                        if (taxRate != -1) {
                            List<Product> products = DataSupport.findAll(Product.class);
                            boolean tag = false;
                            for (Product product : products) {
                                Log.e("数据库数据", product.toString());
                                if (product.getCoding().equals(etCoding.getText().toString())) {
                                    tag = true;
                                    Toast.makeText(AddProductActivity.this, "编码重复，重新输入", Toast
                                            .LENGTH_SHORT).show();
                                }
                            }
                            if (!tag) {
                                Product product = new Product();
                                product.setCoding(etCoding.getText().toString());
                                product.setName(etName.getText().toString());
                                product.setUint(etUint.getText().toString());
                                product.setTaxRate(Double.parseDouble(etTaxRate.getText()
                                        .toString()));
                                Log.e("添加数据", product.toString());
                                product.save();
                                Toast.makeText(AddProductActivity.this, "添加成功", Toast
                                        .LENGTH_SHORT).show();
                                etCoding.setText("");
                                etTaxRate.setText("");
                                etUint.setText("");
                                etName.setText("");
                            }
                        }
                    }
                }
            }
        });
    }
}
