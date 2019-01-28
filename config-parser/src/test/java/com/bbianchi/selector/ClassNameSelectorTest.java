package com.bbianchi.selector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import com.bbianchi.view.View;

import org.junit.Test;

public class ClassNameSelectorTest {

    // Selector we will use for this test;
    ClassNameSelector selector = new ClassNameSelector();

    @Test
    public void testNullAndEmptySelectorsCase() {
        View view = new View();
        assertFalse(selector.apply(null, view));
        assertFalse(selector.apply("", view));
    }

    @Test
    public void testNullAndEmptyView() {
        View nullView = null;
        View emptyView = new View();
        assertFalse(selector.apply("s", nullView));
        assertFalse(selector.apply("s", emptyView));
    }
    
    @Test
    public void testValidSelectors() {
        View view = new View();

        List<String> classNames = Arrays.asList("container", "child", "element");

        view.setClassNames(classNames);
        
        assertTrue(selector.apply("container", view));
        assertTrue(selector.apply("child", view));
        assertTrue(selector.apply("element", view));
    }
}