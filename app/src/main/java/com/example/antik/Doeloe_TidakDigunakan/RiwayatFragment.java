package com.example.antik.Doeloe_TidakDigunakan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.antik.R;

public class RiwayatFragment extends Fragment {
    private static final String Tanggal = "Tanggal";
    private static final String Poli = "Poli";
    private static final String Diagnosa = "Diagnosa";
    private static final String Tindakan = "Tindakan";
    private static final String Obat = "Obat";

    TextView tanggal , poli, diagnosa, tindakan, obat;
    String mtanggal, mpoli, mdiagnosa, mtindakan, mobat;
    public static RiwayatFragment newInstance(String TGL, String POLI, String DIAGNOSA, String TINDAKAN, String OBAT){
        RiwayatFragment fragment = new RiwayatFragment();
        Bundle args = new Bundle();
        args.putString(Tanggal, TGL);
        args.putString(Poli, POLI);
        args.putString(Diagnosa, DIAGNOSA);
        args.putString(Tindakan, TINDAKAN);
        args.putString(Obat, OBAT);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mtanggal = getArguments().getString(Tanggal);
            mpoli = getArguments().getString(Poli);
            mdiagnosa = getArguments().getString(Diagnosa);
            mtindakan = getArguments().getString(Tindakan);
            mobat = getArguments().getString(Obat);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil,container,false);

        tanggal = v.findViewById(R.id.txttanggal);
        poli = v.findViewById(R.id.txtpoli);
        diagnosa = v.findViewById(R.id.txtdiagnosa);
        tindakan = v.findViewById(R.id.txttindakan);
        obat = v.findViewById(R.id.txtobat);

        tanggal.setText(mtanggal);
        poli.setText(mpoli);
        diagnosa.setText(mdiagnosa);
        tindakan.setText(mtindakan);
        obat.setText(mobat);

        Toast.makeText(getActivity(), mpoli+mdiagnosa+mtindakan+mobat, Toast.LENGTH_LONG).show();

        return  v;
    }
}
