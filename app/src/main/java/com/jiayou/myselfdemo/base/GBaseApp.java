package com.jiayou.myselfdemo.base;

import com.jiayou.myselfdemo.R;
import com.myself.library.base.BaseApp;
import com.myself.library.base.TitleBarConfig;

/**
 * 使用
 */

public class GBaseApp extends BaseApp {
    @Override
    public void initOthers() {
        TitleBarConfig.titleBarResID = R.layout.titlebar;
    }

    @Override
    public void initTitleBar() {
        TitleBarConfig.isUseTitleBar = true;
    }

    @Override
    public boolean isDebugMode() {
        return false;
    }
}
