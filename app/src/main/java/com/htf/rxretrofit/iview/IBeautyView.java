package com.htf.rxretrofit.iview;

import com.htf.rxretrofit.bean.Beauty;

import java.util.ArrayList;

/**
 * 2016/7/21 11:26
 * Author: htf
 */
public interface IBeautyView extends IView {
    
    void onBeautySuccess(ArrayList<Beauty> beauties, boolean refresh);
}
