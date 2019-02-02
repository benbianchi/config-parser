package com.bbianchi.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Views are elements within the config.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class View implements Serializable {
    
    @JsonProperty
    private String identifier= "";

    @JsonProperty("class")
    private String classField = "";

    @JsonProperty("classNames")
    private Set<String> classNames = new HashSet<>();

    @JsonProperty("subviews")
    private List<View> subviews = new ArrayList<>();

    @JsonProperty("control")
    private View control;
    
    @JsonProperty("contentView")
    private View contentView;

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String newIdentifier) {
        this.identifier = newIdentifier;
    }

    public String getClassField() {
        return this.classField;
    }

    public void setClassField(String newClassField) {
        this.classField = newClassField;
    }
    
    public Set<String> getClassNames() {
        return this.classNames;
    }

    public void setClassNames(Set<String> newClassNames) {
        this.classNames = newClassNames;
    }
   
    public List<View> getSubviews() {
        return this.subviews;
    }

    public void setSubViews(List<View> newSubviews) {
        this.subviews = newSubviews;
    }

    public View getControl() {
        return this.control;
    }

    public void setControl(View control) {
        this.control = control;
    }

    public View getContentView() {
        return this.contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    
}