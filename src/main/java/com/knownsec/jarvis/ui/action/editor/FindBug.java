package com.knownsec.jarvis.ui.action.editor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;
import com.knownsec.jarvis.message.ChatGPTBundle;
import com.knownsec.jarvis.util.JarvisUtil;

public class FindBug extends AbstractEditorAction {

    public FindBug() {
        super(() -> ChatGPTBundle.message("action.code.wrong.menu"));
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        key = "帮我找一下下面这段代码的bug:";
        JarvisUtil.Notify("好吧，我帮你review一下这段代码。", MessageType.INFO);
        super.actionPerformed(e);
    }
}
