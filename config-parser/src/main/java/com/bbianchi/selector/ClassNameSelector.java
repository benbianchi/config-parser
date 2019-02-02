package com.bbianchi.selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bbianchi.view.View;

/**
 * Class Selector extracts information from the query parameter and can process that information, telling
 * us if the candidate fits the criteria.
 */
public class ClassNameSelector implements ISelector {

    /**
     * Regex Pattern for Class Names
     */
    Pattern classNamePattern = Pattern.compile("\\.(\\w+)");

    /**
     * Apply a the validation to the view, given our selector
     * @param selector the selector string to use
     * @param view the candidate we validating.
     * @return whether or not this view is valid
     */
    public boolean apply(String selector, View view) {
        if (view == null || view.getClassNames() == null || selector == null || selector.isEmpty() || view.getClassNames().isEmpty()) {
            return false;
        }

        return view.getClassNames().containsAll(Arrays.asList(selector.split("\\.")));
    }

    /**
     * Extract out the class names and return them as a string seperated by dots
     * @param selector The Selector we are parsing
     * @return A string that we can use to `apply` on a view.
     */
    public String extract(String selector) {
        Matcher matcher = classNamePattern.matcher(selector);

        List<String> listOfClassNames = new ArrayList<>();
        
        matcher.results().filter(
            match -> match.group(1) != null
        ).forEach(
            match -> listOfClassNames.add(match.group(1))
        );

        return listOfClassNames.size() == 0 ? null : String.join(".", listOfClassNames);
    }
}