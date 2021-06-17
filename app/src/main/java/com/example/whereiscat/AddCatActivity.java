package com.example.whereiscat;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Hashtable;

public class AddCatActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private ImageView imageView;
    private EditText title, description, feature;  //닉네임, 추정종, 특징
    private FirebaseAuth mFirebaseAuth;  //파이어베이스 인증
    private DatabaseReference mDatabaseRef;  //실시간 데이터 베이스
    Button neut_yes, neut_no, neut_what, cat_finish;
    Bitmap bitmap;
    GoogleMap map;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcat);

        mStorageRef = FirebaseStorage.getInstance().getReference();



        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        feature = findViewById(R.id.feature);
        
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
//                            setValue : database에 insert (삽입) 행위
                            mDatabaseRef.child("Cat Information").child(firebaseUser.getUid()).setValue(information);

//                                            Toast.makeText(MainActivity.this, "Loacation Saved", Toast.LENGTH_SHORT).show();

                        } else {
//                                            Toast.makeText(MainActivity.this, "Loacation Not Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent = new Intent(AddCatActivity.this, MainActivity.class); // 저장 후 엑티비티 종료
                startActivity(intent);
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

    public void uploadImage(){  //DB에 사진 업로드
        StorageReference mountainsRef = mStorageRef.child("user").child("email"+".jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                String photoUrl = String.valueOf(downloadUrl);
                Log.d("url", photoUrl);

                Hashtable<String, String> profile = new Hashtable<String,String>();
                profile.put("photo", photoUrl);

//                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//                mDatabaseRef.child("Cat Information").child(firebaseUser.getUid()).setValue(profile);


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

                    bitmap = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(bitmap);
                    uploadImage();
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }


    }}