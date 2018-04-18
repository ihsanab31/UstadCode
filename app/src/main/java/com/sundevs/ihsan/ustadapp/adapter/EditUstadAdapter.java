package com.sundevs.ihsan.ustadapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sundevs.ihsan.ustadapp.App;
import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.adapter.listener.ItemClickListener;
import com.sundevs.ihsan.ustadapp.adapter.model.UstadModel;
import com.sundevs.ihsan.ustadapp.util.param.URL;
import com.sundevs.ihsan.ustadapp.view.activity.DetailActivity;
import com.sundevs.ihsan.ustadapp.view.activity.InputUstadActivity;

import java.util.List;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on 18/04/2018
 * ------------------------------
 * This class for
 */
public class EditUstadAdapter extends RecyclerView.Adapter<EditUstadAdapter.ViewHolder>{
    private List<UstadModel> ustadModel;
    private List<UstadModel> ustadkModel;
    private Context mContext;
    ImageLoader imageLoader;

    public EditUstadAdapter(List<UstadModel> ustadModel, Context mContext) {
        this.ustadModel = ustadModel;
        this.mContext = mContext;
    }

    @Override
    public EditUstadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditUstadAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(EditUstadAdapter.ViewHolder holder, int position) {
        final UstadModel ustadModels = ustadModel.get(position);
        holder.bindTo(ustadModels);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, InputUstadActivity.class);
                intent.putExtra("id_ustad", ustadModels.getId());
                intent.putExtra("nama", ustadModels.getNama());
                intent.putExtra("harga", ustadModels.getHarga());
                intent.putExtra("bidang", ustadModels.getBidang());
                intent.putExtra("no_hp", ustadModels.getNohp());
                intent.putExtra("email", ustadModels.getEmail());
                intent.putExtra("alamat", ustadModels.getAlamat());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ustadModel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemClickListener itemClickListener;
        //Member Variables for the TextViews
        private TextView nama;
        private TextView alamat;
        private TextView harga;
        private TextView bidang;
        private NetworkImageView imageView;
        private UstadModel ustadModel;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);
            //Initialize the views
            imageView = (NetworkImageView) itemView.findViewById(R.id.foto_ustad);
            nama = (TextView) itemView.findViewById(R.id.nama_list);
            alamat = (TextView) itemView.findViewById(R.id.alamat_list);
            bidang = (TextView) itemView.findViewById(R.id.bidang_list);
            harga = (TextView) itemView.findViewById(R.id.hargalist);
            itemView.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        void bindTo(UstadModel ustadModel) {
            if (imageLoader == null)
                imageLoader = App.getInstance().getImageLoader();
            //Populate the textviews with data
            imageView.setImageUrl(URL.URL_IMAGE + ustadModel.getGambar(), imageLoader);
            nama.setText(ustadModel.getNama());
            alamat.setText(ustadModel.getAlamat());
            bidang.setText(ustadModel.getAlamat());
            harga.setText(ustadModel.getAlamat());

            //Get the current sport
            this.ustadModel = ustadModel;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v);
        }
    }

}
