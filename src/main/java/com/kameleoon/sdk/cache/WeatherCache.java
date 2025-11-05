package com.kameleoon.sdk.cache;


import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import com.kameleoon.sdk.model.WeatherResponse;


public final class WeatherCache {

    private static final int CACHE_MAX_SIZE = 10;
    private static final long CACHE_TTL_SECONDS = 60 * 10;

    private Map<String, CacheEntry> cache;

    public WeatherCache() {
        this.cache = new LinkedHashMap<>(16, .75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, CacheEntry> eldest) {
                return size() > CACHE_MAX_SIZE;
            }
        };
    }

    public void put(String cityName, WeatherResponse data) {
        Instant now = Instant.now();
        CacheEntry cacheEntry = new CacheEntry(data, now, now.plusSeconds(CACHE_TTL_SECONDS));

        this.cache.put(cityName, cacheEntry);
    }

    public WeatherResponse get(String cityName) {
        CacheEntry cacheEntry = this.cache.get(cityName);

        if (cacheEntry == null)
            return null;

        if (Instant.now().isAfter(cacheEntry.expirationTime())) {
            this.cache.remove(cityName);
            return null;
        }

        return cacheEntry.data();
    }

}
