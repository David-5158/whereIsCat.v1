package com.example.whereiscat;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class managementActivity2 extends AppCompatActivity {

    private Button sendbt1;
    private EditText editdt1, editdt2;
    public String msg1, msg2, msg3;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;
    private ChildEventListener mChildEventListener;
    private StorageReference mStorageRef;

    private ListView listView1;
    private ArrayAdapter<String> adapter1;
    List<Object> Array = new ArrayList<Object>();
    File localFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management2);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        ImageView photo22 = findViewById(R.id.cat_image1);



        sendbt1 = (Button) findViewById(R.id.button3);
        editdt1 = (EditText) findViewById(R.id.editText5);
        editdt2 =  (EditText) findViewById(R.id.editText6);
        listView1 = (ListView) findViewById(R.id.manage_list);

        initDatabase1();

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView1.setAdapter(adapter1);

        sendbt1.setOnClickListener((v) -> {
            msg1 = editdt1.getText().toString();
            msg2 = editdt2.getText().toString();

            TimeZone timezone1 = TimeZone.getTimeZone("Etc/GMT-9");
            TimeZone.setDefault(timezone1);

            SimpleDateFormat formater1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
            Date current = new Date();

            String mtime1 = formater1.format(current);

            mDatabaseRef.push().setValue(msg3);

            msg3 = ("밥"+msg1+",    ") + ("물"+msg2) + ("     "+ mtime1);

        });
        try {   //파이어베이스에서 이미지 파일 불러오기
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference mountainsRef = mStorageRef.child("user").child("email"+".jpg");

        mountainsRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                photo22.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        mDatabaseRef = mFirebaseDatabase.getReference("manage");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter1.clear();

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {

                    String msg4 = messageData.getValue().toString();
                    Array.add(msg4);
                    adapter1.add(msg4);

                }
                adapter1.notifyDataSetChanged();
                listView1.setSelection(adapter1.getCount() - 1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void initDatabase1() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseRef = mFirebaseDatabase.getReference("log3");
        mDatabaseRef.child("log3").setValue("check3");

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

    public class MyGalleryAdapter extends BaseAdapter {
        Context context;
        Integer[] catID = {R.drawable.cat_1, R.drawable.cat_2, R.drawable.cat_3, R.drawable.cat_4 };
        public MyGalleryAdapter(Context c) { context = c; }
        public int getCount(){ return catID.length; }
        public Object getItem(int arg0){ return null; }
        public long getItemId(int position) { return 0; }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(500, 500));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(catID[position]);

            return imageview;

        }
    }}
