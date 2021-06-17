package com.example.whereiscat;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class boardActivity extends AppCompatActivity {

    private Button sendbt;
    private EditText editdt;
    public String msg, nsg;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;
    private ChildEventListener mChildEventListener;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        sendbt = (Button) findViewById(R.id.button2);
        editdt = (EditText) findViewById(R.id.editText1);
        listView = (ListView) findViewById(R.id.board_list);

        initDatabase();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);

        sendbt.setOnClickListener((v) -> {
                TimeZone timezone = TimeZone.getTimeZone("Etc/GMT-9");
                TimeZone.setDefault(timezone);

                SimpleDateFormat formater = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
                Date current = new Date();

                String mtime = formater.format(current);

              msg = editdt.getText().toString();
              nsg = msg +"     "+mtime;
              mDatabaseRef.push().setValue(nsg);


        });

        mDatabaseRef = mFirebaseDatabase.getReference("message");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {

                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter.add(msg2);

                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount() - 1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void initDatabase() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseRef = mFirebaseDatabase.getReference("log");
        mDatabaseRef.child("log").setValue("check");

        mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseRef.addChildEventListener(mChildEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mChildEventListener);
    }



}


