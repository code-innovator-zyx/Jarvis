package com.knownsec.jarvis.ui.action.editor;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;
import com.knownsec.jarvis.message.ChatGPTBundle;
import com.knownsec.jarvis.util.JarvisUtil;
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
        key = "帮我优化一下下面这段代码，并说明为什么这样子优化:";
        JarvisUtil.Notify("没问题，马上帮你看看有什么地方可以优化。", NotificationType.INFORMATION);
        super.actionPerformed(e);
    }

}
