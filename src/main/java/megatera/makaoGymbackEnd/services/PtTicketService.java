package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.PtTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.PtTicketDto;
import megatera.makaoGymbackEnd.models.*;
import megatera.makaoGymbackEnd.repositories.ProductRepository;
import megatera.makaoGymbackEnd.repositories.PtTicketRepository;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PtTicketService {
    private final PtTicketRepository ptTicketRepository;
    private final ProductRepository productRepository;
    private final TrainerRepository trainerRepository;

    public PtTicketService(
            PtTicketRepository ptTicketRepository,
            ProductRepository productRepository,
            TrainerRepository trainerRepository
    ) {
        this.ptTicketRepository = ptTicketRepository;
        this.productRepository = productRepository;
        this.trainerRepository = trainerRepository;
    }

    public PtTicketDto create(Long userId, Long trainerId, Long orderId, Long dateOfUse, Long ptTimes, Long productId) {
        PtTicket ptTicket = new PtTicket(userId, trainerId, orderId, new Period(dateOfUse), new Count(ptTimes), productId);

        ptTicket.unUsed();

        ptTicketRepository.save(ptTicket);

        return ptTicket.toDto();
    }

    public List<PtTicketDetailDto> list(Long userId) {
        List<PtTicket> ptTickets = ptTicketRepository.findAllByUserId(userId);

        List<Product> products = productRepository.findAll();

        List<Trainer> trainers = trainerRepository.findAll();

        return ptTickets.stream().map(ptTicket -> ptTicket.toDetailDto(products, trainers)).toList();
    }

    public PtTicketDetailDto use(Long ticketId, String startDate) {
        PtTicket ptTicket = ptTicketRepository.getReferenceById(ticketId);

        List<Product> products = productRepository.findAll();

        List<Trainer> trainers = trainerRepository.findAll();

        ptTicket.setStartDate(startDate);

        ptTicket.used();

        return ptTicket.toDetailDto(products, trainers);
    }

    public PtTicketDto find(Long ticketId) {
        return ptTicketRepository.getReferenceById(ticketId).toDto();
    }

    public PtTicketDetailDto findInUse(Long userId) {
        List<PtTicket> ptTickets = ptTicketRepository.findAllByUserId(userId);

        List<Product> products = productRepository.findAll();

        List<Trainer> trainers = trainerRepository.findAll();

        Optional<PtTicket> optionalPtTicket = ptTickets.stream().filter(PtTicket::inUse).findFirst();

        return optionalPtTicket.map(ptTicket -> ptTicket.toDetailDto(products, trainers)).orElse(null);
    }

    public PtTicketDto findByOrderId(Long orderId) {
        Optional<PtTicket> optionalPtTicket = ptTicketRepository.findByOrderId(orderId);

        return optionalPtTicket.map(PtTicket::toDto).orElseGet(() -> new PtTicketDto(null, null, null, null));
    }

    public PtTicketDto countPt(Long userId) {
        List<PtTicket> ptTickets = ptTicketRepository.findAllByUserId(userId);

        Optional<PtTicket> optionalPtTicket = ptTickets.stream().filter(PtTicket::inUse).findFirst();

        optionalPtTicket.ifPresent(PtTicket::countPt);

        return optionalPtTicket.map(PtTicket::toDto).orElse(null);
    }

    public PtTicketDto cancelPt(Long userId) {
        List<PtTicket> ptTickets = ptTicketRepository.findAllByUserId(userId);

        Optional<PtTicket> optionalPtTicket = ptTickets.stream().filter(PtTicket::inUse).findFirst();

        optionalPtTicket.ifPresent(PtTicket::cancelPt);

        return optionalPtTicket.map(PtTicket::toDto).orElse(null);
    }

    public List<PtTicket> findAllByTrainerId(Long trainerId) {
        List<PtTicket> ptTickets = ptTicketRepository.findAllByTrainerId(trainerId);

        return ptTickets.stream().filter(PtTicket::inUse).toList();
    }
}
