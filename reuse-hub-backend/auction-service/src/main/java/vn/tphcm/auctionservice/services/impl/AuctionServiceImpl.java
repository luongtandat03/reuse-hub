/*
 * @ (#) AuctionServiceImpl.java       1.0     1/24/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.services.impl;
/*
 * @author: Luong Tan Dat
 * @date: 1/24/2026
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.tphcm.auctionservice.dtos.ApiResponse;
import vn.tphcm.auctionservice.dtos.request.BidRequest;
import vn.tphcm.auctionservice.dtos.response.AuctionResponse;
import vn.tphcm.auctionservice.repositories.AuctionRepository;
import vn.tphcm.auctionservice.repositories.BidRepository;
import vn.tphcm.auctionservice.services.AuctionService;
import vn.tphcm.auctionservice.services.CacheService;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "AUCTION-SERVICE")
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    private final CacheService cacheService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public ApiResponse<AuctionResponse> getAllAuction() {


        return null;
    }

    @Override
    @Transactional
    public ApiResponse<Void> placeBid(BidRequest request) {
        return null;
    }

    @Override
    public void processClosedAuction() {

    }
}
