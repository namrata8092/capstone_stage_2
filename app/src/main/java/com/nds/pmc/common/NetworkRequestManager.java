package com.nds.pmc.common;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nds.pmc.util.ValidationUtil;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by Namrata on 11/8/2017.
 */

public class NetworkRequestManager {
    private static final String TAG = NetworkRequestManager.class.getSimpleName();
    private Context mContext;
    private static NetworkRequestManager mNetworkRequestInstance;
    private RequestQueue mRequestQueue;

    private NetworkRequestManager(Context context) {
        this.mContext = context;
    }

    public static synchronized NetworkRequestManager getInstance(Context context) {
        if (mNetworkRequestInstance == null) {
            mNetworkRequestInstance = new NetworkRequestManager(context);
        }
        return mNetworkRequestInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public void initializeNetworkManager() {
        mRequestQueue = getRequestQueue();
    }

    public <T> void addToRequestQueue(Request<T> request, String requestTag) {
        request.setTag(ValidationUtil.isValidString(requestTag) ? requestTag : TAG);
        mRequestQueue.add(request);
    }

    public void cancelOngoingRequest(String requestTag) {
        if (mRequestQueue != null)
            mRequestQueue.cancelAll(requestTag);
    }

    public void terminateNetworkManager() {
        if (mRequestQueue != null)
            mRequestQueue.stop();
    }


    public void createStringRequest(final WeakReference<NetworkRequester> wNetworkRequester, String url, String requestTag) {
        JSONObject obj = new JSONObject();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                NetworkRequester requester = null;
                if (wNetworkRequester != null) {
                    requester = wNetworkRequester.get();
                }
                if (requester != null && ValidationUtil.isValidString(response)) {
                    requester.onSuccess(response);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                NetworkRequester requester = null;
                if (wNetworkRequester != null) {
                    requester = wNetworkRequester.get();
                }
                if (requester != null)
                    requester.onFailure(volleyError);
            }
        };

        Uri.Builder builder = Uri.parse(url).buildUpon();

        url = builder.build().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        addToRequestQueue(stringRequest, requestTag);
    }
}
