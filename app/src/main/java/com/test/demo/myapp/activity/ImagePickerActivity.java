package com.test.demo.myapp.activity;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.test.demo.myapp.R;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImagePickerActivity extends AppCompatActivity {

    private static final int REQUEST_GALLERY_IMAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private LinearLayout imageContainer;
    private SelectedLibrary selectedLibrary = SelectedLibrary.NO_LIBRARY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_picker_activity);
        initViews();
        loadImagesFromDisk();
    }

    private void initViews() {
        imageContainer = (LinearLayout) findViewById(R.id.image_picker_container);
    }

    private void addImage(Bitmap imagebitmap, Uri imageUri) {
        final View imageLayoutView = getLayoutInflater().inflate(R.layout.picked_image_component, imageContainer, false);
        ImageView imageView = (ImageView) imageLayoutView.findViewById(R.id.image);

        switch (selectedLibrary) {
            case NO_LIBRARY:
                if (imagebitmap != null) {
                    imageView.setImageBitmap(imagebitmap);
                } else {
                    imageView.setImageURI(imageUri);
                }
                break;
            case PICASSO:
                if (imagebitmap != null) {
                    Toast.makeText(this, "Picasso does not support loading image bitmaps", Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(imagebitmap);
                } else {
                    Picasso.with(this).load(imageUri).into(imageView);
                }
                break;
            case GLIDE:
                if (imagebitmap != null) {
                    Toast.makeText(this, "Glide does not support loading image bitmaps", Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(imagebitmap);
                } else {
                    Glide.with(this).load(imageUri).into(imageView);
                }
                break;
            case MANUAL_PROCESSING:
                if (imagebitmap != null) {
                } else {
                    String filePath = getPathFromURI(imageUri);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
                    imageView.setImageBitmap(bitmap);
                }
                break;
        }

        ImageView removeImage = (ImageView) imageLayoutView.findViewById(R.id.remove_image_cross);
        removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageContainer.removeView(imageLayoutView);
            }
        });
        imageContainer.addView(imageLayoutView);
    }

    public void captureImageClick(View target) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void addImageClick(View target) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GALLERY_IMAGE);
    }

    private void saveImageToDisk(Uri imageUri) throws IOException {
        String FILENAME = "image_file";
        InputStream inputStream = getContentResolver().openInputStream(imageUri);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.write(bytes);
        fos.close();
    }

    private void saveImageToDisk(Bitmap bitmap) throws IOException {
        String FILENAME = "image_file";
        FileOutputStream out = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        out.close();
    }

    private void readImageFromDisk() throws IOException {
        FileInputStream fileInputStream = openFileInput("image_file");
        byte[] bytes = IOUtils.toByteArray(fileInputStream);
        loadImage(bytes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_GALLERY_IMAGE:
                Uri uri = data.getData();
                // Get the path from the Uri
                String path = getPathFromURI(uri);
                // Set the image in ImageView
                addImage(null, uri);
                storeImageByUri(uri);
                break;
            case REQUEST_IMAGE_CAPTURE:
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                addImage(imageBitmap, null);
                storeImageByBitmap(imageBitmap);
                break;
        }


    }

    private void storeImageByBitmap(final Bitmap bitmap) {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    saveImageToDisk(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }
        };
        task.execute();
    }

    private void storeImageByUri(final Uri uri) {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    saveImageToDisk(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Toast.makeText(ImagePickerActivity.this, "Finished saving image", Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }

    private void loadImagesFromDisk() {
        try {
            readImageFromDisk();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.no_library_radio_button:
                if (checked)
                    selectedLibrary = SelectedLibrary.NO_LIBRARY;
                break;
            case R.id.picasso_radio_button:
                selectedLibrary = SelectedLibrary.PICASSO;
                if (checked)
                    break;
            case R.id.glide_radio_button:
                if (checked)
                    selectedLibrary = SelectedLibrary.GLIDE;
                break;
            case R.id.manual_processing_radio_button:
                if (checked)
                    selectedLibrary = SelectedLibrary.MANUAL_PROCESSING;
                break;
        }
    }

    private void loadImage(byte[] bytes) {
        final View imageLayoutView = getLayoutInflater().inflate(R.layout.picked_image_component, imageContainer, false);
        ImageView imageView = (ImageView) imageLayoutView.findViewById(R.id.image);
        Glide.with(this).load(bytes).into(imageView);
        ImageView removeImage = (ImageView) imageLayoutView.findViewById(R.id.remove_image_cross);
        removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageContainer.removeView(imageLayoutView);
            }
        });
        imageContainer.addView(imageLayoutView);
    }

    private enum SelectedLibrary {
        NO_LIBRARY, PICASSO, GLIDE, MANUAL_PROCESSING
    }
}

