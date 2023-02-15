package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.SetDto;
import megatera.makaoGymbackEnd.dtos.SetPatchDto;
import megatera.makaoGymbackEnd.dtos.SetResultDto;
import megatera.makaoGymbackEnd.models.Set;
import megatera.makaoGymbackEnd.models.Weight;
import megatera.makaoGymbackEnd.repositories.SetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class SetService {
    private final SetRepository setRepository;

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
        setRepository.deleteById(setId);
    }

    public SetResultDto complete(Long setId) {
        Set set = setRepository.getReferenceById(setId);
        set.complete();
        return set.toDto();
    }
}
