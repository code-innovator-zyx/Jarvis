package com.knownsec.jarvis;

import com.intellij.openapi.project.Project;
import com.knownsec.jarvis.ui.MainPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 * @author zouyx
 */
public class GPT35TurboToolWindow {

    private final MainPanel panel;

    public GPT35TurboToolWindow(@NotNull Project project) {
        panel = new MainPanel(project, false);
    }

    public JPanel getContent() {
        return panel.init();
    }

    public MainPanel getPanel() {
        return panel;
    }


    /**
     * rapidly get input focus by keystorke f key
     */
    public void registerKeystrokeFocus() {
        @NotNull JTextArea myTextArea = panel.getSearchTextArea().getTextArea();
        myTextArea.setFocusable(true);
        Action focusAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTextArea.requestFocus();
            }
        };

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F, 0);
        InputMap inputMap = myTextArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(keyStroke, "focusAction");
        myTextArea.getActionMap().put("focusAction", focusAction);
    }
}
