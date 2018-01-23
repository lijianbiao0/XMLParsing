package com.hofo.xmlparsing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    public void xmlPullParser(View view) {
        startActivity(XMLPullParsing.class);
    }


    public void SAX(View view) {
        startActivity(SAX.class);
    }

    public void DOM(View view) {
        startActivity(DomParsing.class);
    }

    public void DOM4j(View view) {
        startActivity(Dom4J.class);
    }
}
