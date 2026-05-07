/*
 * @ (#) AuctionRepository.java       1.0     1/24/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.repositories;

/*
 * @author: Luong Tan Dat
 * @date: 1/24/2026
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tphcm.auctionservice.models.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {
}
