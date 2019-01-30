package com.bbianchi;

import java.util.ArrayList;
import java.util.List;

import com.bbianchi.view.View;

public class Trial {

    private String selector;

    private View view;

    public Trial(String selector, View view) {
        this.selector = selector;
        this.view = view;
    }

    public List<View> search() {
        ArrayList<View> results = new ArrayList<>();
        recursiveSearch(view, selector, results);

        return results;
    }

    private View recursiveSearch(View currentView, String selectorString, List<View> results) {

    }

    
}
