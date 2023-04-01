package com.knownsec.jarvis.ui.action.editor;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.knownsec.jarvis.message.ChatGPTBundle;
import org.jetbrains.annotations.NotNull;

/**
 * @author zouyx
 */
public class OptimizeAction extends AbstractEditorAction {

    public OptimizeAction() {
        super(() -> ChatGPTBundle.message("action.code.optimize.menu"));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        key = "help me to optimize this code:";
        super.actionPerformed(e);
    }

}
