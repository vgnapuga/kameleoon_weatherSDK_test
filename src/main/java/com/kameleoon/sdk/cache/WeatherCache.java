package com.kameleoon.sdk.cache;


import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.kameleoon.sdk.model.WeatherResponse;


public class WeatherCache {

    private static final int CACHE_DEFAULT_SIZE = 10;
    private static final long CACHE_DEFAULT_TTL_SECONDS = 10 * 60;

    private final int cacheMaxSize;
    private final long cacheTtlSeconds;

    private Map<String, CacheEntry> cache;

    private WeatherCache(int cacheMaxSize, long cacheTtlSeconds) {
        this.cacheMaxSize = cacheMaxSize;
        this.cacheTtlSeconds = cacheTtlSeconds;
        this.cache = new LinkedHashMap<>(16, .75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, CacheEntry> eldest) {
                return size() > cacheMaxSize;
            }
        };
    }

    public static WeatherCache of(int cacheMaxSize, long cacheTtlSeconds) {
        if (cacheMaxSize < CACHE_DEFAULT_SIZE)
            throw new IllegalArgumentException(
                    String.format("Invalid cache size (current=%d, min=%d)", cacheMaxSize, CACHE_DEFAULT_SIZE));
        if (cacheTtlSeconds < CACHE_DEFAULT_TTL_SECONDS)
            throw new IllegalArgumentException(
                    String.format(
                            "Invalid cache TTL seconds (current=%d, min=%d)",
                            cacheTtlSeconds,
                            CACHE_DEFAULT_TTL_SECONDS));

        return new WeatherCache(cacheMaxSize, cacheTtlSeconds);
    }

    public static WeatherCache defaultCache() {
        return new WeatherCache(CACHE_DEFAULT_SIZE, CACHE_DEFAULT_TTL_SECONDS);
    }

    public final void put(String cityName, WeatherResponse data) {
        Objects.requireNonNull(cityName);
        Objects.requireNonNull(data);

        Instant now = Instant.now();
        this.cache.put(cityName, new CacheEntry(data, now, now.plusSeconds(this.cacheTtlSeconds)));
    }

    public final CacheEntry get(String cityName) {
        return this.cache.get(Objects.requireNonNull(cityName));
    }

    public final int getCacheMaxSize() {
        return this.cacheMaxSize;
    }

    public final long getCacheTtlSeconds() {
        return this.cacheTtlSeconds;
    }

}
