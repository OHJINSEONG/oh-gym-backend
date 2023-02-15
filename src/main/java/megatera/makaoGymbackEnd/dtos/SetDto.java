package megatera.makaoGymbackEnd.dtos;

public class SetDto {
    private Long id;

    private Long setNumber;

    private Long weight;

    private Long reps;

    public SetDto() {
    }

    public SetDto(Long id , Long setNumber, Long weight, Long reps) {
        this.id = id;
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
    }

    public Long getId() {
        return id;
    }

    public Long getSetNumber() {
        return setNumber;
    }

    public Long getWeight() {
        return weight;
    }

    public Long getReps() {
        return reps;
    }
}
