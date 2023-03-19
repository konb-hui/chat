package com.konb.chat.config;

import com.konb.chat.handler.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

/**
 * @author konb
 * @version 1.0
 * @date 2023/3/18 21:43
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/chat").setAllowedOrigins("*");;
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }
}
