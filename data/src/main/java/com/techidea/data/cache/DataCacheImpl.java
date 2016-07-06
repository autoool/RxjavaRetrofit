package com.techidea.data.cache;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.techidea.data.cache.serializer.JsonSerializer;
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

    private static final String DEFAULT_FILE_NAME = "yunpos_";
    private static final String FILE_NAME_LOGINUSERS = "loginusers";
    private static final long EXPIPATION_TIME = 60 * 10 * 1000;

    private final Context mContext;
    private final File cacheDir;
    private final JsonSerializer<List<UserInfo>> userinfoSerializer;
    private final JsonSerializer<LoginUser> loginuserSerializer;
    private final JsonSerializer<List<Product>> productSerializer;
    private final JsonSerializer<List<ProductCategory>> productCategorySerializer;
    private final FileManager mFileManager;
    private final ThreadExecutor mThreadExecutor;

    public DataCacheImpl(Context context, FileManager fileManager, ThreadExecutor threadExecutor) {
        this.mContext = context.getApplicationContext();
        this.cacheDir = this.mContext.getCacheDir();
        this.mFileManager = fileManager;
        this.mThreadExecutor = threadExecutor;
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
                File loginusers = DataCacheImpl.this.buildFile(FILE_NAME_LOGINUSERS);
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
            File loginusers = this.buildFile(FILE_NAME_LOGINUSERS);
            if (!isCached(FILE_NAME_LOGINUSERS)) {
                String jsonString = this.userinfoSerializer.serialize(userInfoList, new TypeToken<List<UserInfo>>() {
                });
                this.executeAsynchronously(new CacheWriter(this.mFileManager,
                        loginusers, jsonString));
            }
        }
    }

    @Override
    public Observable<List<Product>> getProducts() {
        return null;
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
    public boolean isCached(String name) {
        File file = this.buildFile(name);
        return this.mFileManager.exists(file);
    }

    @Override
    public boolean isExpired() {
        return false;
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

}
