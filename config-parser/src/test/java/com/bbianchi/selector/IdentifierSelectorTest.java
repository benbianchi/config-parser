package com.bbianchi.selector;

import static org.junit.Assert.*;

import com.bbianchi.view.View;

import org.junit.Test;

public class IdentifierSelectorTest {

    // Selector we will use for this test;
    IdentifierSelector selector = new IdentifierSelector();

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
        view.setIdentifier("identity");
        
        assertTrue(selector.apply("identity", view));
        assertFalse(selector.apply("Alias", view));
    }
}