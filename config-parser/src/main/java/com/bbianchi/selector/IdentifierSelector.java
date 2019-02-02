package com.bbianchi.selector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bbianchi.view.View;

/**
 * Identity Selector extracts and validates a candidate's id. NOTE THAT THIS IS DIFFERENT
 * FROM THE className
 */
public class IdentifierSelector implements ISelector {

    /**
     * Regex used to parse a selector
     */
    Pattern identityPattern = Pattern.compile("#(\\w+)");

    /**
     * Validates whether or not the selector causes this candidate to be a result
     * @param selector the query we use to validate the candidate
     * @param view the candidate we are validating
     * @return whether or not the candidate is valid
     */
    public boolean apply(String selector, View view) {

        if (view == null || view.getIdentifier() == null || selector == null || selector.isEmpty()) {
            return false;
        }

        return view.getIdentifier().equals(selector);
    }

    /**
     * Given a selector, return the identifier subquery
     * @param selector a selector used to check for candidates
     * @return the identity subquery used for applying to a candidate
     */
    public String extract(String selector) {
        Matcher matcher = identityPattern.matcher(selector);        
        
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