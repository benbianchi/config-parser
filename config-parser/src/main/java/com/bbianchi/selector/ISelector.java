package com.bbianchi.selector;

import com.bbianchi.view.View;

/**
 * Selectors are used to check candidates to see if the user is truly querying for them
 */
public interface ISelector {
    
    boolean apply(String selector, View element);

    String extract(String selector);
}