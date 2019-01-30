package com.bbianchi;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.bbianchi.view.View;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    static String MALFORMED_JSON_FILE_PATH = "./src/main/test/resources/configs/malformed-config.json";

    static String VALID_JSON_FILE_PATH = "./src/main/test/resources/configs/malformed-config.json";

    /**
     * Check to see if we handle an empty file string
     */
    @Test(expected = FileNotFoundException.class)
    public void testEmptyFilePath() throws IOException {
        App.readViewFromFile("");
    }

    /**
     * Check to see if we handle an null file string
     */
    @Test(expected = FileNotFoundException.class)
    public void testNullFilePath() throws IOException {
        App.readViewFromFile(null);
    }

    /**
     * Check to see if we handle bad JSON
     */
    @Test(expected = FileNotFoundException.class)
    public void testAttemptToReadMalformedFile() throws IOException {
        App.readViewFromFile(MALFORMED_JSON_FILE_PATH);
    }

    /**
     * Check to see if we handle bad JSON
     */
    @Test
    public void testAttemptToReadValidFile() throws IOException {
        File f = new File(".");
        App.readViewFromFile(VALID_JSON_FILE_PATH);
    }
}
