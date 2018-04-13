package com.example.smartcity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Networking {
    private static final String BASE_URL="https://firebasestorage.googleapis.com/v0/b/dehradun-transportation-system.appspot.com/o/Vikram.txt?alt=media&token=235e1e30-cb9f-41d5-b1f6-93670881d715";

    public static ArrayList<String> getHttpResponse(ArrayList<ArrayList<String>> s){
        URL url;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        ArrayList<String> response = new ArrayList<>();

        try{
            url = new URL(BASE_URL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                response = readStream(inputStream,s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    private static ArrayList<String> readStream(InputStream inputStream,ArrayList<ArrayList<String>> s) {
        if(inputStream == null)
            return null;
        InputStreamReader is = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(is);
        StringBuilder builder = new StringBuilder();
        ArrayList<String> string = new ArrayList<>();
        String line = null;
        try{
            while((line=br.readLine())!=null) {
                    String ss[] = line.split(":");
                    ArrayList<String> one = new ArrayList<>();
                    one.add(ss[0]);
                    builder.append(line);
                    for(int i = 1;i<ss.length;i++){
                        string.add(ss[i]);
                        one.add(ss[i]);
                    }
                    s.add(one);
            }

            Object ss[] = string.toArray();
            String f[] = new String[ss.length];
            for(int i = 0;i<ss.length;i++){
                f[i] = (String)ss[i];
            }
            String u[] = new HashSet<String>(Arrays.asList(f)).toArray(new String[0]);
            string.clear();
            for(int  i = 0;i<u.length;i++)
                string.add(u[i]);
        }catch(Exception e){

        }
        return string;
    }
}
