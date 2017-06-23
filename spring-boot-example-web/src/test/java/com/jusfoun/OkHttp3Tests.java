package com.jusfoun;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Create on 2017-06-23.
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
public class OkHttp3Tests {

    private static final String SCHEME = "https";
    private static final String HOST = "cnodejs.org";
    private static final String PATH = "api/v1";

    private static final String API_TOPICS = "/topics";
    private static final String ACCESSTOKEN = "";
    private static final String TAB_DEV = "dev";

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private OkHttpClient okHttpClient;
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    @Before
    public void init() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
    }

    /**
     * 同步 GET
     * @throws IOException
     */
    @Test
    public void syncGet() throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegments(PATH + API_TOPICS)
                .addEncodedQueryParameter("page", "1")
                .addQueryParameter("tab", TAB_DEV)
                .addQueryParameter("limit", "2")
                .addQueryParameter("mdrender", "true")
                .build();

        Request request = new Request.Builder().url(httpUrl).build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        printResponseHeaders(response);
        printResponseBody(response);
    }

    /**
     * 异步 GET
     * @throws IOException
     */
    @Test
    public void asyncGet() throws IOException {
        Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                printResponseHeaders(response);
                printResponseBody(response);
            }
        });

        long start = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start > 10000) {
                break;
            }
        }
    }

    @Test
    public void postFormBody() throws IOException {
        String title = "测试帖";
        String content = "主题内容";

        RequestBody formBody = new FormBody.Builder()
                .add("title", title)
                .add("content", content)
                .add("accesstoken", "")
                .add("tab", TAB_DEV)
                .build();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegments(PATH + API_TOPICS)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        printResponseHeaders(response);
        printResponseBody(response);
    }

    @Test
    public void postBody() throws IOException {
        String postBody = ""
                + "Releases\n"
                + "---\n"
                + "\n"
                + " * 1.0\n"
                + " * 1.1\n"
                + " * 1.2\n";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        printResponseHeaders(response);
        printResponseBody(response);
    }

    private void printResponseHeaders(Response response) {
        Headers headers = response.headers();
        Set<String> names = headers.names();
        System.out.println("========================= Response Headers ===============================");
        for (String name : names) {
            System.out.println(String.format("Response Header: %s - %s", name, headers.get(name)));
        }
    }

    private void printResponseBody(Response response) throws IOException {
        System.out.println("========================= Response Body ==================================");
        System.out.println(response.body().string());
    }
}
