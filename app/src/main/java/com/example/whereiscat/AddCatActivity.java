package com.example.whereiscat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;

public class AddCatActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private ImageView imageView;
    private EditText title, description, feature;  //닉네임, 추정종, 특징
    private FirebaseAuth mFirebaseAuth;  //파이어베이스 인증
    private DatabaseReference mDatabaseRef;  //실시간 데이터 베이스
    Button neut_yes, neut_no, neut_what, cat_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcat);


        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        feature = findViewById(R.id.feature);


        neut_yes = findViewById(R.id.neut_yes);
        neut_no = findViewById(R.id.neut_no);
        neut_what = findViewById(R.id.neut_what);
        cat_finish = findViewById(R.id.cat_finish);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("FirebaseLogin");


        cat_finish.setOnClickListener(new View.OnClickListener() {  //고양이 저장 버튼 눌렀을 때 DB에 입력 값 저장
            @Override
            public void onClick(View v) {
                CatInformation information = new CatInformation();
                String catTitle = title.getText().toString();
                String catDescription = description.getText().toString();
                String catFeature = feature.getText().toString();

                FirebaseDatabase.getInstance().getReference("Cat Information")
                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

                            CatInformation information = new CatInformation();
                            information.setIdToken(firebaseUser.getUid());
                            information.setTitle(catTitle);
                            information.setDescription(catDescription);
                            information.setFeature(catFeature);

                            //setValue : database에 insert (삽입) 행위
                            mDatabaseRef.child("Cat Information").child(firebaseUser.getUid()).setValue(information);

//                                            Toast.makeText(MainActivity.this, "Loacation Saved", Toast.LENGTH_SHORT).show();

                        } else {
//                                            Toast.makeText(MainActivity.this, "Loacation Not Saved", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
                Intent intent = new Intent(AddCatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imageView = findViewById(R.id.imageView2);

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }


    }}