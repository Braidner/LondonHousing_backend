package com.london.housing.utils;

import com.google.gson.Gson;
import com.london.housing.model.Location;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by smith / 01.05.2015.
 */
@Component
public class JsonLoader {

    public Location loadLondonPolygon(File file) throws IOException {
        String json = loadResource(file);
        return new Gson().fromJson(json, Location.class);
    }

    private String loadResource(File file) throws IOException {
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = new FileInputStream(file)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        }

        return writer.toString();
    }
}
