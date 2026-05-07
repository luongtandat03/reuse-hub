/*
 * @ (#) BidMapper.java       1.0     1/27/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.mapper;

/*
 * @author: Luong Tan Dat
 * @date: 1/27/2026
 */

import org.mapstruct.Mapper;
import vn.tphcm.auctionservice.dtos.response.BidResponse;
import vn.tphcm.auctionservice.models.Bid;

@Mapper(componentModel = "spring")
public interface BidMapper {
    BidResponse toResponse(Bid bid);
}
