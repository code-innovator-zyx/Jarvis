
package com.knownsec.jarvis.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.obiscr.OpenAIProxy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.knownsec.jarvis.MyToolWindowFactory.*;

/**
 * @author zouyx
 * Supports storing the application settings in a persistent way.
 * The {@link State} and {@link Storage} annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
        name = "com.knownsec.jarvis.settings.OpenAISettingsState",
        storages = @Storage("ChatGPTSettingsPlugin.xml")
)
public class OpenAISettingsState implements PersistentStateComponent<OpenAISettingsState> {

    public String customizeUrl = "";
    public SettingConfiguration.SettingURLType urlType =
            SettingConfiguration.SettingURLType.OFFICIAL;
    public String readTimeout = "50000";
    public String connectionTimeout = "50000";

    public String email = "1003941268@qq.com";
    public String password = "";
    public Boolean enableProxy = true;
    public Boolean enableAvatar = true;
    public SettingConfiguration.SettingProxyType proxyType =
            SettingConfiguration.SettingProxyType.SOCKS;

    public String proxyHostname = "127.0.0.1";
    public String proxyPort = "7890";

    public String accessToken = "";
    public String expireTime = "";
    public String imageUrl = "https://cdn.auth0.com/avatars/me.png";
    public String apiKey = "";
    public Map<Integer, String> contentOrder = new HashMap<>() {{
        put(1, GPT35_TRUBO_CONTENT_NAME);
        put(2, CHATGPT_CONTENT_NAME);
        put(3, ONLINE_CHATGPT_CONTENT_NAME);
    }};

    public Boolean enableLineWarp = true;

    public List<String> customActionsPrefix = new ArrayList<>();

    public String chatGptModel = "text-davinci-002-render-sha";
    public String gpt35Model = "gpt-3.5-turbo";
    public Boolean enableContext = false;
    public String assistantApiKey = "";
    public Boolean enableTokenConsumption = false;
    public Boolean enableGPT35StreamResponse = false;
    public String gpt35TurboUrl = "https://api.openai.com/v1/chat/completions";

    public Boolean enableProxyAuth = false;
    public String proxyUsername = "";
    public String proxyPassword = "";

    public static OpenAISettingsState getInstance() {
        return ApplicationManager.getApplication().getService(OpenAISettingsState.class);
    }

    @Nullable
    @Override
    public OpenAISettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull OpenAISettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public void reload() {
        loadState(this);
    }

    public Proxy getProxy() {
        Proxy proxy = null;
        if (enableProxy) {
            Proxy.Type type = proxyType ==
                    SettingConfiguration.SettingProxyType.HTTP ? Proxy.Type.HTTP :
                    proxyType == SettingConfiguration.SettingProxyType.SOCKS ? Proxy.Type.SOCKS :
                            Proxy.Type.DIRECT;
            proxy = new OpenAIProxy(proxyHostname, Integer.parseInt(proxyPort),
                    type).build();
        }
        return proxy;
    }
}
