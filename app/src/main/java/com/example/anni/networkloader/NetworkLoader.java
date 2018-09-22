package com.example.anni.networkloader;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NetworkLoader extends AppCompatActivity implements NetworkLoaderThread.ResultInterface {

   private EditText editText;
   private TextView textView;
    //Button startButton = (Button)findViewById(R.id.startButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_loader);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public void threadProgress(final String data) {
        Log.d("tag3", "DATA"+ data);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(data);
            }
        });

    }

    public void sendURL(View view) {
        String Url = editText.getText().toString();
        Log.d("tag1", "url"+Url);
        NetworkLoaderThread networkLoaderThread = new NetworkLoaderThread(Url);
        networkLoaderThread.listener = this;
        networkLoaderThread.start();


    }
}