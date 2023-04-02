package com.knownsec.jarvis.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.MessageType;

public class JarvisUtil {
    public static void Notify(String message, MessageType type) {
        NotificationGroup notificationGroup = new NotificationGroup("HelloJarvis", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification(message, type);
        Notifications.Bus.notify(notification);
    }
}
