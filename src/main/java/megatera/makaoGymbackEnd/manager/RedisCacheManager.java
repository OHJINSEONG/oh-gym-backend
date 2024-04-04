package megatera.makaoGymbackEnd.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheManager {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void updateCache(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
