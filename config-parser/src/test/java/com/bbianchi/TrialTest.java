package com.bbianchi;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bbianchi.selector.ClassNameSelector;
import com.bbianchi.selector.ClassSelector;
import com.bbianchi.selector.IdentifierSelector;
import com.bbianchi.view.View;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrialTest {

    @BeforeClass
    public static void setupTrialSelectors() {
        Trial.selectors.add(new ClassSelector());
        Trial.selectors.add(new IdentifierSelector());
        Trial.selectors.add(new ClassNameSelector());
    }

    @Test
    public void testSearchingOnEmptyViewReturnsNothing() {
        Trial trial = new Trial(".Input", new View());
        
        List<View> results = trial.search();

        assertEquals(results.size(), 0);
    }

    @Test
    public void testSearchingOnViewThatSatisfiesCriteria() {

        Set<String> classNames = new HashSet<String>();
        classNames.add("clazz");

        View view = new View();
        view.setClassField("Input");
        view.setIdentifier("Identifier");
        view.setClassNames(classNames);

        Trial trial = new Trial("Input", view);
        assertEquals(trial.search().get(0), view);
    }

    @Test
    public void testSearchingOnViewSelectorChain() {

        Set<String> classNames = new HashSet<String>();
        classNames.add("clazz");

        View view = new View();
        view.setClassField("Input");
        view.setIdentifier("Identifier");
        view.setClassNames(classNames);

        View childView = new View();
        childView.setClassField("Child");
        childView.setIdentifier("subView");

        View controlView = new View();
        controlView.setClassField("Child");
        controlView.setIdentifier("control");

        View contentView = new View();
        contentView.setClassField("Child");
        contentView.setIdentifier("content");

        view.getSubviews().add(childView);
        view.setControl(controlView);
        view.setContentView(contentView);

        

        Trial trialCompound = new Trial("Input Child", view);
        Trial trialMultipleSelectors = new Trial("Child#content", view);

        assertEquals(trialCompound.search().size(), 3);
        assertEquals(trialMultipleSelectors.search().size(), 1);
    }

    @AfterClass
    public static void removeSelectors() {
        Trial.selectors = new ArrayList<>();
    }
} 