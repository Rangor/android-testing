package com.test.demo.myapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.demo.myapp.R;
import com.test.demo.myapp.dialog.DialogGenerator;

public class DialogTestingActivity extends AppCompatActivity {

    public static final int REQUEST_LARGE_TEXT = 3;

    private static final int REQUEST_GALLERY_IMAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private String m_Text = "";
    private TextView resultText;
    private ImageView resultImage;
    private DialogGenerator dialogGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_testing);
        resultText = (TextView) findViewById(R.id.valueTextView);
        dialogGenerator = new DialogGenerator();
        resultImage = (ImageView) findViewById(R.id.valueImageView);
    }

    public void simpleAlertDialog(View target) {
// 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("This is the message")
                .setTitle("This is the title");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void smallSelection(View target) {
        String[] selection = {"Ost", "Kjøtt", "Kylling"};
        dialogGenerator.selectionDialog(getSupportFragmentManager(), selection, new DialogGenerator.DialogCallback() {
            @Override
            public void onOk(String result) {
                resultText.setText(result);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void largeSelection(View target) {
        Resources res = getResources();
        String[] names = res.getStringArray(R.array.names);

        dialogGenerator.selectionDialog(getSupportFragmentManager(), names, new DialogGenerator.DialogCallback() {
            @Override
            public void onOk(String result) {
                resultText.setText(result);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void largeTextDialog(View target) {
        startActivityForResult(new Intent(this, LargeTextInputActivity.class), REQUEST_LARGE_TEXT);
    }

    public void smallTextDialog(View target) {
        dialogGenerator.alertDialogWithText(this, new DialogGenerator.DialogCallback() {
            @Override
            public void onOk(String result) {
                resultText.setText(result);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void datePickerDialog(View target) {
        dialogGenerator.datePickerDialog(getSupportFragmentManager(), new DialogGenerator.DialogCallback() {
            @Override
            public void onOk(String result) {
                resultText.setText(result);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void timePickerDialog(View target) {
        dialogGenerator.timePickerDialog(getSupportFragmentManager(), new DialogGenerator.DialogCallback() {
            @Override
            public void onOk(String result) {
                resultText.setText(result);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void imageFromGallery(View target) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GALLERY_IMAGE);
    }

    public void imageFromCamera(View target) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void simpleDialog(View target) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Message")
                .setTitle("Title");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, "On Activity Result", Toast.LENGTH_SHORT).show();

        switch (requestCode) {
            case REQUEST_GALLERY_IMAGE:
                Uri uri = data.getData();
                Toast.makeText(this, "You picked a image! " + uri.toString(), Toast.LENGTH_SHORT).show();
                // Get the path from the Uri
                String path = getPathFromURI(uri);
//            Log.i(TAG, "Image Path : " + path);
                // Set the image in ImageView
                resultImage.setImageURI(uri);
//            InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
//            resultImage.setImageURI(uri);
                break;
            case REQUEST_IMAGE_CAPTURE:
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                resultImage.setImageBitmap(imageBitmap);
                break;
            case REQUEST_LARGE_TEXT:
                Toast.makeText(this, "You got large text!", Toast.LENGTH_SHORT).show();
                String text = data.getExtras().getString("text");
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                resultText.setText(text);
                break;
        }


    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


    //    private void alertDialogWithText() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Title");
//
//        // Set up the input
//        final EditText input = new EditText(this);
//        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
////        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        input.setInputType(InputType.TYPE_CLASS_TEXT);
//        builder.setView(input);
//
//        // Set up the buttons
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                m_Text = input.getText().toString();
//                resultText.setText(input.getText().toString());
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        builder.show();
//    }
}
