package com.example.android.requestapi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.android.requestapi.R;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    TextView First_name, Last_name;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageViewD);

        First_name = (TextView) findViewById(R.id.first_nameD);

        Last_name = (TextView) findViewById(R.id.last_nameD);


        String first_name = getIntent().getExtras().getString("first_name");

        String last_name = getIntent().getExtras().getString("last_name");


        String avatar = getIntent().getExtras().getString("avatar");

        First_name.setText(first_name);
        Last_name.setText(last_name);

        Glide.with(this)
                .load(avatar)
                .placeholder(R.drawable.load)
                .into(imageView);

        getSupportActionBar().setTitle("Details Activity");


    }

    private Intent createShareForcastIntent() {
        String first_name = getIntent().getExtras().getString("first_name");
        String last_name = getIntent().getExtras().getString("last_name");


        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer @" + first_name + ", " + last_name)
                .getIntent();
        return shareIntent;
    }
}