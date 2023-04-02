package com.knownsec.jarvis.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author zouyx
 */
public class StringUtil extends com.intellij.openapi.util.text.StringUtil {

    // 校验字符是否是一个数字类型的
    public static boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static @NotNull String stripHtml(@NotNull String html, @Nullable String breaks) {
        if (breaks != null) {
            html = html.replaceAll("<br/?>", breaks);
        }

        return html.replaceAll("<(.|\n)*?>", "");
    }
}
