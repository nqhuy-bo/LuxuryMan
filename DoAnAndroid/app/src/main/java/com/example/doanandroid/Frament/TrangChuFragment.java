//package com.example.doanandroid.Frament;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.GridView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.doanandroid.R;
//import com.example.doanandroid.SanPham;
//import com.example.doanandroid.SanPhamAdapter;
//import com.example.doanandroid.TrangChuActivity;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//
//import java.util.ArrayList;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class TrangChuFragment extends Fragment {
//
//    GridView gridViewHomeFragment;
//    ArrayList<SanPham> arrayList;
//    SanPhamAdapter adapter;
//    public TrangChuFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
//
//    }
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        gridViewHomeFragment = view.findViewById(R.id.gridViewHomeFragment);
//        String url = "https://mylifemrrobot.000webhostapp.com/getdata.php";
//
//        arrayList = new ArrayList<>();
//        adapter = new SanPhamAdapter(getContext(),R.layout.custom_dong_sanpham,arrayList);
//        gridViewHomeFragment.setAdapter(adapter);
//        gridViewHomeFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SanPham sp ;
//                Intent intent = new Intent(getActivity(),ActivityDetail.class);
//                sp= arrayList.get(position);
//                intent.putExtra("MASP",sp.getMASANPHAM());
//                intent.putExtra("TENSP",sp.getTENSANPHAM());
//                intent.putExtra("GIA",sp.getGIA());
//                intent.putExtra("HINH",sp.getHINHANH());
//                intent.putExtra("MALOAI",sp.getMALOAI());
//                intent.putExtra("MOTA",sp.getMOTASANPHAM());
//                startActivity(intent);
//                Toast.makeText(getContext(), ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        getData(url);
//
//    }
//
//    private void getData(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        //SANPHAMArrayList = new ArrayList<>();
//                        //Duyệt từng JSON trog mảng
//                        for(int i=0;i<response.length();i++) {
//                            try {
//                                JSONObject object = response.getJSONObject(i);
//                                int MASANPHAM = object.getInt("MASANPHAM");
//                                Log.d("AAA",MASANPHAM+"");
//                                String TENSANPHAM = object.getString("TENSANPHAM");
//                                Log.d("AAA",TENSANPHAM+" Tên");
//                                int GIA = object.getInt("GIA");
//                                Log.d("AAA",GIA+" Giá");
//                                String MOTASANPHAM = object.getString("MOTASANPHAM");
//                                Log.d("AAA",MOTASANPHAM+" Mo ta");
//                                String HINH = object.getString("HINHANH");
//                                Log.d("AAA",HINH+" hinh ta");
//                                int MALOAI = object.getInt("MALOAI");
//                                arrayList.add(new SanPham(MASANPHAM,MALOAI,GIA,TENSANPHAM,MOTASANPHAM,HINH));
//                                //Toast.makeText(MainActivity.this, hoTen + "\n" + namsSinh + "\n" + diaChi, Toast.LENGTH_SHORT).show();
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText((TrangChuActivity) getContext(),error.toString(),Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//        requestQueue.add(jsonArrayRequest);
//    }
//}
