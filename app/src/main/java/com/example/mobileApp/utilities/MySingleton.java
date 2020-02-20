package com.example.mobileApp.utilities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/* MySingleton class is used to create a Volley RequestQueue as a singleton
   and make the RequestQueue last the lifetime of app. */

public class MySingleton {

    private static MySingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new MySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // RequestQueue is instantiated with the Application context
            // This ensures that the RequestQueue will last for the lifetime of your app
            // getApplicationContext() prevents leaking the Activity or Fragment references
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
