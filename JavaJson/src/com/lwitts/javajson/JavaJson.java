package com.lwitts.javajson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class JavaJson {
    public static void main(String[] args) {

        //
//        JSONParser jsonParser = new JSONParser();
//
//        try {
//            JSONObject jsObject = (JSONObject) jsonParser.parse(new FileReader(""));
//
//            String value = (String) jsObject.get("Salary");
//
//            System.out.println("Salary: " + value);
//
//            JSONArray jsonArray = (JSONArray) jsObject.get("contact");
//            //System.out.println(jsonArray);
//
//            Iterator<String> iterator = jsonArray.iterator();
//            while (iterator.hasNext()){
//                System.out.println(iterator.next());
//            }
//        }
//        catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        catch (ParseException e){
//            e.printStackTrace();
//        }

        //
        //boiler-plate for the code
//        JSONParser jsonParser = new JSONParser();
//        try {
//            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(""));
//
//
//
//            JSONObject js = (JSONObject) jsonObject.get("label");
//            JSONArray jsonArray = (JSONArray) js.get("en");
//
//            System.out.println("Type: " + jsonObject.get("id"));
//            System.out.println("Label: " + jsonObject.get("label"));
//            System.out.println(js);
//            System.out.println(jsonArray);
//
//
//        }
//        catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        catch (ParseException e){
//            e.printStackTrace();
//        }

        //
        JSONParser jsonParser = new JSONParser();
        try {
            //JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(""));
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(""));

            JSONArray jsonArray = (JSONArray) jsonObject.get("sequences");

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject result = (JSONObject) jsonArray.get(i);
                JSONArray canvases = (JSONArray) result.get("canvases");

                for (int j = 0; j < canvases.size(); j++) {
                    JSONObject canvas = (JSONObject) canvases.get(j);
                    String label = (String) canvas.get("label");
                    System.out.println(label);
                    JSONArray images = (JSONArray) canvas.get("images");

                    for (int k = 0; k < images.size(); k++) {
                        JSONObject image = (JSONObject) images.get(k);
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static String createUrl(String id, String height, String width) {
        //generic URL to get the image .jpg
        String url = id + "/full/" + height + "," + width + "/0/" + "default.jpg";
        return url;
    }
}
