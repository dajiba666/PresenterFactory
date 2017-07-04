package myapp.aop_lib;

import android.util.LruCache;

/**
 * Created by yang2 on 2017/7/4.
 */
//创建presenter对象缓存管理类
public class MemoryCacheManager {

    private static MemoryCacheManager mMemoryCacheManager = null;

    private final static int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;

    private final static LruCache<String, Object> mMemoryCache = new LruCache<>(cacheSize);

    public static synchronized MemoryCacheManager getInstance() {
        if (mMemoryCacheManager == null) {
            mMemoryCacheManager = new MemoryCacheManager();
        }
        return mMemoryCacheManager;
    }


    private MemoryCacheManager() {
    }

    public void put(String key, Object value) {
        mMemoryCache.put(key, value);
    }

    public Object get(String key) {
        return mMemoryCache.get(key);
    }


}
