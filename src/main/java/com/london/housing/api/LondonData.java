package com.london.housing.api;

import com.london.housing.model.api.LondonResponse;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author smith
 */
public interface LondonData {

    String LONDON_DATASTORE_URL = "http://data.london.gov.uk/api/3/action/";

    String TRANSPORT_CRIME_ID = "transport-crime-london";
    String TRANSPORT_ACCESSIBILITY_ID = "public-transport-accessibility-levels";
    String WARD_PROFILES = "ward-profiles-and-atlas";

    @GET("/package_show")
    LondonResponse loadTransportData(@Query("id") String id);

}
