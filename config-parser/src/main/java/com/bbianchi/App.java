package com.bbianchi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

import com.bbianchi.selector.ClassNameSelector;
import com.bbianchi.selector.ClassSelector;
import com.bbianchi.selector.IdentifierSelector;
import com.bbianchi.view.View;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This application allows a user to feed in information to the command line via
 * file name or by standard in, and allows the user to query the information with selectors
 */
public class App {


    /**
     * Shamelessly ripped from https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     */
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Error message for when the JSON is malformed
     */
    static final String PARSE_JSON_ERROR = "Unable to Parse Json!";

    /**
     * Template for how we display how many results a query returns
     */
    static final String FOUND_RESULTS_STRING_TEMPLATE = "%sFound %s results.%s\n";

    /**
     * Template for an empty set returned in console!
     */
    static final String EMPTY_RESULTS_STRING_TEMPLATE = "%sUnable to Find any results with that selector. Try again.%s\n";

    /**
     * Delimeter used to space out results.
     */
     static final String RESULT_DELIMETER = "\n";

    /**
     * Code used to signal terminals that this app failed.
     */
    static int APPLICATION_FAILURE_CODE = -1;

    /**
     * This Application's Name
     */
    static String APPLICATION_NAME = "Config Parser";

    /**
     * The view that we deserialized
     */
    static View deserializedView = null;

    /**
     * A resource we use to accept input from the user.
     */
    static Scanner inputScanner;

    /**
     * An ObjectMapper used to transform text to Java Objects
     */
    static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Main entry point into the code.
     * @param args arguments supplied by the user.
     */
    public static void main(String[] args) {

        // Setup for parsing args
        inputScanner = new Scanner(System.in);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        Options appOptions = AppOptions.getOptions();

        CommandLine cmd = null;
        CommandLineParser parser = new DefaultParser();

        // First parse the args
        try {
            cmd = parser.parse(appOptions, args);
        } catch (ParseException parseException) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(APPLICATION_NAME, appOptions);
            System.exit(APPLICATION_FAILURE_CODE);
        }

        // Next, serialize the view, wherever it comes from
        try {
            if (cmd.hasOption(AppOptions.LOAD_FROM_FILE_OPTION)) {
                deserializedView = readViewFromFile(cmd.getOptionValue(AppOptions.LOAD_FROM_FILE_OPTION));
            } else {
                deserializedView = objectMapper.readValue(inputScanner.nextLine(), View.class);
            }
        } catch (Exception e) {
            System.err.println(PARSE_JSON_ERROR);
            e.printStackTrace();

            System.exit(APPLICATION_FAILURE_CODE);
        }

        // Add the current selectors
        Trial.selectors.add(new ClassSelector());
        Trial.selectors.add(new IdentifierSelector());
        Trial.selectors.add(new ClassNameSelector());

        interactWithUser();
    }

    /**
     * Given a file, attempt to read from disk and serialize the view
     * @param filePath the path to the configuration file
     * @return A deserialized view.
     * @throws IOException Happens if something goes wrong trying to access and deserialize the view
     */
    static View readViewFromFile(String filePath) throws IOException {

        View viewFromFile = null;

        if (filePath == null || filePath.isEmpty()) {
            System.err.println("File Not Found");
            throw new FileNotFoundException(filePath);
        }

        try {
            viewFromFile = new ObjectMapper().readValue(new File(filePath), View.class);
        } catch (IOException exception) {
            throw new IOException("Unable to Read File " + filePath);
        }

        return viewFromFile;

    }

    /**
     * Begin a loop interacting with the user.
     */
    private static void interactWithUser() {
        System.out.println("Enter a Selector for the Configuration:");

        // It was not specified to allow the user to end the end, they will have to use sigint
        while (true) {
            String selector = inputScanner.nextLine();

            List<View> results = new Trial(selector, deserializedView).search();

            try {
                if (results.size() > 0) {
                    System.out.println(String.format(FOUND_RESULTS_STRING_TEMPLATE, ANSI_GREEN, results.size(), ANSI_RESET));

                    for (View view: results) {
                        System.out.println(objectMapper.writeValueAsString(view)+ RESULT_DELIMETER);
                    }
                } else {
                    System.out.println(String.format(EMPTY_RESULTS_STRING_TEMPLATE, ANSI_RED, ANSI_RESET));
                }
            } catch (JsonProcessingException jsonProcessingException) {
                System.err.println("Something's wrong with printing out the JSON!");
                jsonProcessingException.printStackTrace();
            }
        }
    }
}
