package com.london.housing.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.london.housing.api.LondonData;
import com.london.housing.model.Statistics;
import com.london.housing.model.api.LondonResponse;
import com.london.housing.utils.ApiUtils;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author smith
 */
@Service
public class TransportService {

    public static final Gson GSON = new GsonBuilder().create();

    public Statistics loadTransportData(String[] code) throws Exception {
        LondonData londonApi = ApiUtils.getLondonApi();
        LondonResponse londonResponse = londonApi.loadTransportData(LondonData.WARD_PROFILES);

        InputStream inputStream = new URL(londonResponse.getResult().getResources().get(0).getUrl()).openStream();
        return parseTransportFile(inputStream, code);
    }

    private Statistics parseTransportFile(InputStream file, String[] code) throws Exception {
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(1);

        JSONObject json = new JSONObject();
        JSONArray rows = new JSONArray();

        List<String> headers = new ArrayList<>();

        int rowCount = 1;

        for (Row row : sheet) {
            int cellCount = 0;
            JSONObject ward = new JSONObject();

            for (Cell cell : row) {
                if (rowCount == 1) {
                    if (cell.getStringCellValue().equals("Ward name")) headers.add("wardName");
                    else if (cell.getStringCellValue().equals("New code")) headers.add("code");
                    else if (cell.getStringCellValue().equals("Median House Price (£) - 2014"))
                        headers.add("housePrice");
                    else if (cell.getStringCellValue().equals("Crime rate - 2013/14")) headers.add("crimeRate");
                    else if (cell.getStringCellValue().equals("Average Public Transport Accessibility score - 2014"))
                        headers.add("transport");
                    else headers.add(cell.getStringCellValue());
                } else if (row.getCell(2) != null && Arrays.asList(code).contains(row.getCell(2).getStringCellValue())) {
                    try {
                        ward.put(headers.get(cellCount), cell.getStringCellValue());
                    } catch (Exception e) {
                        ward.put(headers.get(cellCount), cell.getNumericCellValue());
                    }
                }
                cellCount++;
            }

            if (row.getCell(2) != null && Arrays.asList(code).contains(row.getCell(2).getStringCellValue())) {
                rows.put(ward);
            }
            rowCount++;
        }

        json.put("wards", rows);

        return GSON.fromJson(json.toString(), Statistics.class);
    }

}
