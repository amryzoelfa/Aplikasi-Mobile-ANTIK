package com.example.antik.Doeloe_TidakDigunakan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.antik.Adapter.AdapterData;
import com.example.antik.Model.ModelRiwayat;
import com.example.antik.R;
import com.example.antik.Util.AppController;
import com.example.antik.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CobaActivity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelRiwayat> mItems;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewTemp);
        pd = new ProgressDialog(CobaActivity.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(CobaActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterData(CobaActivity.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

    }

    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_RIWAYAT,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelRiwayat md = new ModelRiwayat();
                                md.setTanggal_periksa(data.getString("tanggal_periksa"));
                                md.setPoli(data.getString("id_poli"));
                                md.setDiagnosa(data.getString("diagnosa"));
                                md.setTindakan(data.getString("tindakan"));
                                md.setObat(data.getString("resep_obat"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }
}
