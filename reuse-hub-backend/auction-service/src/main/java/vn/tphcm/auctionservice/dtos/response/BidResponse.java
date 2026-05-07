/*
 * @ (#) BidResponse.java       1.0     1/27/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.dtos.response;
/*
 * @author: Luong Tan Dat
 * @date: 1/27/2026
 */

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidResponse {
    private String id;

    private String bidderId;

    private Long amount;
}
