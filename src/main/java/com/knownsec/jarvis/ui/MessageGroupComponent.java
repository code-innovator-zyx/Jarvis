package com.knownsec.jarvis.ui;

import com.google.gson.JsonArray;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.NullableComponent;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.labels.LinkLabel;
import com.intellij.ui.components.panels.NonOpaquePanel;
import com.intellij.ui.components.panels.VerticalLayout;
import com.intellij.util.ui.JBFont;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.knownsec.jarvis.core.Constant;
import com.knownsec.jarvis.core.ConversationManager;
import com.knownsec.jarvis.settings.OpenAISettingsState;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MessageGroupComponent extends JBPanel<MessageGroupComponent> implements NullableComponent {
    private final JPanel myList = new JPanel(new VerticalLayout(JBUI.scale(10)));
    private final MyScrollPane myScrollPane = new MyScrollPane(myList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    private int myScrollValue = 0;

    private final MyAdjustmentListener scrollListener = new MyAdjustmentListener();
    private final MessageComponent chatGPTExplanation =
            new MessageComponent(Constant.getChatGPTContent(), false);
    private final MessageComponent gpt35TurboModelExplanation =
            new MessageComponent(Constant.getGpt35TurboContent(), false);
    private final MessageComponent tips =
            new MessageComponent("很高兴为您服务.", false);
    private final MessageComponent mustRead =
            new MessageComponent("顺便问一下，隔壁小朋友都训练到五代了，我啥时候才能跟上大部队？", false);
    private JBTextField systemRole;
    private static final String systemRoleText = "你想让我扮演一个什么样的角色？我无所不能......🐂";
    private JsonArray messages = new JsonArray();

    public MessageGroupComponent(@NotNull Project project, boolean isChatGPT) {
        setBorder(JBUI.Borders.empty(10, 10, 10, 0));
        setLayout(new BorderLayout(JBUI.scale(7), 0));
        setBackground(UIUtil.getListBackground());

        JPanel mainPanel = new JPanel(new BorderLayout(0, JBUI.scale(8)));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(JBUI.Borders.emptyLeft(8));

        if (!isChatGPT) {
            JPanel panel = new NonOpaquePanel(new GridLayout(2, 1));
            panel.add(new JBLabel(" 玩角色扮演么😯: 我可以成为任何你想让我成为的角色"));
            JPanel rolePanel = new NonOpaquePanel(new BorderLayout());
            systemRole = new JBTextField();
            OpenAISettingsState instance = OpenAISettingsState.getInstance();
            systemRole.setText(instance.gpt35RoleText);
            systemRole.setEnabled(false);
            systemRole.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    systemRole.setEnabled(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    systemRole.setEnabled(false);
                }
            });
            rolePanel.add(systemRole, BorderLayout.CENTER);
            DefaultActionGroup toolbarActions = new DefaultActionGroup();
            toolbarActions.add(new AnAction(AllIcons.Actions.MenuSaveall) {
                @Override
                public void actionPerformed(@NotNull AnActionEvent e) {
                    instance.gpt35RoleText = systemRole.getText().isEmpty() ? systemRoleText : systemRole.getText();
                }
            });
            toolbarActions.add(new AnAction(AllIcons.Actions.Rollback) {
                @Override
                public void actionPerformed(@NotNull AnActionEvent e) {
                    systemRole.setText(systemRoleText);
                    instance.gpt35RoleText = systemRoleText;
                }
            });
            ActionToolbarImpl actonPanel = new ActionToolbarImpl("System Role Toolbar", toolbarActions, true);
            actonPanel.setTargetComponent(this);
            rolePanel.add(actonPanel, BorderLayout.EAST);
            panel.add(rolePanel);
            panel.setBorder(JBUI.Borders.empty(0, 8, 10, 10));
            add(panel, BorderLayout.NORTH);
        }

        add(mainPanel, BorderLayout.CENTER);

        JBLabel myTitle = new JBLabel("Conversation");
        myTitle.setForeground(JBColor.namedColor("Label.infoForeground", new JBColor(Gray.x80, Gray.x8C)));
        myTitle.setFont(JBFont.label());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(JBUI.Borders.empty(0, 10, 10, 0));

        panel.add(myTitle, BorderLayout.WEST);

        LinkLabel<String> newChat = new LinkLabel<>("Clear", null);
        newChat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                myList.removeAll();
                myList.add(tips);
                myList.add(mustRead);
                myList.updateUI();
                if (isChatGPT) {
                    ConversationManager.getInstance(project).setConversationId(null);
                } else {
                    messages = new JsonArray();
                }
            }
        });

        newChat.setFont(JBFont.label());
        newChat.setBorder(JBUI.Borders.emptyRight(20));
        panel.add(newChat, BorderLayout.EAST);
        mainPanel.add(panel, BorderLayout.NORTH);

        myList.setOpaque(true);
        myList.setBackground(UIUtil.getListBackground());
        myList.setBorder(JBUI.Borders.emptyRight(10));

        myScrollPane.setBorder(JBUI.Borders.empty());
        mainPanel.add(myScrollPane);
        myScrollPane.getVerticalScrollBar().setAutoscrolls(true);
        myScrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            int value = e.getValue();
            if (myScrollValue == 0 && value > 0 || myScrollValue > 0 && value == 0) {
                myScrollValue = value;
                repaint();
            } else {
                myScrollValue = value;
            }
        });

        // Add the default message
        if (isChatGPT) {
            add(chatGPTExplanation);
        } else {
            add(gpt35TurboModelExplanation);
        }
        add(tips);
        add(mustRead);
    }

    public void add(MessageComponent messageComponent) {
        // The component should be immediately added to the
        // container and displayed in the UI

        // SwingUtilities.invokeLater(() -> {
        myList.add(messageComponent);
        updateLayout();
        scrollToBottom();
        updateUI();
        // });
    }

    public void scrollToBottom() {
        JScrollBar verticalScrollBar = myScrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    }

    public void updateLayout() {
        LayoutManager layout = myList.getLayout();
        int componentCount = myList.getComponentCount();
        for (int i = 0; i < componentCount; i++) {
            layout.removeLayoutComponent(myList.getComponent(i));
            layout.addLayoutComponent(null, myList.getComponent(i));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (myScrollValue > 0) {
            g.setColor(JBColor.border());
            int y = myScrollPane.getY() - 1;
            g.drawLine(0, y, getWidth(), y);
        }
    }


    @Override
    public boolean isVisible() {
        if (super.isVisible()) {
            int count = myList.getComponentCount();
            for (int i = 0; i < count; i++) {
                if (myList.getComponent(i).isVisible()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isNull() {
        return !isVisible();
    }

    static class MyAdjustmentListener implements AdjustmentListener {

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar source = (JScrollBar) e.getSource();
            if (!source.getValueIsAdjusting()) {
                source.setValue(source.getMaximum());
            }
        }
    }

    public void addScrollListener() {
        myScrollPane.getVerticalScrollBar().
                addAdjustmentListener(scrollListener);
    }

    public void removeScrollListener() {
        myScrollPane.getVerticalScrollBar().
                removeAdjustmentListener(scrollListener);
    }

    public JsonArray getMessages() {
        return messages;
    }

    public String getSystemRole() {
        if (systemRole.getText().isEmpty()) {
            return systemRoleText;
        }
        return systemRole.getText();
    }
}
