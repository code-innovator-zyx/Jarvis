package com.knownsec.jarvis.ui.action.editor;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.knownsec.jarvis.message.ChatGPTBundle;
import org.jetbrains.annotations.NotNull;

/**
 * @author zouyx
 */
public class ExplainAction extends AbstractEditorAction {

    public ExplainAction() {
        super(() -> ChatGPTBundle.message("action.code.explain.menu"));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        key = "Explain this code:";
        super.actionPerformed(e);
    }

}
