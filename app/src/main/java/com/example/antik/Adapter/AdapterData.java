package com.example.antik.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.antik.Model.ModelRiwayat;
import com.example.antik.R;

import java.util.List;


public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelRiwayat> mItems ;
    private Context context;

    public AdapterData(Context context, List<ModelRiwayat> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelRiwayat md  = mItems.get(position);
        holder.tvtanggal_periksa.setText(md.getTanggal_periksa());
        holder.tvpoli.setText(md.getPoli());
        holder.tvdiagnosa.setText(md.getDiagnosa());
        holder.tvtindakan.setText(md.getTindakan());
        holder.tvobat.setText(md.getObat());

        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvtanggal_periksa,tvpoli,tvdiagnosa, tvtindakan, tvobat;
        ModelRiwayat md;

        public  HolderData (View view)
        {
            super(view);

            tvtanggal_periksa = (TextView) view.findViewById(R.id.tanggal_periksa);
            tvpoli = (TextView) view.findViewById(R.id.poli);
            tvdiagnosa = (TextView) view.findViewById(R.id.diagnosa);
            tvtindakan = (TextView) view.findViewById(R.id.tindakan);
            tvobat = (TextView) view.findViewById(R.id.obat);

        }
    }
}
