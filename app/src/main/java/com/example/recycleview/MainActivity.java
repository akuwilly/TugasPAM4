package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;
    EditText etNim, etNama;
    Button btnSimpan;
    ArrayList<Mahasiswa> data;
    MahasiswaAdapter adapter;
    public static String TAG = "RV1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = findViewById(R.id.rv1);
        etNim = findViewById(R.id.editTextTextPersonName2);
        etNama = findViewById(R.id.etNim);
        btnSimpan = findViewById(R.id.bt1);

        data = getData();
        adapter = new MahasiswaAdapter(this, data);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();
                if (!nim.isEmpty() && !nama.isEmpty()) {
                    Mahasiswa mhs = new Mahasiswa();
                    mhs.nim = nim;
                    mhs.nama = nama;

                    // Tambahkan item baru di posisi 0
                    data.add(0, mhs);
                    adapter.notifyItemInserted(0); // Notifikasi adapter
                    rv1.scrollToPosition(0); // Gulir ke posisi paling atas
                    etNim.setText(""); // Clear input
                    etNama.setText(""); // Clear input
                }
            }
        });

    }

    public ArrayList<Mahasiswa> getData() {
        ArrayList<Mahasiswa> data = new ArrayList<>();
        List<String> nim = Arrays.asList(getResources().getStringArray(R.array.nim));
        List<String> nama = Arrays.asList(getResources().getStringArray(R.array.nama));
        for (int i = 0; i < nim.size(); i++) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = nim.get(i);
            mhs.nama = nama.get(i);
            Log.d(TAG, "getData " + mhs.nim);
            data.add(mhs);
        }
        return data;
    }
}