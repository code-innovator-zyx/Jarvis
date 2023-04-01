package com.knownsec.jarvis;

import com.intellij.openapi.project.Project;
import com.knownsec.jarvis.ui.MainPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


/**
 * @author zouyx
 */
public class ChatGPTToolWindow {

  private final MainPanel panel;

  public ChatGPTToolWindow(@NotNull Project project) {
    panel = new MainPanel(project, true);
  }

  public JPanel getContent() {
    return panel.init();
  }

  public MainPanel getPanel() {
    return panel;
  }
}
