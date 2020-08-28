package com.example.doanandroid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class DangKiActivity extends AppCompatActivity {
   TextInputLayout edtHoTenDangKi,edtSoDienThoaiDangKi,edtMatKhauDangKi;
    //com.google.android.material.textfield.TextInputEditText edtHoTenDangKi,edtSoDienThoaiDangKi,edtMatKhauDangKi;
    Button btnDangKi,btnQuayVe;
    String hoTen,soDienThoai,matKhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangkii);

        AnhXa();
        final String url = "https://mylifemrrobot.000webhostapp.com/insert.php";

                btnDangKi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hoTen = edtHoTenDangKi.getEditText().getText().toString();
                        matKhau = edtMatKhauDangKi.getEditText().getText().toString();
                        soDienThoai = edtSoDienThoaiDangKi.getEditText().getText().toString();
                        if(!validateName() | !validatePassword() | !validatePhoneNo()  ){
                            return;
                        }
                        else {
                            DangKiThanhVien(url);
                        }

            }
        });
    }

    private void DangKiThanhVien(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Success")){

                            Toast.makeText(DangKiActivity.this,"Đăng kí Tài khoản thành công",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(DangKiActivity.this,"Số điện thoại này đã được đăng kí",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangKiActivity.this,"Xẩy ra lỗi",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("hoten",hoTen.trim());
                params.put("sodienthoai",soDienThoai.trim());
                params.put("matkhau",matKhau.trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtHoTenDangKi = findViewById(R.id.editTextHoTenDangKi);
        edtSoDienThoaiDangKi = findViewById(R.id.editTextSoDienThoaiDangKi);
        edtMatKhauDangKi = findViewById(R.id.editTextMatKhauDangKi);
        btnDangKi = findViewById(R.id.buttonDangKiDangKi);
        btnQuayVe = findViewById(R.id.buttonCancelDangKi);
    }
    private Boolean validateName() {

        String val = edtHoTenDangKi.getEditText().getText().toString();


        if (val.isEmpty()) {
            edtHoTenDangKi.setError("Tên không được để trống");
            return false;
        }

        else {
            edtHoTenDangKi.setError(null);
            edtHoTenDangKi.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo() {
        String val = edtSoDienThoaiDangKi.getEditText().getText().toString();
        String passwordVal = "^0\\d{9}$";


        if (val.isEmpty()) {
            edtSoDienThoaiDangKi.setError("Không được để trống số điện thoại");
            return false;
        }
        if(!val.matches(passwordVal))
        {
            edtSoDienThoaiDangKi.setError("Số điện thoại không hợp lệ");
            return false;
        }
        else {
            edtSoDienThoaiDangKi.setError(null);
            edtSoDienThoaiDangKi.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = edtMatKhauDangKi.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //ít nhất 1 chữ số
                //"(?=.*[a-z])" +         //ít nhất 1 chữ cái viết thường
                //"(?=.*[A-Z])" +         //ít nhất 1 chữ hoa
                "(?=.*[a-zA-Z])" +      //bất kỳ chữ cái nào
                "(?=.*[@#$%^&+=])" +    //ít nhất 1 ký tự đặc biệt
                "(?=\\S+$)" +           //không có khoảng trắng
                ".{4,}" +               //ít nhất 4 ký tự
                "$";

        if (val.isEmpty()) {
            edtMatKhauDangKi.setError("Mật Khẩu Không được để trống");
            return false;
        } else if (!val.matches(passwordVal)) {
            edtMatKhauDangKi.setError("Mật Khẩu quá yếu");
            return false;
        } else {
            edtMatKhauDangKi.setError(null);
            edtMatKhauDangKi.setErrorEnabled(false);
            return true;
        }
    }

}
