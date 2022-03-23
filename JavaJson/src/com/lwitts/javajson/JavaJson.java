package com.lwitts.javajson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.io.*;
import java.net.URL;


public class JavaJson {
    public static void main(String[] args) throws Exception {

        URL manifest = new URL("https://iiif.bodleian.ox.ac.uk/iiif/manifest/abd2e479-a15b-4784-bba0-4c8d8d9a19ea.json");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(manifest.openStream()));

        String inputLine;
        String json;
        StringBuilder sb = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        json = sb.toString();
        JSONParser jParse = new JSONParser();
        JSONObject jObj = (JSONObject) jParse.parse(json);


        JSONArray jsonArray = (JSONArray) jObj.get("sequences");
        for (Object o : jsonArray) {
            JSONObject result = (JSONObject) o;
            JSONArray canvases = (JSONArray) result.get("canvases");

            for (Object cnv : canvases) {
                JSONObject canvas = (JSONObject) cnv;
                String label = (String) canvas.get("label");
                System.out.println(label);
                JSONArray images = (JSONArray) canvas.get("images");

                for (Object img : images) {
                    JSONObject image = (JSONObject) img;
                    JSONObject resources = (JSONObject) image.get("resource");

                    String resId = (String) resources.get("@id");
                    System.out.println(resId);
                    Long height = (Long) canvas.get("height");
                    String heightStr = Long.toString(height);
                    Long width = (Long) canvas.get("width");
                    String widthStr = Long.toString(width);

                    //checking to see if it has a .jpg extension, might need more? Or be more generic?
                    if (resId.contains(".jpg")) {
                        System.out.println(resId);
                    } else {
                        String imageUrl = createUrl(resId, heightStr, widthStr);
                        System.out.println(imageUrl);
                    }
                }
            }
        }

    }


    public static String createUrl(String id, String height, String width) {
        //generic URL to get the image .jpg
        return id + "/full/" + height + "," + width + "/0/" + "default.jpg";
    }
}
