package com.techidea.domain.respository;

import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.MemberInfoItem;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/19.
 */
public interface DataRepositoryDomain {

    Observable<List<ProductCategory>> initProductCategory(String deviceId, String deviceType);

    Observable<List<Product>> initProduct(String deviceId, String deviceType);

    Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType);

    Observable<LoginUser> login(String deviceId, String username, String password);

    Observable<MemberInfoItem> getMemberInfo(String qrcode, String type);

    Observable<List<CityItem>> getCityList(String cityname);

    Observable<List<CityInfo>> getSearchCity(String citytype, String key);
}
