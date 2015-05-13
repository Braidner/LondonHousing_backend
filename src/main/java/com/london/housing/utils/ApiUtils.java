package com.london.housing.utils;

import com.google.gson.*;
import com.london.housing.api.LondonData;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author smith
 */
public class ApiUtils {

    public static LondonData getLondonApi() {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
            }
        });

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(LondonData.LONDON_DATASTORE_URL)
                .setConverter(new GsonConverter(builder.create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return adapter.create(LondonData.class);
    }
}
