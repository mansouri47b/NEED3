package com.example.need;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseDatabaseHelperL {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceLocalisations;
    private List<Localisation> localisations = new ArrayList<Localisation>();




    public interface DataStatus{
        void DataIsLoadedL(List<Localisation> localisations ,List<String> keys );
        void DataIsInsertedL();
        void DataIsUpdatedL();
        void DataIsDeletedL() ;
    }

    public FirebaseDatabaseHelperL() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceLocalisations = mDatabase.getReference("Points des ventes");
    }
    public void ReadLocalisations(final DataStatus dataStatus){
        mReferenceLocalisations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                localisations.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Localisation localisation = keyNode.getValue(Localisation.class);
                    localisations.add(localisation);
                }
                dataStatus.DataIsLoadedL(localisations,keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void addLocalisations(Localisation localisation ,final DataStatus dataStatus){
        String key = mReferenceLocalisations.push().getKey();
        mReferenceLocalisations.child(key).setValue(localisation)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInsertedL();
                    }
                });
    }
    public void updateLocalisation(String key ,Localisation localisation ,final DataStatus dataStatus){
        mReferenceLocalisations.child(key).setValue(localisation)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdatedL();                    }
                });
    }
    public void deleteLocalisation(String key , final DataStatus dataStatus){
        mReferenceLocalisations.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeletedL();
                    }
                });

    }


}
