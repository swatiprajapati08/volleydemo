package com.example.volleydemo;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
 private Button btn;
 private RequestQueue rqstque;
 private StringRequest strrqt;
    public static final String TAG = "MyTag";

    private String url="http://www.mocky.io/v2/5ca60043330000cd522eaaf8";
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendhttprequestandprint();
                
            }
        });
    }

    private void sendhttprequestandprint() {
        rqstque = Volley.newRequestQueue(this);
        strrqt = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText("Response is :"+ response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        strrqt.setTag(TAG);
        rqstque.add(strrqt);

    }
    @Override
    protected void onStop () {
        super.onStop();
        if (rqstque != null) {
            rqstque.cancelAll(TAG);
        }
    }

}
