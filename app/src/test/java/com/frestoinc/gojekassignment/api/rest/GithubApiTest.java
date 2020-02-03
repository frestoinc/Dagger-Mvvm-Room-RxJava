package com.frestoinc.gojekassignment.api.rest;

import com.frestoinc.gojekassignment.data.Constants;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
public class GithubApiTest {

    @Test
    public void testApi() throws IOException {
        URLConnection conn = new URL(Constants.BASE_URL.concat(Constants.REST_CONSTANT)).openConnection();
        InputStream inputStream = conn.getInputStream();

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            for (String s; (s = reader.readLine()) != null; ) {
                sb.append(s);
            }
        }
        assert sb.length() > 0;
    }
}
