package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.*;
import megatera.makaoGymbackEnd.models.Category;
import megatera.makaoGymbackEnd.models.Exercise;
import megatera.makaoGymbackEnd.models.Name;
import megatera.makaoGymbackEnd.models.Set;
import megatera.makaoGymbackEnd.repositories.ExerciseRepository;
import megatera.makaoGymbackEnd.repositories.SetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final SetRepository setRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, SetRepository setRepository) {
        this.exerciseRepository = exerciseRepository;
        this.setRepository = setRepository;
    }

    public ExerciseDto create(String name, String type, Long diaryId) {
        Exercise exercise = new Exercise(diaryId, new Name(name), new Category(type));

        exercise.create();

        exerciseRepository.save(exercise);

        return exercise.toDto();
    }

    public List<ExerciseResultDto> list(Long diaryId) {
        List<Exercise> exercises = exerciseRepository.findAllByDiaryId(diaryId);

        List<Set> sets = setRepository.findAll();

        return exercises.stream().map(exercise -> exercise.toResultDto(sets)).toList();
    }

    public ExerciseResultDto find(Long exerciseId) {
        Exercise exercise = exerciseRepository.getReferenceById(exerciseId);

        List<Set> sets = setRepository.findAll();

        return exercise.toResultDto(sets);
    }

    public ExerciseResultDto complete(Long exerciseId) {
        Exercise exercise = exerciseRepository.getReferenceById(exerciseId);

        List<Set> sets = setRepository.findAll();

        exercise.complete();

        return exercise.toResultDto(sets);
    }

    public void delete(Long exerciseId) {
        List<Set> sets = setRepository.findAllByExerciseId(exerciseId);

        for(Set set : sets){
            setRepository.delete(set);
        }

        exerciseRepository.deleteById(exerciseId);
    }
}
