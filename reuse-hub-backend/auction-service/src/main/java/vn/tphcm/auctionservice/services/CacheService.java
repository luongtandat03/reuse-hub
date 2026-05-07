/*
 * @ (#) CacheService.java       1.0     1/26/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.services;

/*
 * @author: Luong Tan Dat
 * @date: 1/26/2026
 */

import vn.tphcm.auctionservice.dtos.response.AuctionResponse;

public interface CacheService {
    void cacheAuction(String auctionId, AuctionResponse auctionResponse);

    AuctionResponse getCachedAuction(String auctionId);

    void evictCachedAuction(String auctionId);
}
