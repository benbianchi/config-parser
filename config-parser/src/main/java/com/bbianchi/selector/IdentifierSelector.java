package com.bbianchi.selector;

import com.bbianchi.view.View;

public class IdentifierSelector implements ISelector {
    
    public boolean apply(String selector, View view) {

        if (view == null || view.getIdentifier() == null || selector == null || selector.isEmpty()) {
            return false;
        }

        return view.getIdentifier().equals(selector);
    }
}