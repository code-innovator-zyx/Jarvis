package com.knownsec.jarvis.ui.action.editor;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;
import com.knownsec.jarvis.message.ChatGPTBundle;
import com.knownsec.jarvis.util.JarvisUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author zouyx
 */
public class BugAction extends AbstractEditorAction {

    public BugAction() {
        super(() -> ChatGPTBundle.message("action.code.wrong.menu"));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        key = "帮我找一下下面这段代码的bug:";
        JarvisUtil.Notify("好吧，我帮你review一下这段代码。", MessageType.INFO);
        super.actionPerformed(e);
    }

}
