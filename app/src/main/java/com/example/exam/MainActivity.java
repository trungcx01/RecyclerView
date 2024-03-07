package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exam.models.SpinnerAdapter;
import com.example.exam.models.Tour;
import com.example.exam.models.TourAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner sp;
    private RecyclerView recyclerView;
    private TourAdapter adapter;
    private EditText eName, eTime;
    private SearchView searchView;
    private Button btnAdd, btnUpdate;
    private int currentPosition;
    private int[] images = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        adapter = new TourAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        adapter.setListener(new TourAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                btnUpdate.setEnabled(true);
                currentPosition = position;
                Tour tour = adapter.getItem(position);
                int img = tour.getImgTransport();
                for (int i = 0; i < images.length; i++){
                    if (images[i] == img){
                        sp.setSelection(i);
                        break;
                    }
                }
                eName.setText(tour.getName());
                eTime.setText(tour.getTime());
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                try{
                    tour.setName(eName.getText().toString());
                    tour.setTime(eTime.getText().toString());
                    tour.setImgTransport(images[Integer.parseInt(sp.getSelectedItem().toString())]);
                    adapter.add(tour);
                    eName.setText("");
                    eTime.setText("");
                    sp.setSelection(0);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                try{
                    tour.setName(eName.getText().toString());
                    tour.setTime(eTime.getText().toString());
                    tour.setImgTransport(images[Integer.parseInt(sp.getSelectedItem().toString())]);
                    adapter.update(currentPosition, tour);
                    btnUpdate.setEnabled(false);
                    btnAdd.setEnabled(true);
                    eName.setText("");
                    eTime.setText("");
                    sp.setSelection(0);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Tour> filter = new ArrayList<>();
                for (Tour x : adapter.getTourListBackup()){
                    if (x.getName().toLowerCase().contains(s.toLowerCase())){
                        filter.add(x);
                    }
                }
                if (filter.isEmpty()){
                    adapter.filterList(filter);
                    Toast.makeText(getApplicationContext(), "Not Found Data", Toast.LENGTH_SHORT).show();
                }
                else{
                    adapter.filterList(filter);
                }
                return false;
            }
        });

    }

    private void initView(){
        sp = findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);

        recyclerView = findViewById(R.id.recyclerView);
        eName = findViewById(R.id.eName);
        eTime = findViewById(R.id.eTime);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.searchView);
    }
}