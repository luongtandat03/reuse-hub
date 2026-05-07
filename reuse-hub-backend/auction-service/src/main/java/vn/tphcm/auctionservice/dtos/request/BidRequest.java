/*
 * @ (#) BidRequest.java       1.0     1/25/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.dtos.request;
/*
 * @author: Luong Tan Dat
 * @date: 1/25/2026
 */

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BidRequest {
    @NotBlank
    private String auctionId;

    @NotBlank
    private String userId;

    @NotBlank
    private Long amount;
}
