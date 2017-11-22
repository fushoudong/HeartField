package com.bupt.heartarea.retrofit;

import android.content.Context;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by yuqing on 2017/9/1.
 */

public class HttpManager {

    private static HttpManager instance = null;

    private HttpManager() {

    }

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    /**
     * get请求
     *
     * @param url 完整url
     * @return
     */
    public Call<String> get(String url) {
        return RetroFactory.getStringService().get(url);
    }

    /**
     * 基于https协议的get请求
     *
     * @param context    上下文
     * @param resourceId https证书的资源id，请放到raw文件夹下
     * @param url
     * @return
     */
    public Call<String> getForHttps(Context context, int resourceId, String url) {
        return RetroFactory.getStringService(context, resourceId).get(url);
    }

    /**
     * 可设置请求头的get请求
     *
     * @param url     完整url
     * @param headers map格式的请求头
     * @return
     */
    public Call<String> getWithHeaders(String url, HashMap<String, String> headers) {
        return RetroFactory.getStringService().getWithHeaders(headers, url);
    }

    /**
     * 基于https协议的可设置请求头的get请求
     *
     * @param context    上下文
     * @param resourceId https证书的资源id，请放到raw文件夹下
     * @param url        完整url
     * @param headers    map格式的请求头
     * @return
     */
    public Call<String> getWithHeadersForHttps(Context context, int resourceId, String url, HashMap<String, String> headers) {
        return RetroFactory.getStringService(context, resourceId).getWithHeaders(headers, url);
    }

    public Call<ResponseBody> get2(String url) {
        return RetroFactory.getStringService().get2(url);
    }

    public Call<ResponseBody> getWithHeaders2(String url, HashMap<String, String> headers) {
        return RetroFactory.getStringService().getWithHeaders2(headers, url);
    }


    /**
     * 可设置请求头的post请求
     *
     * @param baseUrl 前缀url 例如：http://www.youdao.com/ 必须以'/'结尾 （完整url为http://www.youdao.com/api/login）
     * @param url     后缀url 例如：api/login （完整url为http://www.youdao.com/api/login）
     * @param headers map格式的请求头
     * @param params  post请求参数
     * @return
     */
    public Call<String> post(String baseUrl, String url,
                             HashMap<String, String> headers, HashMap<String, String> params) {
        return RetroFactory.getStringService(null, -1, baseUrl).post(url, headers, params);
    }

    /**
     * post请求
     *
     * @param baseUrl 前缀url 例如：http://www.youdao.com/ 必须以'/'结尾 （完整url为http://www.youdao.com/api/login）
     * @param url     后缀url 例如：api/login （完整url为http://www.youdao.com/api/login）
     * @param params  post请求参数
     * @return
     */
    public Call<String> post(String baseUrl, String url, HashMap<String, String> params) {
        return RetroFactory.getStringService(null, -1, baseUrl).post(url, params);
    }

    /**
     * 上传图片
     *
     * @param baseUrl  前缀url 例如：http://www.youdao.com/ 必须以'/'结尾 （完整url为http://www.youdao.com/api/login）
     * @param url      后缀url 例如：api/login （完整url为http://www.youdao.com/api/login）
     * @param key      图片对应的键值
     * @param file     上传的图片文件
     * @param filename 文件名
     * @param headers  请求头，可为空
     * @param params   post参数
     * @return
     */
    public Call<String> postImage(String baseUrl, String url, String key, File file, String filename,
                                  HashMap<String, String> headers, HashMap<String, String> params) {
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData(key, filename, photoRequestBody);
        return RetroFactory.getStringService(null, -1, baseUrl).postFile(url, headers, photo, params);
    }

    /**
     * 上传图片
     *
     * @param baseUrl  前缀url 例如：http://www.youdao.com/ 必须以'/'结尾 （完整url为http://www.youdao.com/api/login）
     * @param url      后缀url 例如：api/login （完整url为http://www.youdao.com/api/login）
     * @param key      图片对应的键值
     * @param file     上传的图片文件
     * @param filename 文件名
     * @param params   post参数
     * @return
     */
    public Call<String> postImage(String baseUrl, String url, String key, File file, String filename,
                                  HashMap<String, String> params) {
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData(key, filename, photoRequestBody);
        return RetroFactory.getStringService(null, -1, baseUrl).postFile(url, photo, params);
    }

    /**
     * 上传图片
     *
     * @param url      后缀url 例如：api/login （完整url为http://www.youdao.com/api/login）
     * @param key      图片对应的键值
     * @param file     上传的图片文件
     * @param filename 文件名
     * @param params   post参数
     * @return
     */
    public Call<String> postImage(String url, String key, File file, String filename, HashMap<String, String> params) {
//        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
//        MultipartBody.Part photo = MultipartBody.Part.createFormData(key, filename, photoRequestBody);
//        return RetroFactory.getStringService(null, -1).postFile(null, url, photo, params);
        return postImage(HttpConstants.BASE_URL, url, key, file, filename, null, params);
    }


}
