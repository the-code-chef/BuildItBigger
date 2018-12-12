package me.deepanshusinha.joke_and_lib;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyJokeActivity extends AppCompatActivity {

    private TextView tv_joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tv_joke = findViewById(R.id.tv_joke);
        getDataFromBundle();
    }

    private void getDataFromBundle() {
        Intent intent = getIntent();
        // if intent is not null & it contain the extra key, get the data from bundle
        if (intent != null && intent.hasExtra(getString(R.string.key_joke_pass))) {
            Bundle data = intent.getExtras();
            if (data != null) {
                // set values on text and images
                String joke = data.getString(getString(R.string.key_joke_pass));
                tv_joke.setText(joke);
            }

        }
    }
}
