package utils.linde.library.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by 19195 on 2015/12/10.
 * GZIP压缩请求
 */
@SuppressWarnings("unused")
public class GzipRequest extends BaseStringRequest
{
    public GzipRequest(String url, OnResponseListener listener)
    {
        super(url, listener);
    }

    public GzipRequest(int method, String url, OnResponseListener listener)
    {
        super(method, url, listener);
    }

    /**
     * 设置请求头 采用GZIP压缩
     *
     * @return {@link Map }
     * @throws AuthFailureError
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        Map<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put("Content-Type", "application/x-javascript");
        headers.put("Accept-Encoding", "gzip,deflate");
        return headers;
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {
        String jsonStr;
        String contentType = response.headers.get("Content-Encoding");
        final String GZIP_ENCODING_TYPE = "gzip";
        if (contentType != null && contentType.equalsIgnoreCase(GZIP_ENCODING_TYPE))
        {
            /**
             * 返回数据为GZIP压缩数据，需要解压缩
             */
            try
            {
                GZIPInputStream gzip = new GZIPInputStream(new BufferedInputStream(new ByteArrayInputStream(response.data)));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[256];
                int count;
                while ((count = gzip.read(buffer)) >= 0)
                {
                    baos.write(buffer, 0, count);
                }
                byte[] bytes = baos.toByteArray();
                jsonStr = new String(bytes);
            } catch (IOException e)
            {
                e.printStackTrace();
                return Response.error(new ParseError(e));
            }
        } else
        {
            /**
             * 返回数据不是GZIP压缩数据，不需要处理
             */
            try
            {
                jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
                return Response.error(new ParseError(e));
            }
        }
        return Response.success(jsonStr, HttpHeaderParser.parseCacheHeaders(response));
    }
}
