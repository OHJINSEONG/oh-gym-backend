package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.DiaryDto;
import megatera.makaoGymbackEnd.dtos.DiaryResultDto;
import megatera.makaoGymbackEnd.models.Diary;
import megatera.makaoGymbackEnd.models.Exercise;
import megatera.makaoGymbackEnd.models.Set;
import megatera.makaoGymbackEnd.repositories.DiaryRepository;
import megatera.makaoGymbackEnd.repositories.ExerciseRepository;
import megatera.makaoGymbackEnd.repositories.SetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final ExerciseRepository exerciseRepository;
    private final SetRepository setRepository;

    public DiaryService(DiaryRepository diaryRepository, ExerciseRepository exerciseRepository, SetRepository setRepository) {
        this.diaryRepository = diaryRepository;
        this.exerciseRepository = exerciseRepository;
        this.setRepository = setRepository;
    }

    public List<DiaryResultDto> list(Long userId) {
        List<Diary> diarys = diaryRepository.findAllByUserId(userId);

        List<Exercise> exercises = exerciseRepository.findAll();

        List<Set> sets = setRepository.findAll();

        return diarys.stream().map(diary -> diary.toResultDto(exercises, sets)).toList();
    }

    public DiaryResultDto find(String date, Long userId) {
        Optional<Diary> diary = diaryRepository.findByDateAndUserId(LocalDate.parse(date), userId);

        if (diary.isPresent()) {
            List<Exercise> exercises = exerciseRepository.findAll();

            List<Set> sets = setRepository.findAll();

            return diary.get().toResultDto(exercises, sets);
        }

        return new DiaryResultDto(null, null);
    }

    public DiaryDto create(String date, Long userId) {
        Optional<Diary> diary = diaryRepository.findByDateAndUserId(LocalDate.parse(date), userId);

        if (diary.isPresent()) {
            return diary.get().toDto();
        }

        Diary createDiary = new Diary(userId, LocalDate.parse(date), "", "");

        createDiary.create();

        diaryRepository.save(createDiary);

        return createDiary.toDto();
    }

    public DiaryResultDto complete(Long diaryId, String memo, String time) {
        Diary diary = diaryRepository.getReferenceById(diaryId);

        List<Exercise> exercises = exerciseRepository.findAll();

        List<Set> sets = setRepository.findAll();

        diary.complete();

        diary.setMemo(memo);

        diary.setTime(time);

        return diary.toResultDto(exercises, sets);
    }

    public DiaryResultDto findById(Long diaryId) {
        Diary diary = diaryRepository.getReferenceById(diaryId);

        List<Exercise> exercises = exerciseRepository.findAll();

        List<Set> sets = setRepository.findAll();

        return diary.toResultDto(exercises, sets);
    }

    public void delete(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }
}
