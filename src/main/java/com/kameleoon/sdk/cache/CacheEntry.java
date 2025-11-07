package com.kameleoon.sdk.cache;


import java.time.Instant;

import com.kameleoon.sdk.model.WeatherResponse;


final record CacheEntry(WeatherResponse data, Instant creationTime, Instant expirationTime) {
}
