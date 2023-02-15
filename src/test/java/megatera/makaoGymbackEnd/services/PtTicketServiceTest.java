package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.PtTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.PtTicketDto;
import megatera.makaoGymbackEnd.models.*;
import megatera.makaoGymbackEnd.repositories.ProductRepository;
import megatera.makaoGymbackEnd.repositories.PtTicketRepository;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PtTicketServiceTest {
    private PtTicketService ptTicketService;
    private PtTicketRepository ptTicketRepository;
    private ProductRepository productRepository;
    private TrainerRepository trainerRepository;

    @BeforeEach
    void setup() {
        ptTicketRepository = mock(PtTicketRepository.class);
        productRepository = mock(ProductRepository.class);
        trainerRepository = mock(TrainerRepository.class);
        ptTicketService = new PtTicketService(ptTicketRepository, productRepository, trainerRepository);
    }

    @Test
    void create() {
        Long userId = 1L;
        Long trainerId = 1L;
        Long orderId = 1L;
        Long productId = 1L;
        Long dateOfUse = 90L;
        Long ptTimes = 12L;

        PtTicketDto ptTicketDto = ptTicketService.create(userId, trainerId, orderId, dateOfUse, ptTimes, productId);

        assertThat(ptTicketDto.getPeriodOfUse()).isEqualTo(90L);
    }

    @Test
    void list() {
        Long userId = 1L;

        given(ptTicketRepository.findAllByUserId(userId)).willReturn(List.of(
                PtTicket.fake(1L)
        ));

        given(productRepository.findAll()).willReturn(List.of(
                Product.fake(new Title("회원권"))
        ));

        given(trainerRepository.findAll()).willReturn(List.of(
                Trainer.fake(new UserName("오진성"))
        ));

        List<PtTicketDetailDto> ptTicketDetailDtos = ptTicketService.list(userId);

        assertThat(ptTicketDetailDtos.get(0).getPeriodOfUse()).isEqualTo(90L);
        assertThat(ptTicketDetailDtos.get(0).getTrainerUserName()).isEqualTo("오진성");
        assertThat(ptTicketDetailDtos.get(0).getType()).isEqualTo("PT");
    }

    @Test
    void use() {
        Long ticketId = 1L;
        String startDate = "2022-12-10";

        given(ptTicketRepository.getReferenceById(ticketId)).willReturn(
                PtTicket.fake(1L)
        );

        given(productRepository.findAll()).willReturn(List.of(
                Product.fake(new Title("회원권"))
        ));

        given(trainerRepository.findAll()).willReturn(List.of(
                Trainer.fake(new UserName("오진성"))
        ));

        PtTicketDetailDto ptTicketDetailDto = ptTicketService.use(ticketId, startDate);

        assertThat(ptTicketDetailDto.getStartDate()).isEqualTo("2022-12-10");
        assertThat(ptTicketDetailDto.getTrainerUserName()).isEqualTo("오진성");
        assertThat(ptTicketDetailDto.getType()).isEqualTo("PT");
        assertThat(ptTicketDetailDto.getStatus()).isEqualTo("INUSE");
    }

    @Test
    void find() {
        Long ticketId = 1L;

        given(ptTicketRepository.getReferenceById(ticketId)).willReturn(
                PtTicket.fake(1L)
        );

        PtTicketDto ptTicketDto = ptTicketService.find(ticketId);

        assertThat(ptTicketDto.getPeriodOfUse()).isEqualTo(90L);
    }

    @Test
    void findInUse() {
        Long userId = 1L;

        PtTicket inUseTicket = PtTicket.fake(1L);

        inUseTicket.setStartDate("2022-12-10");

        inUseTicket.used();

        given(ptTicketRepository.findAllByUserId(userId)).willReturn(List.of(
                inUseTicket,
                PtTicket.fake(2L)
        ));

        given(productRepository.findAll()).willReturn(List.of(
                Product.fake(new Title("회원권"))
        ));

        given(trainerRepository.findAll()).willReturn(List.of(
                Trainer.fake(new UserName("오진성"))
        ));

        PtTicketDetailDto ptTicketDetailDto = ptTicketService.findInUse(userId);

        assertThat(ptTicketDetailDto.getStartDate()).isEqualTo("2022-12-10");
        assertThat(ptTicketDetailDto.getTrainerUserName()).isEqualTo("오진성");
        assertThat(ptTicketDetailDto.getType()).isEqualTo("PT");
        assertThat(ptTicketDetailDto.getStatus()).isEqualTo("INUSE");
    }

    @Test
    void findByOrderId() {
        Long orderId = 1L;

        given(ptTicketRepository.findByOrderId(orderId)).willReturn(
                Optional.of(PtTicket.fake(1L))
        );

        PtTicketDto ptTicketDto = ptTicketService.findByOrderId(orderId);

        assertThat(ptTicketDto.getPeriodOfUse()).isEqualTo(90L);
    }

    @Test
    void countPt() {
        Long userId = 1L;

        PtTicket inUseTicket = PtTicket.fake(1L);

        inUseTicket.setStartDate("2022-12-10");

        inUseTicket.used();

        given(ptTicketRepository.findAllByUserId(userId)).willReturn(List.of(
                inUseTicket,
                PtTicket.fake(2L)
        ));

        PtTicketDto ptTicketDto = ptTicketService.countPt(userId);

        assertThat(ptTicketDto.getPtTimes()).isEqualTo(11L);
    }

    @Test
    void cancelPt() {
        Long userId = 1L;

        PtTicket inUseTicket = PtTicket.fake(1L);

        inUseTicket.setStartDate("2022-12-10");

        inUseTicket.used();

        given(ptTicketRepository.findAllByUserId(userId)).willReturn(List.of(
                inUseTicket,
                PtTicket.fake(2L)
        ));

        PtTicketDto ptTicketDto = ptTicketService.cancelPt(userId);

        assertThat(ptTicketDto.getPtTimes()).isEqualTo(13L);
    }

    @Test
    void findAllByTrainerId() {
        Long trainerId = 1L;

        PtTicket inUseTicket = PtTicket.fake(1L);

        inUseTicket.setStartDate("2022-12-10");

        inUseTicket.used();


        given(ptTicketRepository.findAllByTrainerId(trainerId)).willReturn(List.of(
                inUseTicket,
                PtTicket.fake(2L)
        ));

        List<PtTicket> ptTickets = ptTicketService.findAllByTrainerId(trainerId);

        assertThat(ptTickets).hasSize(1);
    }
}