package com.example.need;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocalisationListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewL ;
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        mRecyclerViewL = (RecyclerView) findViewById(R.id.recyclerview_localisations);
        setContentView(R.layout.activity_localisation_list);
        mRecyclerViewL.setHasFixedSize(true);
        new FirebaseDatabaseHelperL().ReadLocalisations(new FirebaseDatabaseHelperL.DataStatus() {
            @Override
            public void DataIsLoadedL(List<Localisation> localisations, List<String> keys) {
                new RecyclerView_ConfigL().setConfigL(mRecyclerViewL ,LocalisationListActivity.this
                        ,localisations , keys);
            }

            @Override
            public void DataIsInsertedL() {

            }

            @Override
            public void DataIsUpdatedL() {

            }

            @Override
            public void DataIsDeletedL() {

            }
        });
    }

}
