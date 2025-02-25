package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

import java.util.Arrays;

public class BaseTest implements OptionsFactory {

    @Override
    public Options getOptions() {
        return new Options().setLaunchOptions(
                new BrowserType.LaunchOptions()
                        .setArgs(Arrays.asList("--disable-extensions"))
                        ).setHeadless(false)
                        .setTestIdAttribute("data-test");
    }

    public static void openPage(Page page, String url) {
        page.navigate(url);
    }
}

