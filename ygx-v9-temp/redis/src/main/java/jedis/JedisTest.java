package jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    @Test
    public void stringTest(){
        Jedis jedis = new Jedis("192.168.13.129",6379);
        jedis.auth("ygx");
        jedis.set("target","redis");
        System.out.println(jedis.get("target"));
        jedis.mset("k1","v1","k2","v2");
        List<String> values = jedis.mget("k1", "k2");
        for (String value : values) {
            System.out.println(value);
        }
    }

    @Test
    public void hashTest(){
        Jedis jedis = new Jedis("192.168.13.129",6379);
        jedis.auth("ygx");
        Map<String, String> map = jedis.hgetAll("book:1");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+"----------"+entry.getValue());
        }
    }
}
