package com.htf.rxretrofit.imodel;

import com.htf.rxretrofit.bean.Beauties;

import rx.Observable;

/**
 * 2016/7/21 9:59
 * Author: htf
 */
public interface IBeautyModel extends IModel {

    Observable<Beauties> getBeauty(int size, int page);
}
