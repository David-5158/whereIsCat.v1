package com.example.whereiscat;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

public class managementActivity extends AppCompatActivity {

    Button btn_feed, btn_board, btn_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_feed = findViewById(R.id.btn_feed1);
        btn_board = findViewById(R.id.btn_board1);
        btn_finish = findViewById(R.id.btn_finish1);

        ImageView img1 = (ImageView)findViewById(R.id.empty1); //빈밥그릇
        ImageView img2 = (ImageView)findViewById(R.id.empty2);
        ImageView img3 = (ImageView)findViewById(R.id.empty3);
        ImageView img4 = (ImageView)findViewById(R.id.empty4);
        img1.setImageResource(R.drawable.empty_cat);  //이미지뷰에 이미지를 설정
        img2.setImageResource(R.drawable.empty_cat);
        img3.setImageResource(R.drawable.empty_cat);
        img4.setImageResource(R.drawable.empty_cat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("관리하기");

        //빈밥그릇 클릭시 이미지 변경
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable tempImg = img1.getDrawable();
                Drawable tempRes = getResources().getDrawable(R.drawable.full_cat);
                Bitmap tmpBitmap = ((BitmapDrawable) tempImg).getBitmap();
                Bitmap tmpBitmapRes = ((BitmapDrawable) tempRes).getBitmap();

                if (tmpBitmap.equals(tmpBitmapRes)) {
                    img1.setImageResource(R.drawable.empty_cat);
                    //로직 수행
                } else {
                    img1.setImageResource(R.drawable.full_cat);
                    //로직 수행
                }}});
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable tempImg = img2.getDrawable();
                Drawable tempRes = getResources().getDrawable(R.drawable.full_cat);
                Bitmap tmpBitmap = ((BitmapDrawable) tempImg).getBitmap();
                Bitmap tmpBitmapRes = ((BitmapDrawable) tempRes).getBitmap();

                if (tmpBitmap.equals(tmpBitmapRes)) {
                    img2.setImageResource(R.drawable.empty_cat);
                    //로직 수행
                } else {
                    img2.setImageResource(R.drawable.full_cat);
                    //로직 수행
                }}});
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable tempImg = img3.getDrawable();
                Drawable tempRes = getResources().getDrawable(R.drawable.full_cat);
                Bitmap tmpBitmap = ((BitmapDrawable) tempImg).getBitmap();
                Bitmap tmpBitmapRes = ((BitmapDrawable) tempRes).getBitmap();

                if (tmpBitmap.equals(tmpBitmapRes)) {
                    img3.setImageResource(R.drawable.empty_cat);
                    //로직 수행
                } else {
                    img3.setImageResource(R.drawable.full_cat);
                    //로직 수행
                }}});
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable tempImg = img4.getDrawable();
                Drawable tempRes = getResources().getDrawable(R.drawable.full_cat);
                Bitmap tmpBitmap = ((BitmapDrawable) tempImg).getBitmap();
                Bitmap tmpBitmapRes = ((BitmapDrawable) tempRes).getBitmap();

                if (tmpBitmap.equals(tmpBitmapRes)) {
                    img4.setImageResource(R.drawable.empty_cat);
                    //로직 수행
                } else {
                    img4.setImageResource(R.drawable.full_cat);
                    //로직 수행
                }}});


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}