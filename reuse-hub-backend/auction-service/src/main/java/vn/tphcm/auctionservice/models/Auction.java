/*
 * @ (#) Auction.java       1.0     1/22/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.models;
/*
 * @author: Luong Tan Dat
 * @date: 1/22/2026
 */

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import vn.tphcm.auctionservice.commons.AuctionStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_auctions")
@Builder
public class Auction extends AbstractEntity<String>{
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "seller_id")
    private String sellerId;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Builder.Default
    private AuctionStatus status = AuctionStatus.WAITING;

    @Column(name = "start_price")
    private Long startPrice;

    @Column(name = "current_price")
    private Long currentPrice;

    @Column(name = "step_price")
    private Long stepPrice;

    @Column(name = "winner_id")
    private String winnerId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Version
    private Long version;

    @OneToMany(mappedBy = "auction", cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Bid> bids;
}
