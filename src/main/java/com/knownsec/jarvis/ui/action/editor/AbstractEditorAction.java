package com.knownsec.jarvis.ui.action.editor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.NativeFileType;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.util.NlsActions;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.knownsec.jarvis.core.SendAction;
import com.knownsec.jarvis.ui.BrowserContent;
import com.knownsec.jarvis.ui.MainPanel;
import com.knownsec.jarvis.util.JarvisUtil;
import com.knownsec.jarvis.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import static com.knownsec.jarvis.util.JarvisUtil.Notify;

import java.util.function.Supplier;

import static com.knownsec.jarvis.MyToolWindowFactory.ACTIVE_CONTENT;

/**
 * @author zouyx
 */
public abstract class AbstractEditorAction extends AnAction {

    protected String text = "";
    protected String key = "";
    protected String windowName = "Jarvis";

    public AbstractEditorAction(@NotNull Supplier<@NlsActions.ActionText String> dynamicText) {
        super(dynamicText);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        doActionPerformed(e);
    }

    public void doActionPerformed(@NotNull AnActionEvent e) {

        // Check the toolWindow is active
        ToolWindow jarvis = ToolWindowManager.getInstance(e.getProject()).getToolWindow(windowName);
        if (jarvis == null) {
            Notify("我暂时无法为您提供服务，您还没有激活贾维斯系统", MessageType.WARNING);
            return;
        }
        if (!jarvis.isActive()) {
            jarvis.activate(null);
        }

        Editor editor = e.getData(CommonDataKeys.EDITOR);
        assert editor != null;
        String selectedText = editor.getSelectionModel().getSelectedText();

        // Here is to adapt to the custom prefix
        String prefix = (String) e.getProject().getUserData(CustomAction.ACTIVE_PREFIX);
        if (StringUtil.isNotEmpty(prefix)) {
            key = prefix + ":";
            String customPrompt = (String) e.getProject().getUserData(CustomAction.ACTIVE_PROMPT);
            selectedText = StringUtil.isEmpty(customPrompt) ? selectedText : customPrompt;
            resetUserData(e);
        }


        String browserText = key + "\n" + selectedText;
        String apiText = key + "\n" + "<pre><code>" + selectedText + "</code></pre>";

        SendAction sendAction = e.getProject().getService(SendAction.class);
        Object mainPanel = e.getProject().getUserData(ACTIVE_CONTENT);

        if (mainPanel instanceof BrowserContent) {
            ((BrowserContent) mainPanel).execute(browserText);
            return;
        }

        String formattedText = apiText.replace("\n", "<br />");
        sendAction.doActionPerformed((MainPanel) mainPanel, formattedText);
    }

    public void resetUserData(@NotNull AnActionEvent e) {
        e.getProject().putUserData(CustomAction.ACTIVE_PREFIX, "");
        e.getProject().putUserData(CustomAction.ACTIVE_PROMPT, "");
        e.getProject().putUserData(CustomAction.ACTIVE_FILE_TYPE, NativeFileType.INSTANCE);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) {
            JarvisUtil.Notify("请先选中相应代码块", MessageType.WARNING);
            return;
        }
        boolean hasSelection = editor.getSelectionModel().hasSelection();
        e.getPresentation().setEnabledAndVisible(hasSelection);
    }
}
