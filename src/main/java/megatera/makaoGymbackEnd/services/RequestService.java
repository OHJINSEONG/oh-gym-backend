package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.RequestResultDto;
import megatera.makaoGymbackEnd.models.Request;
import megatera.makaoGymbackEnd.repositories.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public RequestResultDto create(Long senderId, Long receiverId, Long lectureId, String dateTime, String senderName) {
        LocalDateTime requestDateTime = LocalDateTime.parse(dateTime);

        Request request = new Request(senderId, receiverId, requestDateTime, lectureId);

        request.setContext(senderName);

        request.toCreated();

        requestRepository.save(request);

        return request.toDto();
    }

    public List<RequestResultDto> findByReceiverId(Long receiverId) {
        List<Request> requests = requestRepository.findAllByReceiverId(receiverId);

        return requests.stream().map(Request::toDto).toList();
    }

    public List<Request> makeChecked(Long trainerId) {
        List<Request> requests = requestRepository.findAllByReceiverId(trainerId).stream()
                .filter(request -> !request.status().equals("DELETED")).toList();
        requests.forEach(Request::toChecked);

        return requests;
    }

    public void delete(Long id) {
        requestRepository.deleteById(id);
    }
}
