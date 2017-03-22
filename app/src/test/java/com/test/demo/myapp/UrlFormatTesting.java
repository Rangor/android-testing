package com.test.demo.myapp;


import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlFormatTesting {

    @Test
    public void testUrlFormatting() throws UnsupportedEncodingException {
        String query = URLEncoder.encode("apples oranges", "UTF-8");
        String url = "http://stackoverflow.com/search?q=" + query;
        System.out.println(url);
    }

    @Test
    public void testUrlFormatting_2() throws UnsupportedEncodingException {
        String query = URLEncoder.encode("Fri Mar 03 11:34:40 GMT+01:00 2017", "UTF-8");
        String url = "https://ks.rorkjop.no/api/member/projects?updated_since=" + query;
        System.out.println(url);
    }

}
