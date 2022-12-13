package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.RequestResultDto;
import megatera.makaoGymbackEnd.exceptions.RequestFailed;
import megatera.makaoGymbackEnd.models.Request;
import megatera.makaoGymbackEnd.repositories.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public RequestResultDto create(Long senderId, Long receiverId, String type, String context, String senderName) {
        Request request = new Request(senderId, receiverId, context);
        request.setContext(type, senderName);
        request.toCreated();

        if(requestRepository.findByContext(context).isPresent()){
            throw new RequestFailed(context);
        }

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

    public Request delete(Long id) {
        Request request = requestRepository.getReferenceById(id);

        request.toDeleted();

        return request;
    }
}
