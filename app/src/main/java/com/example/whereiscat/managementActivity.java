package com.example.whereiscat;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class managementActivity extends AppCompatActivity {

    Button btn_feed, btn_board, btn_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

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

        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);

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
