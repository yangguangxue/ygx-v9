import entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void stringTest() throws IllegalAccessException, InstantiationException {
        //每次都的确定操作类型
//        redisTemplate.opsForValue().set("mytarget","house");
//        System.out.println(redisTemplate.opsForValue().get("mytarget"));
        //默认对key和value都做序列化操作
//        Student student = new Student("zs",1000);
//        redisTemplate.opsForValue().set("student:2",student);
//        Object o = redisTemplate.opsForValue().get("student:2");
//        System.out.println(o);
        //叠加操作 value的序列化方式必须是字符串的方式
//        redisTemplate.setDefaultSerializer(JdkSerializationRedisSerializer.class.newInstance());
        redisTemplate.opsForValue().set("sifangqian","10");
        redisTemplate.opsForValue().increment("sifangqian3",10);
        System.out.println(redisTemplate.opsForValue().get("sifangqian3"));

    }

    @Test
    public void hashTest(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","101");
        map.put("zuth","py");
        redisTemplate.opsForHash().putAll("book:1",map);
    }


    @Test
    public void multiTest(){
        redisTemplate.execute(new SessionCallback() {
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForList().leftPush("user:1:fans","6");
                operations.opsForList().leftPush("user:6:fans","1");
//                List range = operations.opsForList().range("user:1:fans", 0, -1);
//                for (Object o : range) {
//                    System.out.println(o);
//                }
                operations.exec();
                return null;
            }
        });
    }


    @Test
    public void sendMessageTest(){
        redisTemplate.convertAndSend("nba","科比");
    }

}
