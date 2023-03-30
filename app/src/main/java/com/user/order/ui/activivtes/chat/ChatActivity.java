package com.user.order.ui.activivtes.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.user.order.R;
import com.user.order.databinding.ActivityChatBinding;
import com.user.order.fcm.MyEventBus;
import com.user.order.model.ListMassageData;
import com.user.order.model.contact.ContactUs;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    public static int visible = 0;
    ActivityChatBinding binding;
    MessageAdapter adapter;
    private DatabaseReference mDatabase;
    ArrayList<ListMassageData> list = new ArrayList<>();
    ProgressDialog progressDialog;
    StorageReference storageReference;
    Uri mainImageURI;
    FirebaseStorage firebaseStorage;
    int id;
    public Dialog pic_image_dialog, progress_loading_dialog;
    public static int userid;
    String ref, from, status, invoice_no, user_id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();

    }

    private void initUI() {
        userid = HelperMethods.getCurrentUser(this).getId();

        if (getIntent() != null) {
            id = Integer.parseInt(getIntent().getStringExtra(Const.KEY_ORDER_ID));
            from = getIntent().getStringExtra(Const.KEY_PUBLIC_CHAT);
            status = getIntent().getStringExtra(Const.KEY_STATUS);
            invoice_no = getIntent().getStringExtra(Const.KEY_INVOICE_NUMBER);
            user_id = getIntent().getStringExtra(Const.KEY_user_id);
        }

        if (status.equals("delivered")) {
            binding.sendLinear.setVisibility(View.GONE);
        } else if (status.equals("cancelled")) {
            binding.sendLinear.setVisibility(View.GONE);
        }else {
            binding.sendLinear.setVisibility(View.VISIBLE);
        }

        firebaseStorage = FirebaseStorage.getInstance();

        if (from.equals("chat")) {
            ref = "Chat";
            mDatabase = FirebaseDatabase.getInstance().getReference("Chat");
        } else if (from.equals("chat_public")) {
            mDatabase = FirebaseDatabase.getInstance().getReference("ChatPublic");
            ref = "ChatPublic";
        }

        pic_image_dialog = new Dialog(this);
        progress_loading_dialog = new Dialog(this);
        getMessages(String.valueOf(id));
        sendButton();
    }

    private void sendButton() {
        binding.send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                sendMessage();
                binding.massage.setText("");
                sendNotificationApi();
            }
        });
        binding.send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                binding.massage.setVisibility(View.VISIBLE);
                sendNotificationApi();
            }
        });
        binding.imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        binding.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendMessage() {

        String key = mDatabase.push().getKey();
        mDatabase.child(key).removeValue();

        long millis = System.currentTimeMillis();
        long seconds = millis / 1000;


        Map<String, Object> map = new HashMap<>();
        int user_id = HelperMethods.getCurrentUser(this).getId();
        map.put("idSender", user_id);
        map.put("msg", binding.massage.getText().toString());
        map.put("img", "");
        map.put("time", seconds);

        mDatabase.child(String.valueOf(id)).child(key).setValue(map);
    }

    private void getMessages(String id) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        binding.recyclerView2.setHasFixedSize(true);
        binding.recyclerView2.setLayoutManager(linearLayoutManager);

        FirebaseDatabase.getInstance().getReference(ref).child(id).addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ListMassageData listMassageData = dataSnapshot.getValue(ListMassageData.class);
                    list.add(listMassageData);
                }
                adapter = new MessageAdapter(getBaseContext(), list);
                binding.recyclerView2.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "no data", Toast.LENGTH_LONG).show();
                Log.e("error", "error");
            }
        });


    }

    private void uploadImage() {

        storageReference = firebaseStorage.getReference("images/" + mainImageURI.getLastPathSegment());

        StorageTask<UploadTask.TaskSnapshot> uploadTask = storageReference.putFile(mainImageURI);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String profileImageUrl = task.getResult().toString();
                        Glide.with(getBaseContext()).load(profileImageUrl).transform(new RoundedCorners(8)).error(R.drawable.logo).into(binding.image2);
                        Log.e("UploadActivity", profileImageUrl);
                        binding.sendImage.setVisibility(View.GONE);
//**************************************************----------------------------**********************************************
                        String key = mDatabase.push().getKey();
                        mDatabase.child(key).removeValue();

                        Map<String, Object> map = new HashMap<>();
                        int user_id = HelperMethods.getCurrentUser(ChatActivity.this).getId();
                        map.put("idSender", user_id);
                        map.put("msg", binding.massage.getText().toString());
                        map.put("img", profileImageUrl);
                        map.put("time", 1676720223);

                        mDatabase.child(String.valueOf(id)).child(key).setValue(map);
                        progress_loading_dialog.dismiss();

//**************************************************----------------------------***********************************
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(getBaseContext(), "Image Uploaded Failed!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progress_loading_dialog.setContentView(R.layout.progress_dialog);
                progress_loading_dialog.setCancelable(false);
                progress_loading_dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
                progress_loading_dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
                progress_loading_dialog.show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    private boolean checkStoragePermission() {
        boolean res2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        return res2;
    }

    private boolean checkCameraPermission() {
        boolean res1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
        boolean res2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        return res1 && res2;
    }

    private void chooseImage() {
        pic_image_dialog.setContentView(R.layout.pick_image_dialog);
        pic_image_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pic_image_dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        pic_image_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        ImageView cam = pic_image_dialog.findViewById(R.id.cam);
        ImageView gallery = pic_image_dialog.findViewById(R.id.gallery);

        cam.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                if (!checkCameraPermission()) {
                    requestCameraPermission();
                } else {
                    Intent takePicture = new Intent();
                    takePicture.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                    if (takePicture.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePicture, 2);
                    }
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (!checkStoragePermission()) {
                    requestStoragePermission();
                } else {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                }
            }
        });
        pic_image_dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainImageURI = data.getData();

        switch (requestCode) {

            case 1:
                binding.sendImage.setVisibility(View.VISIBLE);
                Log.e("mainImageURI", mainImageURI + "");

                if (mainImageURI.getPath() != null)
                    Glide.with(getBaseContext()).load(mainImageURI).error(R.drawable.logo).into(binding.image2);
                pic_image_dialog.dismiss();

                break;
            case 2:

                binding.sendImage.setVisibility(View.VISIBLE);
                Bitmap bitmap = null;
                Bundle bundle = data.getExtras();
                bitmap = (Bitmap) bundle.get("data");
                File file1 = new File(getCacheDir(), "image.png");

                binding.sendImage.setVisibility(View.VISIBLE);
                binding.massage.setVisibility(View.INVISIBLE);
                try {
                    FileOutputStream fOut = new FileOutputStream(file1);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                file1.setReadable(true, false);

                mainImageURI = Uri.fromFile(file1);
                Glide.with(getBaseContext()).load(mainImageURI).error(R.drawable.logo).into(binding.image2);
                pic_image_dialog.dismiss();

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    private void sendNotificationApi() {

        HelperMethods.get1OrderAPI().sendNotification(HelperMethods.getUserToken(this),
                user_id, id, invoice_no, ref).enqueue(new Callback<ContactUs>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                if (response.isSuccessful()) {
                    Log.e("Message", response.body().getMessage());
                } else {
                    Log.e("", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ContactUs> call, Throwable t) {
                call.cancel();
                Log.e("Throwable", t.getMessage() + "");


            }
        });

    }

}