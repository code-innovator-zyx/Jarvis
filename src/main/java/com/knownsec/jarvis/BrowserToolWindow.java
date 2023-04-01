package com.knownsec.jarvis;

import com.knownsec.jarvis.ui.BrowserContent;

import javax.swing.*;

/**
 * @author zouyx
 */
public class BrowserToolWindow {

    private final BrowserContent content;

    public BrowserToolWindow() {
        content = new BrowserContent();
    }

    public JPanel getContent() {
        return content.getContentPanel();
    }

    public BrowserContent getPanel() {
        return content;
    }
}
