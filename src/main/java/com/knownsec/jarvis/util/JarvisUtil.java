package com.knownsec.jarvis.util;

import com.intellij.notification.*;
import com.intellij.openapi.ui.MessageType;
import com.knownsec.jarvis.message.ChatGPTBundle;

public class JarvisUtil {
    public static void Notify(String message, NotificationType type) {
        Notifications.Bus.notify(
                new Notification(ChatGPTBundle.message("group.id"),
                        "Jarvis",
                        message, type));
    }
}
