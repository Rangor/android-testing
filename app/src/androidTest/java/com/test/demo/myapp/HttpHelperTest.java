package com.test.demo.myapp;


import android.net.Uri;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class HttpHelperTest {

    @Test
    public void test_get_metadata() throws IOException {
//        TileMetaData tileMetaData = HttpHelper.getTileMetaData("http://stageapi.smartansatt.no/mockedpartnerapi?id=7");
//        assertNotNull(tileMetaData);
//        assertTrue(tileMetaData.version == 1);
    }

    @Test
    public void test_get_tiles() throws  IOException{
//        Tile[] tiles = HttpHelper.getTileArray("F8kpEkV1Aw2PjA1T4c1tw7f7VvcYdFAm4wTSyDX15CPYfS6vnBSpBg");
//        assertNotNull(tiles);
//        assertTrue(tiles.length > 2);
    }

    @Test
    public void testUrlFormatting_3() {
        String query = Uri.encode("Fri Mar 03 11:34:40 GMT+01:00 2017", "utf-8");
        String url = "https://ks.rorkjop.no/api/member/projects?updated_since=" + query;
        System.out.println(url);
    }
}
