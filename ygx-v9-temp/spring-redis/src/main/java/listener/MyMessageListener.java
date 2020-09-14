package listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class MyMessageListener implements MessageListener {
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String string = new String(message.getBody());
        System.out.println("接收到"+channel+"的消息："+string);
    }
}
