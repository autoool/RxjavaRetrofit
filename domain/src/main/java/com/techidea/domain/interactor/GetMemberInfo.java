package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepositoryDomain;

import rx.Observable;

/**
 * Created by zchao on 2016/6/13.
 */
public class GetMemberInfo extends RxBaseCase {

    private String qrcode;
    private String type;
    private final DataRepositoryDomain mDataRepositoryDomain;

    public GetMemberInfo(DataRepositoryDomain dataRepositoryDomain) {
        this.mDataRepositoryDomain = dataRepositoryDomain;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        qrcode = paras[0];
        type = paras[1];
        return this;
    }

    @Override
    public Observable buildCaseObservable() {
        return mDataRepositoryDomain.getMemberInfo(qrcode, type);
    }
}
