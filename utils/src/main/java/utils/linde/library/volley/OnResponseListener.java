package utils.linde.library.volley;

import com.android.volley.VolleyError;

/**
 * Created by 19195 on 2015/12/10.
 * Volley请求监听
 */
public interface OnResponseListener
{
    void onResponse(String response);

    void onErrorResponse(VolleyError error);
}
