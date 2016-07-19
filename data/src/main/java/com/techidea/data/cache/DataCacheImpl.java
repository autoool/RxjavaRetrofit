package com.techidea.data.cache;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.techidea.data.cache.serializer.JsonSerializer;
import com.techidea.data.executor.JobExecutor;
import com.techidea.data.net.CacheException;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;
import com.techidea.domain.executor.ThreadExecutor;

import java.io.File;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zchao on 2016/7/6.
 */
public class DataCacheImpl implements DataCache {

    private static final String SETTING_FILE_NAME = "com.techidea.appclean.setting";
    private static final String DEFAULT_FILE_NAME = "yunpos_";
    public static final String FILE_USREINFOS = "userinfos";
    public static final String FILE_PRODUCTS = "products";
    public static final String FILE_CITYLIST = "citylist";
    public static final String FILE_PRODUCTCATEGORYS = "productcategorys";
    private static final String CACHE_UPDATE_USERINFOS = "cache_update_userinfos";
    private static final String CACHE_UPDATE_PRODUCTS = "cache_update_products";
    private static final String CACHE_UPDATE_PRODUCTCATEGRYS = "cache_update_productcategorys";
    private static final String CACHE_UPDATE_CITYLIST = "cache_update_citylist";

    private static final long EXPIPATION_TIME = 60 * 10 * 1000;

    private final Context mContext;
    private final File cacheDir;
    private final JsonSerializer<List<UserInfo>> userinfoSerializer;
    private final JsonSerializer<LoginUser> loginuserSerializer;
    private final JsonSerializer<List<Product>> productSerializer;
    private final JsonSerializer<List<ProductCategory>> productCategorySerializer;
    private final FileManager mFileManager;
    private final ThreadExecutor mThreadExecutor;

    public DataCacheImpl(Context context) {
        this.mContext = context.getApplicationContext();
        this.cacheDir = this.mContext.getCacheDir();
        this.mFileManager = new FileManager();
        this.mThreadExecutor = new JobExecutor();
        this.userinfoSerializer = new JsonSerializer<>();
        this.loginuserSerializer = new JsonSerializer<>();
        this.productSerializer = new JsonSerializer<>();
        this.productCategorySerializer = new JsonSerializer<>();
    }

    @Override
    public Observable<List<UserInfo>> getLoginUsers() {
        return Observable.create(new Observable.OnSubscribe<List<UserInfo>>() {
            @Override
            public void call(Subscriber<? super List<UserInfo>> subscriber) {
                File loginusers = DataCacheImpl.this.buildFile(FILE_USREINFOS);
                String fileContent = DataCacheImpl.this.mFileManager.readFileContent(loginusers);
                List<UserInfo> list = DataCacheImpl.this.userinfoSerializer.deserialize(fileContent, new TypeToken<List<UserInfo>>() {
                });
                if (list != null) {
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new CacheException("获取初始化用户失败"));
                }
            }
        });
    }

    @Override
    public void putLoginUsers(List<UserInfo> userInfoList) {
        if (userInfoList != null) {
            File loginusers = this.buildFile(FILE_USREINFOS);
            if (!isCached(FILE_USREINFOS)) {
                String jsonString = this.userinfoSerializer.serialize(userInfoList, new TypeToken<List<UserInfo>>() {
                });
                this.executeAsynchronously(new CacheWriter(this.mFileManager,
                        loginusers, jsonString));
            }
            setLastCacheUpdateTimeMillis(FILE_USREINFOS);
        }
    }

    @Override
    public Observable<List<Product>> getProducts() {
        return Observable.create(new Observable.OnSubscribe<List<Product>>() {
            @Override
            public void call(Subscriber<? super List<Product>> subscriber) {
                File products = DataCacheImpl.this.buildFile(FILE_PRODUCTS);
                String fileContent = DataCacheImpl.this.mFileManager.readFileContent(products);
                List<Product> list = DataCacheImpl.this.productSerializer.deserialize(fileContent, new TypeToken<List<Product>>() {
                });
                if (list != null) {
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new CacheException("获取商品数据失败"));
                }
            }
        });
    }

    @Override
    public void putProducts(List<Product> productList) {

    }

    @Override
    public Observable<List<ProductCategory>> getProductCategorys() {
        return null;
    }

    @Override
    public void putProductCategorys(List<ProductCategory> productCategoryList) {

    }

    @Override
    public Observable<LoginUser> getLoginUser() {
        return null;
    }

    @Override
    public void putLoginUser(LoginUser loginUser) {

    }

    @Override
    public boolean isCached(String filename) {
        File file = this.buildFile(filename);
        return this.mFileManager.exists(file);
    }

    @Override
    public boolean isExpired(String filename) {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis(filename);
        boolean expired = ((currentTime - lastUpdateTime) > EXPIPATION_TIME);
        if (expired) {
            this.evictAll();
        }
        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.mFileManager, this.cacheDir));
    }

    private File buildFile(String filename) {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(filename);
        return new File(fileNameBuilder.toString());
    }

    private void executeAsynchronously(Runnable runnable) {
        this.mThreadExecutor.execute(runnable);
    }

    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        public CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        public CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }

    private long getLastCacheUpdateTimeMillis(String filename) {
        if (filename.equals(FILE_USREINFOS)) {
            return this.mFileManager.getFromPreferences(this.mContext, SETTING_FILE_NAME, CACHE_UPDATE_USERINFOS);
        } else if (filename.equals(FILE_PRODUCTCATEGORYS)) {
            return this.mFileManager.getFromPreferences(this.mContext, SETTING_FILE_NAME, CACHE_UPDATE_PRODUCTCATEGRYS);
        } else if (filename.equals(FILE_PRODUCTS)) {
            return this.mFileManager.getFromPreferences(this.mContext, SETTING_FILE_NAME, CACHE_UPDATE_PRODUCTS);
        }
        return 0;
    }

    private void setLastCacheUpdateTimeMillis(String filename) {
        long currentMillis = System.currentTimeMillis();
        if (filename.equals(FILE_USREINFOS)) {
            this.mFileManager.writeToPreferences(this.mContext, SETTING_FILE_NAME, CACHE_UPDATE_USERINFOS, currentMillis);
        } else if (filename.equals(FILE_PRODUCTCATEGORYS)) {
            this.mFileManager.writeToPreferences(this.mContext, SETTING_FILE_NAME, CACHE_UPDATE_PRODUCTCATEGRYS, currentMillis);
        } else if (filename.equals(FILE_PRODUCTS)) {
            this.mFileManager.writeToPreferences(this.mContext, SETTING_FILE_NAME, CACHE_UPDATE_PRODUCTS, currentMillis);
        }
    }

}
