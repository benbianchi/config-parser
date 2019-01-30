package com.bbianchi;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.bbianchi.view.View;

import org.junit.Test;

public class TrialTest {

    @Test
    public void testSearchingOnEmptyViewReturnsNothing() {
        Trial trial = new Trial(".Input", new View());
        
        List<View> results = trial.search();

        assertEquals(results.size(), 0);
    }

    @Test
    public void testSearchingOnViewThatSatisfiesCriteria() {

        ArrayList<String> classNames = new ArrayList<String>();
        classNames.add(0, "clazz");

        View view = new View();
        view.setClassField("Input");
        view.setIdentifier("Identifier");
        view.setClassNames(classNames);

        Trial trial = new Trial(".Input", view);
        assertEquals(trial.search().get(0), view);
        
    }
} 