package com.knownsec.jarvis.ui.action.editor;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;
import com.knownsec.jarvis.message.ChatGPTBundle;
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
        key = "Find the bug in the code below:";
//        NotificationGroup notificationGroup = new NotificationGroup("find bug", NotificationDisplayType.BALLOON, true);
//        Notification notification = notificationGroup.createNotification("好吧，我帮你review一下这段代码。", MessageType.INFO);
//        Notifications.Bus.notify(notification);
        super.actionPerformed(e);
    }

}
