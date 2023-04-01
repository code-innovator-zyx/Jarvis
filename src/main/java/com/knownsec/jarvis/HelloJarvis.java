package com.knownsec.jarvis;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;

public class HelloJarvis extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        NotificationGroup notificationGroup = new NotificationGroup("HelloJarvis", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification("邹玉玺，贾维斯为您服务", MessageType.INFO);
        Notifications.Bus.notify(notification);
    }
}
