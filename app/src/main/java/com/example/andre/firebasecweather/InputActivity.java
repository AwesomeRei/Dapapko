package com.example.andre.firebasecweather;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InputActivity extends AppCompatActivity {
    Button tambah;
    private final static int SELECT_PHOTO = 123;
    EditText judul;
    EditText deskripsi;
    EditText bahan;
    EditText masak;
    LinearLayout LLEnterText;
    LinearLayout LLEnterText2;
    Button lanjut;
    Button submit;
    Button upload;
    String nama;
    String desk;
    Resep resep;
    int i,j;
    int flag;
    int _intMyLineCount;
    int _intMyLineCount2;
    String imageResep;
    StringBuilder sb ;
    StringBuilder sm;
    private List<EditText> editTextList = new ArrayList<EditText>();
    private List<EditText> editTextList2 = new ArrayList<EditText>();
    Firebase ref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ref = new Firebase("https://dpp.firebaseio.com/Recipe");
        sb = new StringBuilder();
        sm = new StringBuilder();
        i= 1;
        j =1;
        judul =(EditText)findViewById(R.id.namaresep);
        deskripsi = (EditText)findViewById(R.id.deskripsiresep);
        bahan = (EditText)findViewById(R.id.ingredient);
        masak =(EditText)findViewById(R.id.steps);
        tambah = (Button)findViewById(R.id.add_mas);
        lanjut = (Button)findViewById(R.id.nextstep);
        submit = (Button)findViewById(R.id.submit);
        upload = (Button)findViewById(R.id.upload);
        LLEnterText = (LinearLayout)findViewById(R.id.linearForm);
        LLEnterText2 = (LinearLayout)findViewById(R.id.linearFormMasak);
        upload.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent,SELECT_PHOTO);
            }
        });
        tambah.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editTextList.add(editText(_intMyLineCount));
                flag = 1;
                LLEnterText.addView(linearlayout(_intMyLineCount,flag));
                _intMyLineCount++;
            }
        });

        lanjut.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 2;
                LLEnterText2.addView(linearlayout(_intMyLineCount2,flag));
                _intMyLineCount2++;
            }
        });

        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = judul.getText().toString();
                desk = deskripsi.getText().toString();
                sb.append(bahan.getText().toString());
                sb.append("\n");
                sm.append(masak.getText().toString());
                sm.append("\n");
                for (EditText editText : editTextList) {
                    sb.append(editText.getText().toString());
                    sb.append("\n");
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + sb);

                }
                for (EditText editText : editTextList2) {
                    sm.append(editText.getText().toString());
                    sm.append("\n");
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + sm);

                }

                Firebase resepref=ref.child(nama);
                resep = new Resep(nama,desk,imageResep, sb.toString(),sm.toString());
                resepref.setValue(resep);
                //System.out.println("=============================================BERHASIL=============================================");
            }

        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            imageResep = encodeTobase64(bitmap);
            cursor.close();
        }
    };

    private EditText editText(int _intID,int flag) {
        EditText editText = new EditText(this);
        editText.setId(_intID);
        editText.setHint("Tulis Disini");
        editText.setWidth(400);
        editText.setBackgroundColor(Color.WHITE);
        if(flag == 1 )
        {
            editTextList.add(editText);
        }
        else if (flag == 2) {
            editTextList2.add(editText);
        }
        return editText;
    }
    private TextView textView(int _intID,int flag)
    {
        TextView txtviewAll=new TextView(this);
        txtviewAll.setId(_intID);
        if(flag == 1){
            txtviewAll.setText("Step " + i);
            i++;
        }
        else if(flag == 2){
            txtviewAll.setText("Step " + j);
            j++;
        }
        txtviewAll.setWidth(180);
        txtviewAll.setTextColor(Color.RED);
        txtviewAll.setTypeface(Typeface.DEFAULT_BOLD);
        //textviewList.add(txtviewAll);
        return txtviewAll;
    }
    private LinearLayout linearlayout(int _intID,int flag)
    {
        LinearLayout LLMain=new LinearLayout(this);
        LLMain.setId(_intID);
        LLMain.addView(textView(_intID, flag));
        LLMain.addView(editText(_intID, flag));
        LLMain.setOrientation(LinearLayout.HORIZONTAL);
        //linearlayoutList.add(LLMain);
        return LLMain;

    }
    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        //Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }


}
