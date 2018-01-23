package com.hofo.xmlparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ljb on 2018/1/22.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private TextView src;
    private TextView pars;
    private String content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);
        findViewByIds();
        try {
            setSrcText(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parsing(View view) {
        try {
            parsing();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void parsing() throws Exception;

    protected void log(String msg) {
        Log.e("AppDebug", msg);
        content += msg + "\n";
        pars.setText(content);
    }

    protected void setSrcText(TextView src) throws IOException {
        InputStream is = getAssets().open("XmlParsingTest.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String tempLine = null;
        String content = "";
        while ((tempLine = br.readLine()) != null) {
            content += tempLine + "\r\n";
        }
        src.setText(content);
    }


    private void findViewByIds() {
        src = findViewById(R.id.textViewSrc);
        pars = findViewById(R.id.textViewParsing);

    }

}
