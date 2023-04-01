package com.knownsec.jarvis.ui.action;

import com.intellij.icons.AllIcons;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.knownsec.jarvis.message.ChatGPTBundle;
import org.jetbrains.annotations.NotNull;

/**
 * @author zouyx
 */
public class PluginAction extends DumbAwareAction {

    public PluginAction() {
        super(() -> ChatGPTBundle.message("action.plugins"), AllIcons.Nodes.Plugin);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        BrowserUtil.browse("https://plugins.jetbrains.com/organizations/obiscr");
    }
}
