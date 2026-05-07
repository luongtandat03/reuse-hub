/*
 * @ (#) WebSocketConfig.java       1.0     1/24/2026
 *
 * Copyright (c) 2026. All rights reserved.
 */

package vn.tphcm.auctionservice.configs;
/*
 * @author: Luong Tan Dat
 * @date: 1/24/2026
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j(topic = "WEB-SOCKET-CONFIG")
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final WebSocketAuthInterceptor webSocketAuthInterceptor;

     @Value("${websocket.allow-origin}")
    private String allowedOrigins;

    @Value("${websocket.endpoint}")
    private String endpoint;

    @Value("${websocket.destination-prefix}")
    private String destinationPrefix;

    @Value("${websocket.application-prefix}")
    private String applicationPrefix;

    @Value("${websocket.user-destination-prefix}")
    private String userDestinationPrefix;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(destinationPrefix.split(","));

        registry.setApplicationDestinationPrefixes(applicationPrefix);

        registry.setUserDestinationPrefix(userDestinationPrefix);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("Registering STOMP endpoint at: {}", endpoint);

        registry.addEndpoint("/ws-native")
                        .setAllowedOriginPatterns(allowedOrigins);

        registry.addEndpoint(endpoint)
                .setAllowedOriginPatterns(allowedOrigins)
                .withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketAuthInterceptor);
        log.info("Configured client inbound channel with WebSocketAuthInterceptor");
    }
}
