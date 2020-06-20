package com.example.antik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.antik.Util.ServerAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

public class navigationbar extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    //    parameter untuk profil
    String mProfil, mNama, mAlamat, mJenisKelamin, mTtl, mNomor;
    String mtanggal, mpoli, mdiagnosa, mtindakan, mobat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationbar);

        progressDialog = new ProgressDialog(this);
        requestQueue = Volley.newRequestQueue(this);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BerandaFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.nav_riwayat:
                        fragment = new RiwayatFragment();
                        loadRiwayat();
                        break;
                    case R.id.nav_beranda:
                        fragment = new BerandaFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                        break;
                    case R.id.nav_profil:
                        loadProfil();
                        break;
                }

                return true;
            }
        });
    }

    private void loadProfil() {
        progressDialog.setMessage("Sedang memproses");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String id = "1";
        String url = AppVar.PROFIL_URL + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject data = jsonArray.getJSONObject(0);

                    mProfil = "http://192.168.43.97/CIANTIK/assets/img/" + data.getString("foto");
                    mNama = data.getString("nama");
                    mTtl = data.getString("tempat_lahir") + ", " + data.getString("tanggal_lahir");
                    mAlamat = data.getString("alamat");
                    mJenisKelamin = data.getString("jenis_kelamin");
                    mNomor = data.getString("no_hp");

                    Fragment fragment = ProfilFragment.newInstance(mProfil, mNama, mJenisKelamin, mTtl, mAlamat, mNomor);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                } catch (Exception e) {
                    Toast.makeText(navigationbar.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(navigationbar.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }
    private void loadRiwayat() {
        progressDialog.setMessage("Sedang memproses");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String id = "2";
        String url = ServerAPI.URL_RIWAYAT + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject data = jsonArray.getJSONObject(0);

                    mtanggal = data.getString("tanggal");
                    mpoli = data.getString("poli");
                    mdiagnosa = data.getString("diagnosa");
                    mtindakan = data.getString("tindakan");
                    mobat = data.getString("obat");

                    Fragment fragment = RiwayatFragment.newInstance(mtanggal, mpoli, mdiagnosa, mtindakan, mobat);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                } catch (Exception e) {
                    Toast.makeText(navigationbar.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(navigationbar.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
