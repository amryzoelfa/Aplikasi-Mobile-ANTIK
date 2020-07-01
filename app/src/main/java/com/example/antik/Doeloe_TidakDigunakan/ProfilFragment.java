package com.example.antik.Doeloe_TidakDigunakan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.antik.EditProfilActivity;
import com.example.antik.R;
import com.squareup.picasso.Picasso;

public class ProfilFragment extends Fragment {
    private static final String FOTO_PENGGUNA = "FOTO_PENGGUNA";
    private static final String NAMA = "NAMA";
    private static final String JENIS_KELAMIN = "JENIS_KELAMIN";
    private static final String TTL = "TTL";
    private static final String ALAMAT = "ALAMAT";
    private static final String NO_HP = "NO_HP";

    ImageView profil;
    TextView nama, alamat, jenisKelamin, tempatTanggalLahir;

    Button gantiPass, editProfil;

    String mProfil, mNama, mAlamat, mJenisKelamin, mTtl, mNomor;

    public static ProfilFragment newInstance(String foto, String nama, String jenisKelamin, String ttl, String alamat, String no) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putString(FOTO_PENGGUNA, foto);
        args.putString(NAMA, nama);
        args.putString(JENIS_KELAMIN, jenisKelamin);
        args.putString(TTL, ttl);
        args.putString(ALAMAT, alamat);
        args.putString(NO_HP, no);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProfil = getArguments().getString(FOTO_PENGGUNA);
            mNama = getArguments().getString(NAMA);
            mAlamat = getArguments().getString(ALAMAT);
            mNomor = getArguments().getString(NO_HP);
            mJenisKelamin = getArguments().getString(JENIS_KELAMIN);
            mTtl = getArguments().getString(TTL);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil,container,false);

        nama = v.findViewById(R.id.textView2);
        alamat = v.findViewById(R.id.textView4);
        jenisKelamin = v.findViewById(R.id.txt_bb);
        tempatTanggalLahir = v.findViewById(R.id.txt_alamat);
        profil = v.findViewById(R.id.imageView2);
        gantiPass = v.findViewById(R.id.btnGantiPass);
        editProfil = v.findViewById(R.id.button);

        Picasso.get().load(mProfil).into(profil);
        nama.setText(mNama);
        alamat.setText(mAlamat);
        jenisKelamin.setText(mJenisKelamin);
        tempatTanggalLahir.setText(mTtl);

        Toast.makeText(getActivity(), mProfil+mNama+mAlamat+mTtl, Toast.LENGTH_LONG).show();

        gantiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toUpdatePass = new Intent(getActivity(), GantiPass.class);
                getActivity().startActivity(toUpdatePass);
            }
        });

        editProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEditProfil = new Intent(getActivity(), EditProfilActivity.class);
                getActivity().startActivity(toEditProfil);
            }
        });

        return  v;
    }


}
