package com.ifsju.interfaceweb.service;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserAuthService.class);

    public List<String> getUserAuthInfos(String studentId, String password){
        String jsessionId = setJsessionId();
        sendPost(studentId,password,jsessionId);
        return sendGet(jsessionId);
    }

    public String setJsessionId(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://classic.sejong.ac.kr")
                .build();
        try {
            Response response = client.newCall(request).execute();
            LOGGER.info("[setJesessionId] "+response);
            Headers headers = response.headers();
            for (String name : headers.names()) {
                List<String> values = headers.values(name);
                // If the header is 'Set-Cookie', check for JSESSIONID
                if ("Set-Cookie".equalsIgnoreCase(name)) {
                    for (String value : values) {
                        if (value.contains("JSESSIONID")) {
                            String jsessionId = extractJSessionID(value);
                            System.out.println("JSESSIONID: " + jsessionId);
                            return jsessionId;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public String extractJSessionID(String cookieValue) {
        String[] parts = cookieValue.split(";");
        for (String part : parts) {
            part = part.trim();
            if (part.startsWith("JSESSIONID=")) {
                return part.substring("JSESSIONID=".length());
            }
        }
        return null;
    }

    public void sendPost(String studentId, String password, String jsessionId){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userId",studentId)
                .addFormDataPart("password",password)
                .build();
        Request request = new Request.Builder()
                .url("https://classic.sejong.ac.kr/userLogin.do?userId="+studentId+"&password="+password)
                .method("POST", body)
                .addHeader("Cookie", "JSESSIONID="+jsessionId)
                .build();
        try {
            Response response = client.newCall(request).execute();
            LOGGER.info("[sendPost] "+response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> sendGet(String jsessionId){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://classic.sejong.ac.kr/userCertStatus.do?menuInfoId=MAIN_02_05")
                .addHeader("Cookie", "JSESSIONID="+jsessionId)
                .build();
        try {
            Response response = client.newCall(request).execute();
            LOGGER.info("[sendGet] "+response);
            List<String> list = extractDataFromHtml(response.body().string());
            for (String e:list) {
                System.out.println(e);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> extractDataFromHtml(String html) {
        List<String> dataList = new ArrayList<>();
        Document doc = Jsoup.parse(html);

        Elements elements = doc.select("div.contentWrap li dl dd");

        for (Element element : elements) {
            dataList.add(element.text());
        }

        return dataList;
    }

}
