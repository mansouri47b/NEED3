package com.example.need;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseDatabaseHalper  {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Produit> produits = new ArrayList<>();
    public interface DataStatus{
        void DataIsLoaded(List<Produit> produits ,List<String> keys );
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHalper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("Produit");
    }
    public void ReadProduits(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                produits.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Produit produit = keyNode.getValue(Produit.class);
                    produits.add(produit);
                }
                dataStatus.DataIsLoaded(produits,keys);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addProduit(Produit produit ,final DataStatus dataStatus){
        String key = mReference.push().getKey();
        mReference.child(key).setValue(produit)
              .addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void aVoid) {
                      dataStatus.DataIsInserted();
                  }
              });
    }
    public void updateProduit(String key ,Produit produit ,final DataStatus dataStatus) {
        mReference.child(key).setValue(produit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }
    public void deleteProduit(String key, Produit produitD, final DataStatus dataStatus){
        mReference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });



    }

}
