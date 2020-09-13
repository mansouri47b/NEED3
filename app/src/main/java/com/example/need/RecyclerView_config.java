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

public class RecyclerView_config{
   private Context mContext ;
   public ProduitsAdapter mProduitsAdapter  ;

   public void setConfig(RecyclerView recyclerView , Context context , List<Produit> produits , List<String> keys){
       mContext = context;
       mProduitsAdapter = new ProduitsAdapter(produits ,keys);
       
       recyclerView.setAdapter(mProduitsAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(context));
   }

   class ProduitTtemView extends RecyclerView.ViewHolder{
       private TextView mNuméro;
       private TextView mNom_produit;
       private TextView mQuantité;
       private TextView mPrix;

       private String key ;
       public ProduitTtemView(ViewGroup parent){
           super(LayoutInflater.from(mContext).
                   inflate(R.layout.produit_list_item, parent , false));
           mNuméro = (TextView) itemView.findViewById(R.id.Numéro);
           mNom_produit = (TextView) itemView.findViewById(R.id.Nom_produit);
           mQuantité = (TextView) itemView.findViewById(R.id.Quantité);
           mPrix = (TextView) itemView.findViewById(R.id.Prix);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(mContext, updateProduitActivity.class);
                   Intent intent1 = new Intent(mContext, deleteProduitActivity.class);
                   intent.putExtra("key",key);
                   intent.putExtra("Numéro",mNuméro.getText().toString());
                   intent.putExtra("Nom produit",mNom_produit.getText().toString());
                   intent.putExtra("Quantité",mQuantité.getText().toString());
                   intent.putExtra("Prix",mPrix.getText().toString());

                   intent1.putExtra("key",key);
                   intent1.putExtra("Numéro",mNuméro.getText().toString());
                   intent1.putExtra("Nom produit",mNom_produit.getText().toString());
                   intent1.putExtra("Quantité",mQuantité.getText().toString());
                   intent1.putExtra("Prix",mPrix.getText().toString());


                   mContext.startActivity(intent);
                   mContext.startActivity(intent1);
               }
           });
       }
       public void bind(Produit produit , String key ){
           mNuméro.setText(produit.getNuméro());
           mNom_produit.setText(produit.getNom_produit());
           mQuantité.setText(produit.getQuantité());
           mPrix.setText(produit.getPrix());
           this.key = key ;
       }
   }
   class ProduitsAdapter extends RecyclerView.Adapter<ProduitTtemView>{
       private List<Produit> mProduitList;
       private List<String> mkeys;

       public ProduitsAdapter(List<Produit> mProduitList, List<String> mkeys) {
           this.mProduitList = mProduitList;
           this.mkeys = mkeys;
       }

       @NonNull
       @Override
       public ProduitTtemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           return new ProduitTtemView(parent);
       }

       @Override
       public void onBindViewHolder(@NonNull ProduitTtemView holder, int position) {
           holder.bind(mProduitList.get(position), mkeys.get(position));
       }

       @Override
       public int getItemCount() {
           return mProduitList.size();
       }
   }


}
