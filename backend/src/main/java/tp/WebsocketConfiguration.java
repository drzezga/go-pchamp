package tp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import tp.game.WebsocketPlayer;
import tp.messages.MessageController;

@Configuration
@EnableWebSocket
public class WebsocketConfiguration implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(defaultMessageController(), "/ws");
    }

    @Bean
    public WebSocketHandler defaultMessageController() {
        return new MessageController();
    }
}
