package com.example.whereiscat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
<<<<<<< HEAD
<<<<<<< HEAD
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
=======
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
>>>>>>> 5b4d977d40c8547227afe777de2d281af5d84150
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //로그캣 사용 설정
    private static final String TAG = "MainActivity";

    private FirebaseAuth mFirebaseAuth;  //파이어베이스 인증
    private DatabaseReference mDatabaseRef;  //실시간 데이터 베이스
    //객체 선언
    SupportMapFragment mapFragment;
    GoogleMap map;
    Button mylocation, check,btn_mypage,btn_addcat ;
    EditText editText;

    MarkerOptions myMarker;
=======
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
>>>>>>> 0b2f5120cc3d9cd2749925eb85579a74610cab1d

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        //권한 설정
        checkDangerousPermissions();

        //객체 초기화
        editText = findViewById(R.id.editText);
        mylocation = findViewById(R.id.mylocation);
        check = findViewById(R.id.check);
        btn_mypage = findViewById(R.id.btn_mypage);
        btn_addcat = findViewById(R.id.btn_addcat);
<<<<<<< HEAD

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Current Location");
=======
>>>>>>> 5b4d977d40c8547227afe777de2d281af5d84150


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
                map.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                    @Override
                    public void onMapClick(LatLng point) {
                        MarkerOptions mOptions = new MarkerOptions();
                        // 마커 타이틀
                        mOptions.title("마커 좌표");
                        Double latitude = point.latitude; // 위도
                        Double longitude = point.longitude; // 경도
                        // 마커의 스니펫(간단한 텍스트) 설정
                        mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                        // LatLng: 위도 경도 쌍을 나타냄
                        mOptions.position(new LatLng(latitude, longitude));
                        // 마커(핀) 추가
                        map.addMarker(mOptions);

<<<<<<< HEAD
                        btn_addcat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UserLocation location = new UserLocation();
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                                location.setIdToken(firebaseUser.getUid());
                                location.setLatitude(latitude);
                                location.setLongitude(longitude);

                                FirebaseDatabase.getInstance().getReference("Current Location")
                                        .setValue(location).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

                                            //setValue : database에 insert (삽입) 행위
                                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(location);

=======
                        Location resLocation = new Location("");
                        resLocation.setLatitude(latitude);
                        resLocation.setLongitude(longitude);
                        btn_addcat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FirebaseDatabase.getInstance().getReference("Current Location")
                                        .setValue(resLocation).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
>>>>>>> 5b4d977d40c8547227afe777de2d281af5d84150
                                            Toast.makeText(MainActivity.this, "Loacation Saved", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Loacation Not Saved", Toast.LENGTH_SHORT).show();
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

        //위치 확인 버튼 기능 추가
        mylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMyLocation();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().length() > 0) {
                    Location location = getLocationFromAddress(getApplicationContext(), editText.getText().toString());

                    showCurrentLocation(location);
                }
            }
        });
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
=======
        //Asign variable
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);
>>>>>>> 0b2f5120cc3d9cd2749925eb85579a74610cab1d

        //Check permission
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permisson granted
            //call method
            getCurrentLocation();
        } else {
            //when permission denied
            //request permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


    }

<<<<<<< HEAD
    private void getCurrentLocation() {
        //Initialize task location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
=======
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == 1) {
//            for (int i = 0; i < permissions.length; i++) {
//                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//    }
    //------------------권한 설정 끝------------------------

    private void showMyMarker(Location location) {
        if(myMarker == null) {
            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(location.getLatitude(), location.getLongitude()));
            myMarker.title("◎ 내위치\n");
            myMarker.snippet("여기가 어디지?");
>>>>>>> 5b4d977d40c8547227afe777de2d281af5d84150
        }
<<<<<<< HEAD



<<<<<<< HEAD


    }
}
=======
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //when success
                        if (location != null){
                            //Sync map
                            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(@NonNull GoogleMap googleMap) {
                                    //Initialize lat lng
                                    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                                    //create marker option
                                    MarkerOptions options = new MarkerOptions().position(latLng)
                                            .title("I am there");
                                    //zoom map
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
                                    //add marker on map
                                    googleMap.addMarker(options);

                                }
                            });
                        }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //when permission granted
                //call method
                getCurrentLocation();
            }
        }
    }
}

>>>>>>> 0b2f5120cc3d9cd2749925eb85579a74610cab1d
=======




    }
}
>>>>>>> 5b4d977d40c8547227afe777de2d281af5d84150
