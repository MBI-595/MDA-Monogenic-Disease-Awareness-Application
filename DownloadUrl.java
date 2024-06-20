package com.example.monogenicdiseases;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUrl {
    public String retrieveUrl(String url) throws IOException{
        String urlData="";
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;

        try{
            URL getURL=new URL(url);
            httpURLConnection=(HttpURLConnection) getURL.openConnection();
            httpURLConnection.connect();

            inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb=new StringBuffer();

            String Line="";

            while ((Line= bufferedReader.readLine())!=null){
                sb.append(Line);
            }
            urlData=sb.toString();
            bufferedReader.close();
        }catch (Exception e){
            Log.d("EXCEPTION",e.toString());
        }finally {
            //inputStream.close();
            httpURLConnection.disconnect();
        }
        return urlData;
    };
}
