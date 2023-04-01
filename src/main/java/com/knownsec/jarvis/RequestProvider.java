package com.knownsec.jarvis;

import com.intellij.openapi.project.Project;
import com.knownsec.jarvis.core.TokenManager;
import com.knownsec.jarvis.core.builder.OfficialBuilder;
import com.knownsec.jarvis.settings.OpenAISettingsState;
import com.knownsec.jarvis.settings.SettingConfiguration;
import com.knownsec.jarvis.ui.MainPanel;

import java.util.Map;

/**
 * @author zouyx
 */
public class RequestProvider {

    public static final String OFFICIAL_CONVERSATION_URL = "https://bypass.duti.tech/api/conversation";
    private Project myProject;
    private String url;
    private String data;
    private Map<String, String> header;

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public RequestProvider create(MainPanel mainPanel, String question) {
        myProject = mainPanel.getProject();
        RequestProvider provider = new RequestProvider();

        OpenAISettingsState instance = OpenAISettingsState.getInstance();
        if (mainPanel.isChatGPTModel()) {
            if (instance.urlType == SettingConfiguration.SettingURLType.CUSTOMIZE) {
                provider.url = instance.customizeUrl;
            } else {
                provider.url = OFFICIAL_CONVERSATION_URL;
            }
            provider.header = TokenManager.getInstance().getChatGPTHeaders();
            provider.data = OfficialBuilder.buildChatGPT(myProject,question).toString();
        } else {
            provider.url = instance.gpt35TurboUrl;
            provider.header = TokenManager.getInstance().getGPT35TurboHeaders();
            if (instance.enableContext) {
                provider.data = OfficialBuilder.buildGpt35Turbo(question,mainPanel.getContentPanel()).toString();
            } else {
                provider.data = OfficialBuilder.buildGpt35Turbo(question).toString();
            }
        }
        return provider;
    }
}
