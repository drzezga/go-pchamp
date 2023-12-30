package tp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpSessionScope;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import tp.messages.MessageController;

@Configuration
@EnableWebSocket
public class WebsocketConfiguration implements WebSocketConfigurer {
    private final MessageController controller;

    @Autowired
    public WebsocketConfiguration(MessageController controller) {
        this.controller = controller;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(controller, "/ws");
    }

    // I give up for now
    // TODO: Finish this
    // https://github.com/spring-projects/spring-framework/blob/main/spring-websocket/src/main/java/org/springframework/web/socket/config/MessageBrokerBeanDefinitionParser.java#L181
    // https://github.com/spring-projects/spring-framework/blob/main/spring-beans/src/main/java/org/springframework/beans/factory/config/CustomScopeConfigurer.java#L47
    // https://github.com/spring-projects/spring-framework/blob/main/spring-websocket/src/main/java/org/springframework/web/socket/config/annotation/WebSocketMessageBrokerConfigurationSupport.java#L127
    // https://github.com/spring-projects/spring-framework/blob/main/spring-messaging/src/main/java/org/springframework/messaging/simp/SimpSessionScope.java#L33

    @Bean
    public static CustomScopeConfigurer webSocketScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("websocket", new SimpSessionScope());
        return configurer;
    }

}
