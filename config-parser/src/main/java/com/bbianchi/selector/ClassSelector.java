package com.bbianchi.selector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bbianchi.view.View;

/**
 * Class Selector extracts and validates a candidate's class. NOTE THAT THIS IS DIFFERENT
 * FROM THE className
 */
public class ClassSelector implements ISelector {

    /**
     * The Pattern used to scrape the Class out of a string.
     */
    Pattern classPattern = Pattern.compile("^(\\w+)");

    /**
     * Validate that the selector and the candidate match.
     *
     * @param selector the selector to use for searching for results
     * @param view the candidate we are checking
     * @return whether or not this view is valid
     */
    public boolean apply(String selector, View view) {

        if (view == null || view.getClassField() == null || selector == null || selector.isEmpty()) {
            return false;
        }

        return view.getClassField().equals(selector);
    }

    /**
     * Extract out the valid selector subquery that we will use to check candidates
     * @param selector the selector we are parsing
     * @return a string we can easily use on a candidate.
     */
    public String extract(String selector) {
        Matcher matcher = classPattern.matcher(selector);
        
        try {
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IllegalStateException ise) {
            return null;
        }

        return null;
    }
}