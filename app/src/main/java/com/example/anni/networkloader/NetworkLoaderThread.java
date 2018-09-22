package com.example.anni.networkloader;

import android.util.Log;

import java.net.*;
import java.io.*;

public class NetworkLoaderThread extends Thread{
    private String readURL;
    private String allData;
    protected ResultInterface listener = null;

    public NetworkLoaderThread(String aURL) {

        this.readURL = aURL;
    }
    public interface ResultInterface {
        void threadProgress(String data);
    }
    public void setListener(ResultInterface aListener){
        this.listener = aListener;
    }
    @Override
    public void run(){
        try {
            loadStuff();
        }
        catch (Exception e) {
            System.out.println("Exception e");
        }
    }
    private void loadStuff() {
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(readURL);
            Log.d("tag2", "URL"+readURL);
            //URL url = new URL("http://google.fi/");

            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            allData = fromStream(in);

            if(listener != null){

                listener.threadProgress(allData);
            }
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
    }
    public static String fromStream(InputStream in) throws IOException
    {Log.d("tag4", "data");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}
