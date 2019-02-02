package com.bbianchi.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

public class ViewTest {

    private Object deserialize(String s, Class clazz) {
        try {
            return jsonObjectMapper.readValue(s, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        return null;
    }

    ObjectMapper jsonObjectMapper = new ObjectMapper();

    @Test
    public void viewHasIdentifierField() {

        String viewAsString = "{\"identifier\":\"me\"}";
        View view = (View) deserialize(viewAsString, View.class);
        assertEquals(view.getIdentifier().equals("me"), true);
    }

    @Test
    public void viewHasClassField() {
        String viewAsString = "{\"identifier\":\"me\", \"class\":\"StackView\"}";
        View view = (View) deserialize(viewAsString, View.class);

        assertEquals(view.getClassField().equals("StackView"), true);
    }

    @Test
    public void viewHasClassNameField() {
        String viewAsString = "{\"identifier\":\"me\", \"class\":\"StackView\", \"classNames\":[\"container\"]}";
        View view = (View) deserialize(viewAsString, View.class);

        assertEquals(view.getClassNames().contains("container"), true);

    }

    @Test
    public void viewHasSubViews() {
        String subviewAsString = "{\"identifier\":\"childIdentifier\", \"class\":\"ChildStackView\", \"classNames\":[\"child\"]}";
        String viewAsString = String.format(
                "{\"identifier\":\"me\", \"class\":\"StackView\", \"classNames\":[\"container\"], \"subviews\":[%s]}",
                subviewAsString);
        View view = (View) deserialize(viewAsString, View.class);

        assertEquals(view.getSubviews().size(), 1);

        assertEquals(view.getSubviews().get(0).getClassField().equals("ChildStackView"), true);
        assertEquals(view.getSubviews().get(0).getClassNames().contains("child"), true);
        assertEquals(view.getSubviews().get(0).getIdentifier().equals("childIdentifier"), true);
    }

    @Test
    public void canSerializeFixture() {
        try {
            jsonObjectMapper.readValue(new File("./src/test/resources/configs/settings-config.json"), View.class);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(true);
    }

}