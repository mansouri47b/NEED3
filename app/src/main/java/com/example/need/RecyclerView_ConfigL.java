package com.example.need;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class RecyclerView_ConfigL{
    private Context mContextL ;
    private LocalisationsAdapter mLocalisationsAdapter  ;
    public void setConfigL(RecyclerView recyclerView , Context context , List<Localisation> localisations , List<String> keys){
        mContextL = context;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mLocalisationsAdapter = new LocalisationsAdapter(localisations ,keys);
        recyclerView.setAdapter(mLocalisationsAdapter);
    }

    class LocalisationTtemView extends RecyclerView.ViewHolder{
        private TextView Longétude ;
        private TextView Attitude;

        private String key ;
        public LocalisationTtemView(ViewGroup parent){
            super(LayoutInflater.from(mContextL).
                    inflate(R.layout.localisation_list_item, parent , false));
            Longétude = (TextView) itemView.findViewById(R.id.Longétude);
            Attitude = (TextView) itemView.findViewById(R.id.Attitude);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContextL, LocalisationDetailsActivity.class);

                    intent.putExtra("key",key);
                    intent.putExtra("Longétude",Longétude.getText().toString());
                    intent.putExtra("Attitude",Attitude.getText().toString());

                    mContextL.startActivity(intent);

                }
            });
        }
        public void bindL(Localisation localisation , String key ){
            Longétude.setText(localisation.getLongétude());
            Attitude.setText(localisation.getAttitude());
            this.key = key ;
        }
    }
    class LocalisationsAdapter extends RecyclerView.Adapter<LocalisationTtemView>{
        private List<Localisation> mLocalisationList;
        private List<String> Lkeys;

        public LocalisationsAdapter(List<Localisation> mLocalisationList, List<String> Lkeys) {
            this.mLocalisationList = mLocalisationList;
            this.Lkeys = Lkeys;
        }
        @NonNull
        @Override
        public LocalisationTtemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LocalisationTtemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LocalisationTtemView holder, int position) {
            holder.bindL(mLocalisationList.get(position), Lkeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mLocalisationList.size();
        }
    }
}
