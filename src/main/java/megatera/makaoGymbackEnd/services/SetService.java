package megatera.makaoGymbackEnd.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import megatera.makaoGymbackEnd.dtos.SetDto;
import megatera.makaoGymbackEnd.dtos.SetResultDto;
import megatera.makaoGymbackEnd.exceptions.DeleteError;
import megatera.makaoGymbackEnd.manager.RedisCacheManager;
import megatera.makaoGymbackEnd.models.QSet;
import megatera.makaoGymbackEnd.models.Set;
import megatera.makaoGymbackEnd.models.Weight;
import megatera.makaoGymbackEnd.repositories.SetRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class SetService {
    private final SetRepository setRepository;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;


    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    public SetResultDto create(Long exerciseId) {
        Set set = new Set(exerciseId, new Weight(0L), 0L, 0L);

        set.created();

        setRepository.save(set);

        return set.toDto();
    }

    public List<SetResultDto> list(Long exerciseId) {
        return setRepository.findAllByExerciseId(exerciseId).stream().map(Set::toDto).toList();
    }

    public List<SetResultDto> patch(Long exerciseId, List<SetDto> setDtos) {
        List<Set> sets = setRepository.findAllByExerciseId(exerciseId);
        for (Set set : sets) {
            Optional<SetDto> find = setDtos.stream().filter(setDto -> Objects.equals(setDto.getId(), set.id())).findFirst();
            find.ifPresent(set::patch);
        }

        return sets.stream().map(Set::toDto).toList();
    }

    public void delete(Long setId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        try {
            QSet qSet = QSet.set;

            long deletedCount = queryFactory
                    .delete(qSet)
                    .where(qSet.id.eq(setId))
                    .execute();


            entityTransaction.commit();
            entityManager.close();

        } catch (Exception exception) {
            System.out.println("트랜잭션 에러");
            System.out.println(exception.getMessage());
            entityTransaction.rollback();
            entityManager.close();

            throw new DeleteError(exception.getMessage());
        }
    }

    public SetResultDto complete(Long setId) {
        Set set = setRepository.getReferenceById(setId);
        set.complete();

        return set.toDto();
    }
}
