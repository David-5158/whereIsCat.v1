package com.example.whereiscat;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class boardActivity extends AppCompatActivity {

    private EditText title, content;
    Button btnPost;

    ListView board_list;

    private FirebaseAuth mFirebaseAuth;  //파이어베이스 인증
    private DatabaseReference mDatabaseRef;  //실시간 데이터 베이스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        title = findViewById(R.id.write_title);
        content = findViewById(R.id.write_content);
        btnPost = findViewById(R.id.btn_update);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("FirebaseLogin");

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board board = new Board();
                String UserTitle = title.getText().toString();
                String UserContent = content.getText().toString();

                FirebaseDatabase.getInstance().getReference("Board")
                        .setValue(board).addOnCompleteListener(new OnCompleteListener<Void>(){
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

                                    Board board = new Board();
                                    board.setTitle(UserTitle);
                                    board.setContent(UserContent);

                                    mDatabaseRef.child("User Board").child(firebaseUser.getUid()).setValue(board);
                                }
                            }

                });
            }
        });

    }

}
