package com.dowrow.blanquealo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Diego on 17/06/2015.
 */
public class Blanqueador {

    private HashMap<String, String> dic;

    public Blanqueador (Context context) {
        dic = new HashMap<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.diccionario)));
        String line;
        try {

            while ((line = in.readLine()) != null) {
                String palabras[] = line.split(",");
                dic.put(palabras[0], palabras[1]);
            }

        }catch (Exception e) {

        }
    }

    public String blanquear(String tweet) {

        for (String palabraTabu : dic.keySet()) {
            tweet = tweet.replaceAll("(?i)" + palabraTabu, dic.get(palabraTabu));
        }

        return tweet;
    }

}

