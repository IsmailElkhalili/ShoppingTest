package com.example.shoping.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shoping.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.getSystemService;


public class FeedFragment extends Fragment implements OnMapReadyCallback {
    private static  final int IMAFE_REQUEST = 1;
    DatabaseReference reference;
    FirebaseFirestore firebaseStore;
    StorageReference storageReference;
    EditText name,description,price,marka;
    ImageView product_image;
    private Uri imageUri;
    private StorageTask uploadTask;
    GoogleMap mGoogleMap;
    MapView mMapView;
    Double latitude , longitude;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_feed, container, false);
        firebaseStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        marka = view.findViewById(R.id.name_of_Marka);
        description = view.findViewById(R.id.product_desctption);
        price = view.findViewById(R.id.price_item);
        name =  view.findViewById(R.id.product_name);

        mMapView = view.findViewById(R.id.mapView);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        product_image = view.findViewById(R.id.product_image);
        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });
        view.findViewById(R.id.btn_AddItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addProduct();
                addProduct();

            }
        });

        return view;
    }



//    private  void addProduct(){
//
//      String nameP =  name.getText().toString().trim();
//      String markaP =  marka.getText().toString().trim();
//      String descriptionP =  description.getText().toString().trim();
//      String priceP = price.getText().toString().trim();
//        if(nameP.isEmpty() && markaP.isEmpty() && descriptionP.isEmpty() && priceP.isEmpty()){
//            Toast.makeText(getContext(),  "Enter Data",Toast.LENGTH_SHORT).show();
//        }else {
//
//
//            HashMap<String, String> product = new HashMap();
//            product.put("Name",nameP);
//            product.put("Marka" , markaP);
//            product.put("Description" , descriptionP);
//            product.put("Price", priceP);
//            product.put("latItem" ,"0");
//            product.put("longItem" , "0");
//
//
//            firebaseStore.collection("Products").add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                @Override
//                public void onSuccess(DocumentReference documentReference) {
//                    Toast.makeText(getContext(), "Add Successful " , Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getContext(), "Error Failure " , Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//        }

//    }
    //this method for open image picker
    private void openImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAFE_REQUEST);

    }
    private String getFileExtensnion(Uri uri){
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
    private void addProduct(){
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Uploading");
        pd.show();

        final String nameP =  name.getText().toString().trim();
        final String markaP =  marka.getText().toString().trim();
        final String descriptionP =  description.getText().toString().trim();
        final String priceP = price.getText().toString().trim();



        if(imageUri != null && !nameP.isEmpty() && !markaP.isEmpty() && !descriptionP.isEmpty() && !priceP.isEmpty() && latitude != null && longitude != null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtensnion(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw  task.getException();
                    }
                    return  fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        final Uri downloadUri = task.getResult();
                        final String mUri = downloadUri.toString();

                        HashMap<String  , Object> product = new HashMap<>();
                        product.put("image", mUri);
                        product.put("Name",nameP);
                        product.put("Marka" , markaP);
                        product.put("Description" , descriptionP);
                        product.put("Price", priceP);
                        product.put("latItem" ,latitude + "");
                        product.put("longItem" , longitude + "");
                        firebaseStore.collection("Products").add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getContext(), "Add Successful " , Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Error Failure " , Toast.LENGTH_SHORT).show();
                            }
                        });

                        pd.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Failed!",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage()+"",Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });


        }else {
            Toast.makeText(getContext(),  "Enter Data",Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
    }

    //this method from another activity to get image from user device
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAFE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();

            if(uploadTask != null && uploadTask.isInProgress()){
                Toast.makeText(getContext(), "Uplaod in preogress",Toast.LENGTH_SHORT).show();
            }else {
               // uploadImage();
            }

        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGoogleMap = googleMap;
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                LatLng latLng1 = googleMap.getCameraPosition().target;
                googleMap.addMarker(new MarkerOptions().position(latLng1).title("aaaaaaaaaa"));
                latitude = latLng1.latitude;
                longitude = latLng1.longitude;
                Log.d("aaa" ,   latLng1.latitude+ " :  Nice  : " + latLng1.longitude );
            }
        });

    }

    }
