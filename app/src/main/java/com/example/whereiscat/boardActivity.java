package com.example.whereiscat;


import android.os.Bundle;
<<<<<<< HEAD
import android.widget.ArrayAdapter;
=======
import android.view.View;
>>>>>>> 0cbb848997aa5c8f4809dbfe5fffd167ffd55fb8
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
<<<<<<< HEAD
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
=======
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
>>>>>>> 0cbb848997aa5c8f4809dbfe5fffd167ffd55fb8

public class boardActivity extends AppCompatActivity {

    private Button sendbt;
    private EditText editdt;
    public String msg;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;
    private ChildEventListener mChildEventListener;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();



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
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("message"); // 변경값을 확인할 child 이름

        sendbt.setOnClickListener((v) -> {
          msg = editdt.getText().toString();
          mDatabaseRef.child("message").push().setValue(msg);
        });

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


