package com.myjobassistant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddDocuments extends AppCompatActivity {
    Button cv,save,next,back;
    StorageReference storageRef;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_documents);

        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();

        cv=findViewById(R.id.addcv);
        save= findViewById(R.id.savefiles);
        next= findViewById(R.id.nexttojobrequirement);
        back= findViewById(R.id.back6);

        storageRef= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference().child(uid);
        save.setEnabled(false);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCV();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddDocuments.this, AddJobRequirement.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddDocuments.this, home.class);
                startActivity(i);
            }
        });

    }

    private void selectCV() {
        Intent intent= new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"CV File"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            save.setEnabled(true);
            cv.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadCVfileFirebase(data.getData());
                }
            });
        }
    }

    private void uploadCVfileFirebase(Uri data) {
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("CV is Uploading");
        progressDialog.show();

        StorageReference reference= storageRef.child("Upload "+ System.currentTimeMillis()+ ".pdf");

        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                            Uri uri=uriTask.getResult();
                            Documents cvdoc= new Documents(cv.getText().toString(),uri.toString());
                            databaseReference.child("Documents").setValue(cvdoc);
                            Toast.makeText(AddDocuments.this, "CV Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100.0 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("File Upload.."+ (int)progress + "%");
            }
        });
    }
}