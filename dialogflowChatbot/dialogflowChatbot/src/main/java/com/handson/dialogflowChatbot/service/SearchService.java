package com.handson.dialogflowChatbot.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class SearchService {

    public String searchProducts(String keyword) {
        return "Searched for:" + keyword;
    }

    private String getProductHtml(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://www.ticketmaster.com/search?q=pink")
                .method("GET", body)
                .addHeader("Referer", "https://www.ticketmaster.com/search?q=pink")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .addHeader("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

