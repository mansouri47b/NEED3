package com.example.need;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProduitListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView ;

    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.poduit_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_produits);
        new FirebaseDatabaseHalper().ReadProduits(new FirebaseDatabaseHalper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Produit> produits, List<String> keys) {
                new RecyclerView_config().setConfig(mRecyclerView, ProduitListActivity.this
                        ,produits , keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

}
