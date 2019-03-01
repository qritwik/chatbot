package com.library.apple.scify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import static com.android.volley.VolleyLog.TAG;


public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager rvlayoutManager;
    List<ModelChat> list = new ArrayList<>();
    Button botton_send;
    EditText et_mess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar_chat);
        setSupportActionBar(toolbar);

        et_mess = (EditText)findViewById(R.id.et_mess);

        botton_send = (Button)findViewById(R.id.btn_send);
        botton_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String message = et_mess.getText().toString().trim();
                final ModelChat modelChat = new ModelChat();
//                Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();


                modelChat.setUser_type(0);
                modelChat.setMess(message);
                list.add(modelChat);
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(list.size()-1);

                getResponse(message);

                et_mess.setText("");




            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.rv_chat);
        adapter = new ChatAdapter(list);
        recyclerView.setAdapter(adapter);
        rvlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(rvlayoutManager);
        recyclerView.setHasFixedSize(true);


    }




    private void getResponse(String message) {

        String robo_url = "http://sandbox.api.simsimi.com/request.p?key=8c80b2c4-a935-411e-8d7a-4fbf2133b098&lc=en&ft=1.0&text="+message;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                robo_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String res_message = response.getString("response");

                    final ModelChat modelChat1 = new ModelChat();
                    modelChat1.setUser_type(1);
                    modelChat1.setMess(res_message);
                    list.add(modelChat1);
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(list.size()-1);


//                    Toast.makeText(getApplicationContext(),res_message, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(jsonObjReq);
    }



}
