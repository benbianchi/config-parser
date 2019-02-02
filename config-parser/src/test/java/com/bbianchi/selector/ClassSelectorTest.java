package com.bbianchi.selector;

import static org.junit.Assert.*;

import com.bbianchi.view.View;

import org.junit.Test;

public class ClassSelectorTest {

    // Selector we will use for this test;
    ClassSelector selector = new ClassSelector();

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
        view.setClassField("StackView");
        
        assertTrue(selector.apply("StackView", view));
        assertFalse(selector.apply("Input", view));
    }
    
    @Test
    public void testExtraction() {
        View view = new View();
        view.setClassField("StackView");
        
        assertEquals(selector.extract("StackView#id.class"), "StackView");
        assertFalse(selector.apply("Input", view));
    }
}