// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.knownsec.jarvis.settings;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.Configurable;
import com.intellij.ui.TitledSeparator;
import com.intellij.ui.components.BrowserLink;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.knownsec.jarvis.message.ChatGPTBundle;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author zouyx
 * TODO
 * jarvis-4
 * jarvis-4-0314
 * jarvis-4-32k
 * jarvis-4-32k-0314
 * text-davinci-003
 * text-davinci-002
 * code-davinci-002
 */
public class GPT3_35_TurboPanel implements Configurable, Disposable {
    private JPanel myMainPanel;
    private JPanel apiKeyTitledBorderBox;
    private JBTextField apiKeyField;
    private JComboBox<String> comboCombobox;
    private JPanel modelTitledBorderBox;
    private JCheckBox enableContextCheckBox;
    private JLabel contextLabel;
    private JCheckBox enableTokenConsumptionCheckBox;
    private JCheckBox enableStreamResponseCheckBox;
    private JLabel tokenLabel;
    private JPanel urlTitledBox;
    private JCheckBox enableCustomizeGpt35TurboUrlCheckBox;
    private JTextField customizeServerField;
    private JPanel customizeServerOptions;
    private final HashMap<String, String> modelMapping = new HashMap<>();

    public GPT3_35_TurboPanel() {
        init();
    }

    private void init() {
        apiKeyField.getEmptyText().setText("Your API Key, find it in: https://platform.openai.com/account/api-keys");
        ItemListener proxyTypeChangedListener = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                enableCustomizeServerOptions(true);
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                enableCustomizeServerOptions(false);
            }
        };
        enableCustomizeGpt35TurboUrlCheckBox.addItemListener(proxyTypeChangedListener);
        enableCustomizeServerOptions(false);
        modelMapping.put("jarvis-3.5-turbo-0301", "gpt-3.5-turbo-0301");
        modelMapping.put("jarvis-3.5-turbo", "gpt-3.5-turbo");
        initHelp();
    }

    private void enableCustomizeServerOptions(boolean enabled) {
        UIUtil.setEnabled(customizeServerOptions, enabled, true);
    }

    @Override
    public void reset() {
        OpenAISettingsState state = OpenAISettingsState.getInstance();
        apiKeyField.setText(state.apiKey);
        comboCombobox.setSelectedItem(state.gpt35Model);
        enableContextCheckBox.setSelected(state.enableContext);
        enableTokenConsumptionCheckBox.setSelected(state.enableTokenConsumption);
        enableStreamResponseCheckBox.setSelected(state.enableGPT35StreamResponse);
        enableCustomizeGpt35TurboUrlCheckBox.setSelected(state.enableCustomizeGpt35TurboUrl);
        customizeServerField.setText(state.gpt35TurboUrl);
    }

    @Override
    public @Nullable JComponent createComponent() {
        return myMainPanel;
    }

    @Override
    public boolean isModified() {
        OpenAISettingsState state = OpenAISettingsState.getInstance();

        return !state.apiKey.equals(apiKeyField.getText()) ||
                !state.gpt35Model.equals(modelMapping.get(comboCombobox.getSelectedItem().toString())) ||
                !state.enableContext == enableContextCheckBox.isSelected() ||
                !state.enableTokenConsumption == enableTokenConsumptionCheckBox.isSelected() ||
                !state.enableGPT35StreamResponse == enableStreamResponseCheckBox.isSelected() ||
                !state.enableCustomizeGpt35TurboUrl == enableCustomizeGpt35TurboUrlCheckBox.isSelected() ||
                !state.gpt35TurboUrl.equals(customizeServerField.getText());
    }

    @Override
    public void apply() {
        OpenAISettingsState state = OpenAISettingsState.getInstance();
        state.apiKey = apiKeyField.getText();
        state.gpt35Model = modelMapping.get(comboCombobox.getSelectedItem().toString());
        state.enableContext = enableContextCheckBox.isSelected();
        state.enableTokenConsumption = enableTokenConsumptionCheckBox.isSelected();
        state.enableGPT35StreamResponse = enableStreamResponseCheckBox.isSelected();
        state.enableCustomizeGpt35TurboUrl = enableCustomizeGpt35TurboUrlCheckBox.isSelected();
        state.gpt35TurboUrl = customizeServerField.getText();
    }

    @Override
    public void dispose() {
    }

    @Override
    public String getDisplayName() {
        return ChatGPTBundle.message("ui.setting.menu.text");
    }

    private void createUIComponents() {
        apiKeyTitledBorderBox = new JPanel(new BorderLayout());
        TitledSeparator tsUrl = new TitledSeparator("API Key Settings");
        apiKeyTitledBorderBox.add(tsUrl, BorderLayout.CENTER);

        modelTitledBorderBox = new JPanel(new BorderLayout());
        TitledSeparator mdUrl = new TitledSeparator("Other Settings");
        modelTitledBorderBox.add(mdUrl, BorderLayout.CENTER);

        urlTitledBox = new JPanel(new BorderLayout());
        TitledSeparator url = new TitledSeparator("Server Settings");
        urlTitledBox.add(url, BorderLayout.CENTER);
    }

    private void initHelp() {
        contextLabel.setFont(JBUI.Fonts.smallFont());
        contextLabel.setForeground(UIUtil.getContextHelpForeground());

        tokenLabel.setFont(JBUI.Fonts.smallFont());
        tokenLabel.setForeground(UIUtil.getContextHelpForeground());
    }
}
