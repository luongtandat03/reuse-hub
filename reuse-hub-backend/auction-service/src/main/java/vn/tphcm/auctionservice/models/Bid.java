/*
 * @ (#) Bids.java       1.0     1/24/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.models;
/*
 * @author: Luong Tan Dat
 * @date: 1/24/2026
 */

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_bid")
@Builder
public class Bid extends AbstractEntity<String> implements Serializable {
    @Column(name = "bidder_id")
    private String bidderId;

    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "auction_id")
    private Auction auction;
}
