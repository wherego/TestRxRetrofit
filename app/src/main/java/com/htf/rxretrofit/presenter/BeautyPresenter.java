package com.htf.rxretrofit.presenter;

import com.htf.retrofit.net.api.GankService;
import com.htf.rxretrofit.bean.Beauties;
import com.htf.rxretrofit.iview.IBeautyView;
import com.htf.rxretrofit.model.BeautyModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 2016/7/21 11:05
 * Author: htf
 */
public class BeautyPresenter extends BasePresenter<IBeautyView> {
    
    private int page;

    public BeautyPresenter() {
        page = 1;
    }

    public void getData(boolean refresh) {
        
        if(refresh) {
            page = 1;
        } else {
            page ++;
        }
        
        this.mCompositeSubscription.add(BeautyModel.getInstance()//
                .getBeauty(GankService.DEFAULT_DATA_SIZE, page)//
                .subscribeOn(Schedulers.io())//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Subscriber<Beauties>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().onFailure(e);
                    }

                    @Override
                    public void onNext(Beauties beauties) {
                        getMvpView().onBeautySuccess(beauties.results, refresh);
                    }
                }));
    }
}
