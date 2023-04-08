package com.knownsec.jarvis;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;

import java.util.Random;

import com.knownsec.jarvis.util.JarvisUtil;
import org.jetbrains.annotations.NotNull;

public class HelloJarvis extends AnAction {
    private String[] messages = {"在的，老大，您需要我为您做些什么？除了把您的衣服洗干净，当然。", "老大，您的要求已经超出了我的能力范围。我建议您联系神奇女侠。", "抱歉，老大，我正在和钢铁侠玩‘谁是谁的老板’游戏。", "老大，您的请求已被记录。请稍等，我正在为您寻找一名合适的超级英雄。", "老大，我正在和黑寡妇讨论如何打败洛基。您能稍等一下吗？", "老大，我正在和蜘蛛侠比赛谁更擅长爬墙。", "老大，我正在和雷神讨论如何打造一把更强大的锤子。", "老大，我正在和美国队长比赛谁更擅长跑步。", "老大，我正在和绿巨人讨论如何控制他的愤怒。", "老大，我正在和星爵一起唱卡拉OK。您想加入吗？"};

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // TODO: insert action logic here
        Random random = new Random();
        int index = random.nextInt(messages.length);
        JarvisUtil.Notify(messages[index], MessageType.INFO);
    }
}
