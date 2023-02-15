package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.MembershipTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.MembershipTicketDto;
import megatera.makaoGymbackEnd.models.*;
import megatera.makaoGymbackEnd.repositories.MembershipTicketRepository;
import megatera.makaoGymbackEnd.repositories.ProductRepository;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MembershipTicketServiceTest {
    private MembershipTicketService membershipTicketService;
    private MembershipTicketRepository membershipTicketRepository;
    private ProductRepository productRepository;
    private TrainerRepository trainerRepository;

    @BeforeEach
    void setup() {
        membershipTicketRepository = mock(MembershipTicketRepository.class);
        productRepository = mock(ProductRepository.class);
        trainerRepository = mock(TrainerRepository.class);
        membershipTicketService = new MembershipTicketService(membershipTicketRepository, productRepository,trainerRepository);
    }

    @Test
    void create() {
        Long userId = 1L;
        Long orderId = 1L;
        Long dateOfUse = 90L;
        Long productId = 1L;

        MembershipTicketDto membershipTicketDto = membershipTicketService.create(userId, orderId, dateOfUse, productId);

        assertThat(membershipTicketDto.getStatus()).isEqualTo("UNUSED");
    }

    @Test
    void list() {
        Long userId = 1L;

        given(membershipTicketRepository.findAllByUserId(userId)).willReturn(List.of(
                MembershipTicket.fake(1L)
        ));

        given(productRepository.findAll()).willReturn(List.of(
                Product.fake(new Title("회원권"))
        ));

        given(trainerRepository.findAll()).willReturn(List.of(
                Trainer.fake(new UserName("오진성"))
        ));

        List<MembershipTicketDetailDto> membershipTicketDetailDtos = membershipTicketService.list(userId);

        assertThat(membershipTicketDetailDtos.get(0).getPeriodOfDate()).isEqualTo(90L);
        assertThat(membershipTicketDetailDtos.get(0).getTrainerUserName()).isEqualTo("오진성");
        assertThat(membershipTicketDetailDtos.get(0).getType()).isEqualTo("PT");
    }

    @Test
    void use() {
        Long ticketId = 1L;
        String startDate = "2022-12-11";

        given(membershipTicketRepository.getReferenceById(ticketId)).willReturn(
                MembershipTicket.fake(1L)
        );

        given(productRepository.findAll()).willReturn(List.of(
                Product.fake(new Title("회원권"))
        ));

        given(trainerRepository.findAll()).willReturn(List.of(
                Trainer.fake(new UserName("오진성"))
        ));

        MembershipTicketDetailDto membershipTicketDetailDto = membershipTicketService.use(ticketId,startDate);

        assertThat(membershipTicketDetailDto.getPeriodOfDate()).isEqualTo(90L);
        assertThat(membershipTicketDetailDto.getStartDate()).isEqualTo("2022-12-11");
        assertThat(membershipTicketDetailDto.getStatus()).isEqualTo("INUSE");
    }

    @Test
    void findInUse() {
        Long userId = 1L;

        MembershipTicket inUseMemberShipTicket = MembershipTicket.fake(1L);

        inUseMemberShipTicket.setStartDate(LocalDate.now().toString());

        inUseMemberShipTicket.used();

        given(membershipTicketRepository.findAllByUserId(userId)).willReturn(List.of(
                inUseMemberShipTicket,
                MembershipTicket.fake(2L)
        ));

        given(productRepository.findAll()).willReturn(List.of(
                Product.fake(new Title("회원권"))
        ));

        given(trainerRepository.findAll()).willReturn(List.of(
                Trainer.fake(new UserName("오진성"))
        ));

        MembershipTicketDetailDto membershipTicketDetailDto = membershipTicketService.findInUse(userId);

        assertThat(membershipTicketDetailDto.getStatus()).isEqualTo("INUSE");
    }
}