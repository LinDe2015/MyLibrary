package utils.linde.library.volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

import utils.linde.library.AppWrapper;
import utils.linde.library.R;

/**
 * Created by 19195 on 2015/12/8.
 *
 * @see StringRequest
 */
public class BaseStringRequest extends StringRequest
{
    private static final HashMap<String, OnResponseListener> mResponses = new HashMap<>();
    private static RequestQueue mRequestQueue;

    public BaseStringRequest(String url, OnResponseListener listener)
    {
        this(Method.GET, url, listener);
    }

    public BaseStringRequest(int method, String url, OnResponseListener listener)
    {
        super(method, url, new BaseListener(url), new BaseErrorListener(url));
        if (mRequestQueue == null)
        {
            synchronized (BaseStringRequest.class)
            {
                if (mRequestQueue == null)
                {
                    mRequestQueue = Volley.newRequestQueue(AppWrapper.c());
                }
            }
        }
        if (mResponses.containsKey(url))
        {
            mResponses.remove(url);
            mResponses.put(url, listener);
            return;
        }
        mResponses.put(url, listener);
        setRetryPolicy(new DefaultRetryPolicy(
                AppWrapper.c().getResources().getInteger(R.integer.connect_time_out),
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        setCharset("UTF-8");
        mRequestQueue.add(this);
    }

    private static class BaseListener implements Response.Listener<String>
    {
        private final String url;

        BaseListener(String url)
        {
            this.url = url;
        }

        @Override
        public void onResponse(String s)
        {
            final OnResponseListener listener = mResponses.get(url);
            if (listener != null)
            {
                listener.onResponse(s);
            }
            mResponses.remove(url);
        }
    }

    private static class BaseErrorListener implements Response.ErrorListener
    {
        private final String url;

        BaseErrorListener(String url)
        {
            this.url = url;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError)
        {

            final OnResponseListener listener = mResponses.get(url);

            if (listener != null)
            {
                listener.onErrorResponse(volleyError);
            }
            mResponses.remove(url);
        }
    }
}

