package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.RequestResultDto;
import megatera.makaoGymbackEnd.models.Request;
import megatera.makaoGymbackEnd.repositories.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RequestServiceTest {
    private RequestService requestService;

    private RequestRepository requestRepository;

    @BeforeEach
    void setup() {
        requestRepository = mock(RequestRepository.class);
        requestService = new RequestService(requestRepository);
    }

    @Test
    void create() {
        Long senderId = 1L;
        Long receiverId = 1L;
        String type = "requestPt";
        String context = "2022-12-09T11:00";
        String senderName = "오진욱";

        RequestResultDto requestResultDto = requestService.create(senderId, receiverId, type, context, senderName);

        assertThat(requestResultDto.getContext()).isEqualTo(context);
        assertThat(requestResultDto.getStatus()).isEqualTo("CREATED");
    }

    @Test
    void findByReceiverId() {
        Long receiverId = 1L;

        given(requestRepository.findAllByReceiverId(receiverId)).willReturn(List.of(
                Request.fake("2022-12-09T11:00"),
                Request.fake("2022-12-09T12:00"),
                Request.fake("2022-12-09T13:00"),
                Request.fake("2022-12-09T14:00"),
                Request.fake("2022-12-09T15:00")
        ));
        List<RequestResultDto> requestResultDtos = requestService.findByReceiverId(receiverId);

        assertThat(requestResultDtos).hasSize(5);
    }

    @Test
    void makeChecked() {
        Long trainerId = 1L;

        given(requestRepository.findAllByReceiverId(trainerId)).willReturn(List.of(
                Request.fake("2022-12-09T11:00"),
                Request.fake("2022-12-09T12:00"),
                Request.fake("2022-12-09T13:00"),
                Request.fake("2022-12-09T14:00"),
                Request.fake("2022-12-09T15:00")
        ));

        List<Request> requests = requestService.makeChecked(trainerId);

        assertThat(requests).hasSize(5);
        assertThat(requests.get(0).status()).isEqualTo("CHECKED");
        assertThat(requests.get(1).status()).isEqualTo("CHECKED");
        assertThat(requests.get(2).status()).isEqualTo("CHECKED");
        assertThat(requests.get(3).status()).isEqualTo("CHECKED");
        assertThat(requests.get(4).status()).isEqualTo("CHECKED");
    }

    @Test
    void delete() {
        Long id= 1L;

        given(requestRepository.getReferenceById(id)).willReturn(Request.fake("2022-12-09T11:00"));

        Request request = requestService.delete(id);

        assertThat(request.status()).isEqualTo("DELETED");
    }
}
