package com.example.doanandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Frament.ActivityDetail;
import com.example.doanandroid.Retrofit.ApiInterface;
import com.example.doanandroid.Retrofit.Retrofitclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemActivity extends AppCompatActivity {
    com.airbnb.lottie.LottieAnimationView lottieAnimationView;
    private static final int REQUEST_CODE_SPECH_INPUT = 2500;
    ImageButton imgbtMic;
    SearchView searchView;
    RecyclerView recyclyViewSanPham;
    ArrayList<SanPham> arrayList;
    ListView listView;
    TagGroup tagGroup;
    ArrayList<String> tag;
    TextView tvKhongTimThay;
    String text="";
    ApiInterface apInterface;
    ImageView imgQuayLai;
    final ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    CustomSanPhamAdapter customSanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        AnhXa();
        apInterface = Retrofitclient.getClient().create(ApiInterface.class);
        tag=new ArrayList<>();
//        tag.add("Quần jean");
//        tagGroup.setTags(tag);
//        sanPhamArrayList.add(new SanPham(1,1,100000,"Quần short","Quần short nam thời trang, lựa chọn hàng đầu cho những tín đồ thời trang mùa hè",
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjrupOhagxSFeFnLtSSDU-ZXqiiNFO7DxTPQ&usqp=CAU"));

        recyclyViewSanPham.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclyViewSanPham.setLayoutManager(layoutManager);
        customSanPhamAdapter = new CustomSanPhamAdapter(sanPhamArrayList,getApplicationContext());
        customSanPhamAdapter.notifyDataSetChanged();
        recyclyViewSanPham.setAdapter(customSanPhamAdapter);

        imgQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TimKiemActivity.this,TrangChuActivity.class));
                finish();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tag.add(query);
                tagGroup.setTags(tag);
                return false;


            }

            @Override
            public boolean onQueryTextChange(String newText) {
                timKiemSanPham(newText);
                if(sanPhamArrayList.isEmpty())
                {
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    tvKhongTimThay.setVisibility(View.VISIBLE);
                }
                else
                {
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    tvKhongTimThay.setVisibility(View.INVISIBLE);
                }

                return false;

            }
        });

        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                sanPhamArrayList.clear();
                customSanPhamAdapter.notifyDataSetChanged();
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView);
            }
        });

        imgbtMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamArrayList.clear();
                customSanPhamAdapter.notifyDataSetChanged();
                speak();
            }
        });

    }

    private void speak() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Nói nhỏ thế ai nghe");


        try{
            startActivityForResult(intent,REQUEST_CODE_SPECH_INPUT);
        }
        catch (Exception e)
        {
            Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    //nhận đầu vào bằng giọng nói và xử lý
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_CODE_SPECH_INPUT:
            {
                if(resultCode==RESULT_OK && null!=data)
                {
                    // lấy từ vừa nói
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String loiNoi = "";
                    for(int i=0;i<result.size();i++)
                    {
                        loiNoi+=result.get(i);
                    }
                    timKiemSanPham(loiNoi);
                    Toast.makeText(this, ""+loiNoi, Toast.LENGTH_SHORT).show();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    tvKhongTimThay.setVisibility(View.INVISIBLE);
                    //chuyển thành dạng văn bản
                    //  searchView.te(result.get(0));
                    if(sanPhamArrayList.size()==0)
                    {
                        lottieAnimationView.setVisibility(View.VISIBLE);
                        tvKhongTimThay.setVisibility(View.VISIBLE);
                    }

                }

                break;
            }
        }
    }

    public void timKiemSanPham(String text)
    {
        Call<List<SanPham>> sanpham = apInterface.searchSanPham(text);

        sanpham.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhamArrayList.clear();
                for(int i=0;i<response.body().size();i++)
                {
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    tvKhongTimThay.setVisibility(View.INVISIBLE);
                    sanPhamArrayList.add(new SanPham(response.body().get(i).getMASANPHAM(),
                            response.body().get(i).getMALOAI(),response.body().get(i).getGIA(),
                            response.body().get(i).getTENSANPHAM(),response.body().get(i).getMOTASANPHAM(),
                            response.body().get(i).getHINHANH()));
                    Log.d("BBB",response.body().get(i).getMASANPHAM()+"");
                    customSanPhamAdapter.notifyDataSetChanged();
                }



                customSanPhamAdapter.setOnItemClickListenner(new CustomSanPhamAdapter.OnItemClickListener() {
                                                                 @Override
                                                                 public void onItemClick(int position) {
                                                                     SanPham sp ;
                                                                     Intent intent = new Intent(TimKiemActivity.this, ActivityDetail.class);
                                                                     sp= sanPhamArrayList.get(position);
                                                                     intent.putExtra("MASP",sp.getMASANPHAM());
                                                                     intent.putExtra("TENSP",sp.getTENSANPHAM());
                                                                     intent.putExtra("GIA",sp.getGIA());
                                                                     intent.putExtra("HINH",sp.getHINHANH());
                                                                     intent.putExtra("MALOAI",sp.getMALOAI());
                                                                     intent.putExtra("MOTA",sp.getMOTASANPHAM());
                                                                     startActivity(intent);
                                                                     Toast.makeText(TimKiemActivity.this, ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
                                                                     finish();
                                                                 }
                                                             }
                );

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(TimKiemActivity.this, "Lỗi"+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("SSS",t.toString());
            }
        });
    }

    private void AnhXa() {
        recyclyViewSanPham = findViewById(R.id.recycleViewSanPham);
        tagGroup  = findViewById(R.id.tag_group);
        searchView = findViewById(R.id.search_vew);
        imgbtMic =  findViewById(R.id.ImageButtonMic);

        lottieAnimationView = findViewById(R.id.lottieKhongTimThay);
        tvKhongTimThay = findViewById(R.id.textViewKhongTimThay);
        imgQuayLai = findViewById(R.id.imageViewQuayLai);
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}