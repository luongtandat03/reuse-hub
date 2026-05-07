/*
 * @ (#) AuctionService.java       1.0     1/24/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.services;

/*
 * @author: Luong Tan Dat
 * @date: 1/24/2026
 */

import vn.tphcm.auctionservice.dtos.ApiResponse;
import vn.tphcm.auctionservice.dtos.request.BidRequest;
import vn.tphcm.auctionservice.dtos.response.AuctionResponse;

public interface AuctionService {
    ApiResponse<AuctionResponse> getAllAuction();

    ApiResponse<Void> placeBid(BidRequest request);

    void processClosedAuction()
}
