package com.example.shawnli.pickupball;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shawnli.pickupball.Model.Court;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView listRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return false;
    }

    private void init(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        List<Court> courts = Single.getInstance().getCourts();
        listRecyclerView = (RecyclerView) findViewById(R.id.listRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CourtAdapter(courts);
        listRecyclerView.setAdapter(mAdapter);
    }
}
