package utils.linde.library.volley;

import com.android.volley.AuthFailureError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 19195 on 2015/12/10.
 * Post请求，含参数
 */@SuppressWarnings("unused")
public class PostRequest extends BaseStringRequest
{
    private final Map<String, String> headers;

    public PostRequest(String url, HashMap<String, String> headers, OnResponseListener listener)
    {
        super(url, listener);
        this.headers = headers;
    }

    public PostRequest(int method, HashMap<String, String> headers, String url, OnResponseListener listener)
    {
        super(method, url, listener);
        this.headers = headers;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        return headers == null ? super.getHeaders() : headers;
    }
}
