package com.bbianchi.selector;

import com.bbianchi.view.View;

public interface ISelector {
    
    boolean apply(String selector, View element);
}