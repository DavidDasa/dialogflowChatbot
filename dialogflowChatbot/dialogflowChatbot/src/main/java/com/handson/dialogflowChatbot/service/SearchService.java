package com.handson.dialogflowChatbot.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchService {
    private static final Pattern PRODUCT_PATTERN = Pattern.compile("<span class=\"VisuallyHidden-sc-8buqks-0 lmhoCy\">Open additional information for <span class=\"VisuallyHidden-sc-8buqks-0 lmhoCy\">([^<]+)<span>([^<]+)");

    public String searchShows(String keyword) throws IOException {
        return parseShowHtml(getShowHtml(keyword));
    }

    private String parseShowHtml(String html) {
        StringBuilder res = new StringBuilder();
        Matcher matcher = PRODUCT_PATTERN.matcher(html);
        while (matcher.find()) {
            res.append(matcher.group(1)).append(" - ").append(matcher.group(2)).append("<br>\n");
        }
        return res.toString();
    }

    private String getShowHtml(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.ticketmaster.com/search?q=" + keyword)
                .method("GET", null)
                .addHeader("Referer", "https://www.ticketmaster.com/search?q=" + keyword)
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .addHeader("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
