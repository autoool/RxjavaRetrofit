package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepositoryDomain;

import java.io.File;

import rx.Observable;

/**
 * Created by zchao on 2017/4/13.
 */

public class UploadFileWithPartMap {

    private String appKey;
    private File file;
    private DataRepositoryDomain dataRepositoryDomain;

    public UploadFileWithPartMap(DataRepositoryDomain dataRepositoryDomain) {
        this.dataRepositoryDomain = dataRepositoryDomain;
    }

    public UploadFileWithPartMap initParams(File file, String... paras) {
        this.appKey = paras[0];
        this.file = file;
        return this;
    }

    public Observable<String> execute() {
        return dataRepositoryDomain.uploadFileWidthParam(appKey, file);
    }
}
