package com.example.whereiscat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //로그캣 사용 설정
    private static final String TAG = "MainActivity";
    private FirebaseAuth mFirebaseAuth;  //파이어베이스 인증
    private DatabaseReference mDatabaseRef;  //실시간 데이터 베이스
    private FirebaseUser firebaseUser;
    private StorageReference mStorageRef;


    //객체 선언
    SupportMapFragment mapFragment;
    GoogleMap map;
    Button btn_mypage,btn_addcat;
    File localFile;
    ImageView catPhoto;

    MarkerOptions myMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageRef = FirebaseStorage.getInstance().getReference();


        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("FirebaseLogin");


        //권한 설정
        checkDangerousPermissions();

        //객체 초기화
//        mylocation = findViewById(R.id.mylocation);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_addcat = findViewById(R.id.btn_addcat);


        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        //지도 프래그먼트 설정
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "onMapReady: ");
                map = googleMap;
                LatLng Dongrae = new LatLng(35.20615984627955, 129.0777944773436);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(Dongrae,16));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(Dongrae);
                map.moveCamera(CameraUpdateFactory.newLatLng(Dongrae));
                map.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                    @Override
                    public void onMapClick(LatLng point) {
                        MarkerOptions mOptions = new MarkerOptions();
                        UserLocation location = new UserLocation();

                        // 마커 타이틀
                        mOptions.title("마커 좌표");
                        Double latitude = point.latitude; // 위도
                        Double longitude = point.longitude; // 경도
                        // 마커의 스니펫(간단한 텍스트) 설정
                        mOptions.snippet(location.toString() + ", " + longitude.toString())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ping_cat));
                        // LatLng: 위도 경도 쌍을 나타냄
                        mOptions.position(new LatLng(latitude, longitude));
                        // 마커(핀) 추가
                        map.addMarker(mOptions);

                        btn_addcat.setOnClickListener(new View.OnClickListener() {  //고양이 추가 버튼(마커를 찍어야지만 작동함)
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, AddCatActivity.class);
                                startActivity(intent);
                                UserLocation location = new UserLocation();
                                FirebaseDatabase.getInstance().getReference("Current Location")
                                        .setValue(location).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

                                            UserLocation location = new UserLocation();
                                            location.setIdToken(firebaseUser.getUid());
                                            location.setLatitude(latitude);
                                            location.setLongitude(longitude);

                                            //setValue : database에 insert (삽입) 행위
                                            mDatabaseRef.child("Current Location").child(firebaseUser.getUid()).setValue(location);

//                                            Toast.makeText(MainActivity.this, "Loacation Saved", Toast.LENGTH_SHORT).show();

                                        } else {
//                                            Toast.makeText(MainActivity.this, "Loacation Not Saved", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });

                    }
                });
                map.setMyLocationEnabled(true);
            }
        });
        MapsInitializer.initialize(this);


        ValueEventListener valueEventListener = mDatabaseRef.child("Current Location").addValueEventListener(new ValueEventListener() {  //DB 불러오기 현재 오류남
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserLocation markerLocation = ds.getValue(UserLocation.class);
                    LatLng latLng = new LatLng(markerLocation.getLatitude(), markerLocation.getLongitude());
                    map.addMarker(new MarkerOptions().title(markerLocation.idToken).position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ping_cat)));
                }

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {  //마커 클릭 시 이벤트
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        String token = marker.getTitle();
                        mDatabaseRef.child("Cat Information").child(token).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>(){
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (!task.isSuccessful()) {
                                    Log.e("firebase", "Error getting data", task.getException());
                                }
                                else {
                                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                    Map<String, Object> catinfo = (Map<String, Object>) task.getResult().getValue();
                                    //CatInformation catinfo = (CatInformation) task.getResult().getValue();
                                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                                            MainActivity.this, R.style.BotttomSheetDialogTheme
                                    );
                                    View bottomSheetView = LayoutInflater.from(getApplicationContext())
                                            .inflate(
                                                    R.layout.layout_bottom_sheet,
                                                    (LinearLayout) findViewById(R.id.bottomSheetContainer)
                                            );
                                    TextView catTitle = bottomSheetView.findViewById(R.id.cat_title);
                                    TextView catSpecies = bottomSheetView.findViewById(R.id.cat_description);
                                    TextView catFeature = bottomSheetView.findViewById(R.id.cat_feature);
                                    catPhoto = bottomSheetView.findViewById(R.id.cat_image);
                                    catTitle.setText(catinfo.get("title").toString());
                                    catSpecies.setText(catinfo.get("description").toString());
                                    catFeature.setText(catinfo.get("feature").toString());
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
                                            catPhoto.setImageBitmap(bitmap);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle any errors
                                        }
                                    });
                                    bottomSheetView.findViewById(R.id.buttonShare).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(MainActivity.this, managementActivity.class); //상세보기
                                            startActivity(intent);
                                            bottomSheetDialog.dismiss();
                                        }
                                    });
                                    bottomSheetDialog.setContentView(bottomSheetView);
                                    bottomSheetDialog.show();
                                }
                            }
                        });
                        return false;
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


        });

//        //위치 확인 버튼 기능 추가
//        mylocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                requestMyLocation();
//            }
//        });
//
    }

    private Location getLocationFromAddress(Context context, String address) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;
        Location resLocation = new Location("");
        try {
            addresses = geocoder.getFromLocationName(address, 5);
            if((addresses == null) || (addresses.size() == 0)) {
                return null;
            }
            Address addressLoc = addresses.get(0);
            resLocation.setLatitude(addressLoc.getLatitude());
            resLocation.setLongitude(addressLoc.getLongitude());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resLocation;
    }

    private void requestMyLocation() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            long minTime = 1000;    //갱신 시간
            float minDistance = 0;  //갱신에 필요한 최소 거리

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    showCurrentLocation(location);
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
        String msg = "Latitutde : " + curPoint.latitude
                + "\nLongitude : " + curPoint.longitude;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        //화면 확대, 숫자가 클수록 확대
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 24));

    }

    //------------------권한 설정 시작------------------------
    private void checkDangerousPermissions() {
        String[] permissions = {
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
//            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }



}