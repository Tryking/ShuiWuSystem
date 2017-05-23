package suiwu.bishe.com.suiwu.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import suiwu.bishe.com.suiwu.MainActivity;
import suiwu.bishe.com.suiwu.R;
import suiwu.bishe.com.suiwu.bean.LoginInfo;
import suiwu.bishe.com.suiwu.util.Constant;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    private RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        btLogin.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在登陆");
        progressDialog.setMessage("请稍等...");
        progressDialog.setCancelable(false);

        pref = getSharedPreferences("data", MODE_PRIVATE);

        boolean loginSuccess = pref.getBoolean(Constant.PREF_LOGIN_SUCCESS, false);
        if (loginSuccess) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                if (etAccount.getText().toString().isEmpty()) {
                    Toast.makeText(this, "账户不能为空", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    //执行网络操作
                    String url = Constant.loginPath;
                    url = url + "?username=" + etAccount.getText().toString() + "&password=" +
                            etPassword.getText().toString();
                    Log.e("url：", url);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new
                            Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e("成功", response.toString());
                                    String jsonStr = response.toString();
                                    LoginInfo loginInfo = JSON.parseObject(jsonStr, LoginInfo
                                            .class);
                                    Message msg = new Message();
                                    if (loginInfo.getLogin().equals("1")) {
                                        msg.what = Constant.LoginSuccess;
                                    } else {
                                        msg.what = Constant.LoginFailure;
                                    }
                                    msg.obj = loginInfo;
                                    handler.sendMessage(msg);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("失败", error.toString());
                            handler.sendEmptyMessage(Constant.ErrorRequest);
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                }
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            switch (msg.what) {
                case Constant.LoginSuccess:
                    //登陆成功
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putBoolean(Constant.PREF_LOGIN_SUCCESS, true);
                    edit.putString(Constant.PREF_ACCOUNT_NAME, etAccount.getText().toString());
                    edit.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    break;
                case Constant.LoginFailure:
                    //登陆失败
                    Toast.makeText(LoginActivity.this, "账户密码不符，请重新输入", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.ErrorRequest:
                    Toast.makeText(LoginActivity.this, "网络错误，请检查", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
