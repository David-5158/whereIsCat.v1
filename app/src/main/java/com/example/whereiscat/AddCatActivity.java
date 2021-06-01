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

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;

public class AddCatActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private ImageView imageView;
    private EditText cat_nickname, cat_species, cat_feature, cat_neut;  //닉네임, 추정종, 특징
    private FirebaseAuth mFirebaseAuth;  //파이어베이스 인증
    private DatabaseReference mDatabaseRef;  //실시간 데이터 베이스
    Button neut_yes, neut_no, neut_what, cat_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcat);

        cat_nickname = findViewById(R.id.cat_nickname);
        cat_species = findViewById(R.id.cat_species);
        cat_feature = findViewById(R.id.cat_feature);

        neut_yes = findViewById(R.id.neut_yes);
        neut_no = findViewById(R.id.neut_no);
        neut_what = findViewById(R.id.neut_what);
        cat_finish = findViewById(R.id.cat_finish);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("FirebaseLogin");


        cat_finish.setOnClickListener(new View.OnClickListener() {  //고양이 저장 버튼 눌렀을 때 DB에 입력 값 저장
            @Override
            public void onClick(View v) {
                String strNickname = cat_nickname.getText().toString(); //닉네임
                String strSpecies = cat_species.getText().toString();   //추정종
                String strFeature = cat_feature.getText().toString();   //특징
                String btnYes = neut_yes.getText().toString();
                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                mDatabaseRef.child("Current Location").child(firebaseUser.getUid()).child("information").push().setValue(strNickname);
                mDatabaseRef.child("Current Location").child(firebaseUser.getUid()).child("information").push().setValue(strSpecies);
                mDatabaseRef.child("Current Location").child(firebaseUser.getUid()).child("information").push().setValue(strFeature);
                Intent intent = new Intent(AddCatActivity.this, MainActivity.class); // 저장 후 엑티비티 종료
                startActivity(intent);
                finish(); //현재 액티비티 파괴
            }
        });

        imageView = findViewById(R.id.image);

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