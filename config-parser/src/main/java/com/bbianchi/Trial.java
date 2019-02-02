package com.bbianchi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bbianchi.selector.ClassNameSelector;
import com.bbianchi.selector.ClassSelector;
import com.bbianchi.selector.ISelector;
import com.bbianchi.selector.IdentifierSelector;
import com.bbianchi.view.View;

public class Trial {

    /**
     * Selector String used in the trial
     */
    private final String selectorString;

    /**
     * root view node
     */
    private final View view;

    /**
     * Populate the selectors via list to make it easier
     */
    public static List<ISelector> selectors = new ArrayList<>();

    /**
     * Create a Trial
     * @param selectorString the selector string to query
     * @param view the root view node
     */
    public Trial(String selectorString, View view) {
        this.selectorString = selectorString;
        this.view = view;
    }

    /**
     * Function that can be called by other packages that allows us to query the tree
     * @return A list of results
     */
    public List<View> search() {
        ArrayList<View> results = new ArrayList<>();

        Queue<String> tokens = new LinkedList<>(Arrays.asList(selectorString.split(" ")));

        recursiveSearch(view, tokens, results);

        return results;
    }

    /**
     * Searches the tree recursively
     * @param currentView the current view we are looking at
     * @param tokens a queue of tokens that allows many selectors
     * @param results the list of results
     */
    private void recursiveSearch(View currentView, Queue<String> tokens, List<View> results) {

        String currentSelectorString = tokens.peek();

        boolean isSuccessful = true;

        // For every selector
        for (ISelector selector : selectors) {
            String extractedString = selector.extract(currentSelectorString);

            // if we can extract something out of it
            if (extractedString == null) {
                continue;
            }

            // and it runs through our validations
            if (!selector.apply(extractedString, currentView)) {
                isSuccessful = false;
            }
        }

        // If we are successful, but are not at the end of the selector, draw from the queue
        // Otherwise we are successful, add to the list of results.
        if (isSuccessful) {
            if (tokens.size() > 1) {
                tokens.poll();
            } else {
            results.add(currentView);
            }
        }

        // Crawl subviews
        for (View subview : currentView.getSubviews()) {
            recursiveSearch(subview, tokens, results);          
        }

        // Crawl content View
        if (currentView.getContentView() != null) {
            recursiveSearch(currentView.getContentView(), tokens, results);          
        }

        // Crawl Control view
        if (currentView.getControl() != null) {
            recursiveSearch(currentView.getControl(), tokens, results);          
        }
        
    }
}
