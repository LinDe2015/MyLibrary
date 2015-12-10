package utils.linde.library.volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Set;

import utils.linde.library.AppWrapper;
import utils.linde.library.R;

/**
 * Created by 19195 on 2015/12/8.
 *
 * @see StringRequest
 */
public class BaseStringRequest extends StringRequest
{
    private static RequestQueue mRequestQueue;

    private BaseStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(method, url, listener, errorListener);
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
        if (isRequesting(url))
        {
            return;
        }
        setRetryPolicy(new DefaultRetryPolicy(
                AppWrapper.c().getResources().getInteger(R.integer.connect_time_out),
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        setCharset("UTF-8");
        mRequestQueue.add(this);
    }

    private boolean isRequesting(String url)
    {
        final Set<Request<?>> requests = mRequestQueue.getCurrentRequests();
        for (Request<?> request : requests)
        {
            if (request.getUrl().equals(url))
            {
                return true;
            }
        }
        return false;
    }
}

class BaseListener implements Response.Listener<String>
{
    private final String url;

    BaseListener(String url)
    {
        this.url = url;
    }

    @Override
    public void onResponse(String s)
    {
    }
}

class BaseErrorListener implements Response.ErrorListener
{
    private final String url;

    BaseErrorListener(String url)
    {
        this.url = url;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError)
    {
    }
}

