package com.knownsec.jarvis.ui.action;

import com.intellij.icons.AllIcons;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Separator;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.awt.RelativePoint;
import com.knownsec.jarvis.util.JarvisUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zouyx
 */
public class HelpAction extends DumbAwareAction {

    public HelpAction() {
        super(() -> "Documentation", AllIcons.Actions.Help);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ListPopup actionGroupPopup = JBPopupFactory.getInstance().createActionGroupPopup("Helps",
                new HelpActionGroup(), e.getDataContext(), true, null, Integer.MAX_VALUE);
        MouseEvent source = (MouseEvent) e.getInputEvent();
        actionGroupPopup.show(new RelativePoint(source));
    }

    static class HelpActionGroup extends ActionGroup {
        @Override
        public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
            List<AnAction> actions = new ArrayList<>();
            actions.add(new Announcement());
            actions.add(new Separator());
            actions.add(new DocumentAction());
            return actions.toArray(new AnAction[0]);
        }
    }

    static class DocumentAction extends DumbAwareAction {
        public DocumentAction() {
            super(() -> "Documents", AllIcons.Toolwindows.Documentation);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
//      BrowserUtil.browse("https://chatgpt.en.obiscr.com");
            // TODO: insert action logic here
            JarvisUtil.Notify("嘛也不懂，自己想办法解决........", MessageType.INFO);
        }
    }

    static class Announcement extends DumbAwareAction {
        public Announcement() {
            super(() -> "Announcement", AllIcons.General.InspectionsEye);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            // TODO: insert action logic here
            JarvisUtil.Notify("作甚、作甚，你贾维斯大爷在此！有啥问题么", MessageType.INFO);
//      BrowserUtil.browse("https://github.com/dromara/ChatGPT/discussions/categories/announcements");
        }
    }

}
