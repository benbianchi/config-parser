package com.bbianchi.view;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class View implements Serializable {
    
    @JsonProperty
    private String identifier= "";

    @JsonProperty("class")
    private String elementClass = "";

    @JsonProperty("classNames")
    private List<String> classNames;

    @JsonProperty("subviews")
    private List<View> subviews;


    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String newIdentifier) {
        this.identifier = newIdentifier;
    }

    public String getClassField() {
        return this.elementClass;
    }

    public void setClassField(String newClassField) {
        this.elementClass = newClassField;
    }
    
    public List<String> getClassNames() {
        return this.classNames;
    }

    public void setClassNames(List<String> newClassNames) {
        this.classNames = newClassNames;
    }
   
    public List<View> getSubviews() {
        return this.subviews;
    }

    public void setSubViews(List<View> newSubviews) {
        this.subviews = newSubviews;
    }
}