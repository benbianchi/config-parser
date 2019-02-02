package com.bbianchi;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.bbianchi.selector.ClassNameSelector;
import com.bbianchi.selector.ClassSelector;
import com.bbianchi.selector.IdentifierSelector;
import com.bbianchi.view.View;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    static String MALFORMED_JSON_FILE_PATH = "./src/test/resources/configs/malformed-config.json";

    static String VALID_JSON_FILE_PATH = "./src/test/resources/configs/settings-config.json";

    @BeforeClass
    public static void setupTrialSelectors() {
        Trial.selectors.add(new ClassSelector());
        Trial.selectors.add(new IdentifierSelector());
        Trial.selectors.add(new ClassNameSelector());
    }

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
    @Test(expected = IOException.class)
    public void testAttemptToReadMalformedFile() throws IOException {
        App.readViewFromFile(MALFORMED_JSON_FILE_PATH);
    }

    /**
     * Check to see if we handle bad JSON
     */
    @Test
    public void testAttemptToReadValidFile() throws IOException {
        View v = App.readViewFromFile(VALID_JSON_FILE_PATH);
        Trial t = new Trial("Input", v);
        assertEquals(t.search().size(), 26);

        Trial t2 = new Trial("#System", v);
        assertEquals(t2.search().size(), 1);

        Trial t3 = new Trial(".container", v);
        assertEquals(t3.search().size(), 6);
 
        Trial t4 = new Trial(".columns.container", v);
        assertEquals(t4.search().size(), 1);
    }


    @AfterClass
    public static void removeSelectors() {
        Trial.selectors = new ArrayList<>();
    }}
