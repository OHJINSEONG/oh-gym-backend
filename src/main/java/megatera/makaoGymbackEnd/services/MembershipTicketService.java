package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.MembershipTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.MembershipTicketDto;
import megatera.makaoGymbackEnd.models.MembershipTicket;
import megatera.makaoGymbackEnd.models.Period;
import megatera.makaoGymbackEnd.models.Product;
import megatera.makaoGymbackEnd.models.Trainer;
import megatera.makaoGymbackEnd.repositories.MembershipTicketRepository;
import megatera.makaoGymbackEnd.repositories.ProductRepository;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MembershipTicketService {
    private final MembershipTicketRepository membershipTicketRepository;
    private final ProductRepository productRepository;
    private final TrainerRepository trainerRepository;

    public MembershipTicketService(MembershipTicketRepository membershipTicketRepository, ProductRepository productRepository, TrainerRepository trainerRepository) {
        this.membershipTicketRepository = membershipTicketRepository;
        this.productRepository = productRepository;
        this.trainerRepository = trainerRepository;
    }

    public MembershipTicketDto create(Long userId, Long orderId, Long dateOfUse, Long productId) {
        MembershipTicket membershipTicket = new MembershipTicket(userId, productId, new Period(dateOfUse), orderId);

        membershipTicket.unused();

        membershipTicketRepository.save(membershipTicket);

        return membershipTicket.toDto();
    }

    public List<MembershipTicketDetailDto> list(Long userId) {
        List<MembershipTicket> membershipTickets = membershipTicketRepository.findAllByUserId(userId);

        List<Product> products = productRepository.findAll();

        List<Trainer> trainers = trainerRepository.findAll();

        return membershipTickets.stream().map(membershipTicket -> membershipTicket.toDetailDto(products, trainers)).toList();
    }

    public MembershipTicketDto find(Long ticketId) {
        return membershipTicketRepository.getReferenceById(ticketId).toDto();
    }

    public MembershipTicketDetailDto use(Long ticketId, String startDate) {
        MembershipTicket membershipTicket = membershipTicketRepository.getReferenceById(ticketId);

        List<Product> products = productRepository.findAll();

        List<Trainer> trainers = trainerRepository.findAll();

        membershipTicket.setStartDate(startDate);

        membershipTicket.used();

        return membershipTicket.toDetailDto(products, trainers);
    }

    public MembershipTicketDetailDto findInUse(Long userId) {
        List<MembershipTicket> membershipTickets = membershipTicketRepository.findAllByUserId(userId);

        List<Product> products = productRepository.findAll();

        List<Trainer> trainers = trainerRepository.findAll();

        Optional<MembershipTicket> optionalMembershipTicket = membershipTickets.stream().filter(MembershipTicket::inUse).findFirst();

        return optionalMembershipTicket.map(membershipTicket -> membershipTicket.toDetailDto(products, trainers)).orElse(null);
    }
}
