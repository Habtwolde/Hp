package com.example.mhts.hp.MainActivities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyAdapterclass {
    private static MyAdapterclass mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;


    private MyAdapterclass(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();

    }
    private RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MyAdapterclass getmInstance(Context context){
        if (mInstance == null){
            mInstance = new MyAdapterclass(context);
        }
        return mInstance;
    }

    public <T>void addToRequestque(Request<T> request){
        requestQueue.add(request);

    }
}
