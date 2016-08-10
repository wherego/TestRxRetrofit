package com.htf.rxretrofit.base;

import rx.subscriptions.CompositeSubscription;

/**
 * 2016/7/21 10:48
 * Author: htf
 */
public abstract class ConfigurationActivity extends BaseAppCompatActivity {
    
    public CompositeSubscription mCompositeSubscription;

    @Override
    protected void initData() {
        mCompositeSubscription = new CompositeSubscription();
    }
}
