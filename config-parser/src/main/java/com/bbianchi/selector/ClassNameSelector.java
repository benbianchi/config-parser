package com.bbianchi.selector;

import com.bbianchi.view.View;

public class ClassNameSelector implements ISelector {
    
    public boolean apply(String selector, View view) {

        if (view == null || view.getClassNames() == null || selector == null || selector.isEmpty()) {
            return false;
        }

        for (String className : view.getClassNames()) {
            if (className == selector) {
                return true;
            }
        }

        return false;
    }
}