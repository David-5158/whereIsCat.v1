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

        ImageView img1 = (ImageView)findViewById(R.id.empty_feed1); //빈밥그릇
        ImageView img2 = (ImageView)findViewById(R.id.empty_feed2);
        ImageView img3 = (ImageView)findViewById(R.id.empty_feed3);
        ImageView img4 = (ImageView)findViewById(R.id.empty_feed4);
        img1.setImageResource(R.drawable.empty_cat);  //이미지뷰에 이미지를 설정
        img2.setImageResource(R.drawable.empty_cat);
        img3.setImageResource(R.drawable.empty_cat);
        img4.setImageResource(R.drawable.empty_cat);

        ImageView img5 = (ImageView)findViewById(R.id.empty_snack1); //빈간식
        ImageView img6 = (ImageView)findViewById(R.id.empty_snack2);
        ImageView img7 = (ImageView)findViewById(R.id.empty_snack3);
        ImageView img8 = (ImageView)findViewById(R.id.empty_snack4);
        img5.setImageResource(R.drawable.empty_snack);  //이미지뷰에 이미지를 변경
        img6.setImageResource(R.drawable.empty_snack);
        img7.setImageResource(R.drawable.empty_snack);
        img8.setImageResource(R.drawable.empty_snack);

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
        //빈 간식 클릭시 이미지 변경
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable tempImg = img5.getDrawable();
                Drawable tempRes = getResources().getDrawable(R.drawable.full_snack);
                Bitmap tmpBitmap = ((BitmapDrawable) tempImg).getBitmap();
                Bitmap tmpBitmapRes = ((BitmapDrawable) tempRes).getBitmap();

                if (tmpBitmap.equals(tmpBitmapRes)) {
                    img5.setImageResource(R.drawable.empty_snack);
                    //로직 수행
                } else {
                    img5.setImageResource(R.drawable.full_snack);
                    //로직 수행
                }}});
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable tempImg = img6.getDrawable();
                Drawable tempRes = getResources().getDrawable(R.drawable.full_snack);
                Bitmap tmpBitmap = ((BitmapDrawable) tempImg).getBitmap();
                Bitmap tmpBitmapRes = ((BitmapDrawable) tempRes).getBitmap();

                if (tmpBitmap.equals(tmpBitmapRes)) {
                    img6.setImageResource(R.drawable.empty_snack);
                    //로직 수행
                } else {
                    img6.setImageResource(R.drawable.full_snack);
                    //로직 수행
                }}});
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable tempImg = img7.getDrawable();
                Drawable tempRes = getResources().getDrawable(R.drawable.full_snack);
                Bitmap tmpBitmap = ((BitmapDrawable) tempImg).getBitmap();
                Bitmap tmpBitmapRes = ((BitmapDrawable) tempRes).getBitmap();

                if (tmpBitmap.equals(tmpBitmapRes)) {
                    img7.setImageResource(R.drawable.empty_snack);
                    //로직 수행
                } else {
                    img7.setImageResource(R.drawable.full_snack);
                    //로직 수행
                }}});
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable tempImg = img8.getDrawable();
                Drawable tempRes = getResources().getDrawable(R.drawable.full_snack);
                Bitmap tmpBitmap = ((BitmapDrawable) tempImg).getBitmap();
                Bitmap tmpBitmapRes = ((BitmapDrawable) tempRes).getBitmap();

                if (tmpBitmap.equals(tmpBitmapRes)) {
                    img8.setImageResource(R.drawable.empty_snack);
                    //로직 수행
                } else {
                    img8.setImageResource(R.drawable.full_snack);
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