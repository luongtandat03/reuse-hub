/*
 * @ (#) CacheServiceImpl.java       1.0     1/26/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.services.impl;
/*
 * @author: Luong Tan Dat
 * @date: 1/26/2026
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.tphcm.auctionservice.dtos.response.AuctionResponse;
import vn.tphcm.auctionservice.services.CacheService;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "CACHE-SERVICE")
public class CacheServiceImpl implements CacheService {
    private final RedisTemplate<String, Object> template;
    private final ObjectMapper objectMapper;

    private static final String AUCTION_KEY_PREFIX = "auction:";
    private static final Duration DEFAULT_TTL = Duration.ofMinutes(30);

    @Override
    public void cacheAuction(String auctionId, AuctionResponse auctionResponse) {
        try {
            String key = AUCTION_KEY_PREFIX + auctionId;
            template.opsForValue().set(key, auctionResponse, DEFAULT_TTL);
            log.info("Cache Auction Response: {}", auctionResponse);
        }catch (Exception e){
            log.error("Failed to cache item {} : {}", auctionId, e.getMessage());
        }
    }

    @Override
    public AuctionResponse getCachedAuction(String auctionId) {
        try {
            String key = AUCTION_KEY_PREFIX + auctionId;
            Object cachedData = template.opsForValue().get(key);
            if (cachedData != null) {
                log.info("Cache hit for auction {} with key {}", auctionId, key);
                return objectMapper.convertValue(cachedData, AuctionResponse.class);
            }
        } catch (Exception e) {
            log.error("Failed to get cached auction {}: {}", auctionId, e.getMessage());
        }
        return null;
    }

    @Override
    public void evictCachedAuction(String auctionId) {
        try {
            String key = AUCTION_KEY_PREFIX + auctionId;
            template.delete(key);
            log.info("Evicted cached auction {} with key {}", auctionId, key);
        } catch (Exception e) {
            log.error("Failed to evict cached auction {}: {}", auctionId, e.getMessage());
        }
    }
}
