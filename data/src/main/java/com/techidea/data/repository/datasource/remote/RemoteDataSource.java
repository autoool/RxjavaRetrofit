package com.techidea.data.repository.datasource.remote;

import com.techidea.data.net.HttpMethods;
import com.techidea.data.repository.datasource.DataStore;
import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by zchao on 2016/5/19.
 * 服务端数据
 */
public class RemoteDataSource implements DataStore {

    public RemoteDataSource() {
    }

    @Override
    public Observable<List<CityItem>> getCityList(String cityname) {
        return null;
    }

    @Override
    public Observable<List<CityInfo>> getSearchCityInfo(String citytype, String key) {
        return null;
    }

    @Override
    public Observable<String> uploadFileWidthParam(String key, File file) {
        Map<String, RequestBody> requestMap = new HashMap<>();
        requestMap.put("appKey",createPartFormString(key));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part partFile = MultipartBody.Part.createFormData("uploadfile",file.getName(),requestFile);
        return HttpMethods.getInstance().uploadFileWithPartMap(requestMap,partFile);
    }

    private RequestBody createPartFormString(String param) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), param);
    }
}
