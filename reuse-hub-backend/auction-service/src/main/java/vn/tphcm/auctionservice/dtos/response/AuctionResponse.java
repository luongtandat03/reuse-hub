/*
 * @ (#) AuctionResponse.java       1.0     1/25/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.dtos.response;
/*
 * @author: Luong Tan Dat
 * @date: 1/25/2026
 */

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionResponse {
    private String id;

    private String itemId;

    private String sellerId;

    private Long startPrice;

    private Long stepPrice;

    private String winnerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private List<BidResponse> bids;
}
