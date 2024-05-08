package megatera.makaoGymbackEnd.repositories;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface SseEmitterRepository {
    SseEmitter save(String emitterId, SseEmitter sseEmitter);
    void deleteById(String id);
    Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String memberId);
    void saveEventCache(String eventCacheId, Object event);
    Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId);
    void deleteAllEmitterStartWithId(String memberId);
    void deleteAllEventCacheStartWithId(String memberId);
}
