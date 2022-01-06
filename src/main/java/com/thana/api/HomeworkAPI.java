package com.thana.api;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HomeworkAPI {

    public static final String API_URL = "https://api.google.com/cloud-engine-scheduled/dsv-server-default/cast/freeplan/8rdHSw6yMvuRShcJEHlGMo_/";

    public static void main(String[] args) throws IOException {
        URL url = new URL(API_URL);
        String html = IOUtils.toString(url, StandardCharsets.UTF_8);

        // In Maintenance
    }
}
