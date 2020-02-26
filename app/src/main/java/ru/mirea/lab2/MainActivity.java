package ru.mirea.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.OnNoteListener {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Item> r;
    private final String TAG = "gg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        Intent i = getIntent();
        r = i.getParcelableArrayListExtra("listOfItems");
        adapter = new Adapter(this, r, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "clicked");
        Intent i = new Intent(this, ViewPagerActivity.class);
        i.putParcelableArrayListExtra("listOfItems", r);
        i.putExtra("Position", position);
        startActivity(i);
    }
}
