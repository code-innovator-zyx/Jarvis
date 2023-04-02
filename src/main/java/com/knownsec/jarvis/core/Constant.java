package com.knownsec.jarvis.core;

import com.knownsec.jarvis.util.HtmlUtil;

/**
 * @author zouyx
 */
public class Constant {

    public static final String CHATGPT_CONTENT =
                    "**Hello Boss**: \n\n<br />"+
                    "贾维斯一代已加载完成，很高兴为您服务. <br />" +
                            "**你有任何问题都可以直接问我.**\n\n<br />" +
                    "**fuck**: \n\n<br />" +
                    "如果你想要了解我的原理: 可以参考chatGpt   😋你懂得 \n\n<br />";

    public static final String GPT35_TURBO_CONTENT =
            "**Hello Boss**: \n\n<br />"+
                    "贾维斯3.5已加载完成，很高兴为您服务. <br />" +
                    "**你有任何问题都可以直接问我.**\n\n<br />" +
                    "**fuck**: \n\n<br />" +
                    "如果你想要了解我的原理:  可以参考chatGpt  😋你懂得\n\n<br />";


    public static String getChatGPTContent() {
        return HtmlUtil.md2html(CHATGPT_CONTENT);
    }

    public static String getGpt35TurboContent() {
        return HtmlUtil.md2html(GPT35_TURBO_CONTENT);
    }
}
