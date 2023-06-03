package com.cercetare.ecommerce.utils;

import com.cercetare.ecommerce.entity.Country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class TestData {

    public static List<Country> readCountriesFromCsv(String filePath) {
        List<Country> countries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0].trim());
                String code = fields[1].trim();
                String name = fields[2].trim().replaceAll("\"", "");
                Country country = new Country(id, code, name);
                countries.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countries;
    }
}
