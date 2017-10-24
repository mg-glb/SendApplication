package com.example.mgigena.sendapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button textButton;
    Button imageButton;
    Button multipleButton;
    File dir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textButton = (Button) findViewById(R.id.sendTextButton);
        imageButton = (Button) findViewById(R.id.sendImageButton);
        multipleButton = (Button) findViewById(R.id.sendMultipleButton);

        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(
                        Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "score.jpg";
                File file = new File(dir, name);

                Uri uriToImage = Uri.parse(file.getAbsolutePath());

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent,
                        getResources().getText(R.string.send_to)));

            }
        });

        multipleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Uri> imageUris = new ArrayList<>();
                String name1 = "score1.jpg";
                String name2 = "score2.jpg";
                File file1 = new File(dir,name1);
                File file2 = new File(dir,name2);

                Uri imageUri1 = Uri.parse(file1.getAbsolutePath());
                Uri imageUri2 = Uri.parse(file2.getAbsolutePath());
                imageUris.add(imageUri1);
                imageUris.add(imageUri2);

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,imageUris);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent,"Send images to..."));
            }
        });
    }
}
