package megatera.makaoGymbackEnd.dtos;

public class ExerciseDto {
    private Long id;

    private String name;

    private String type;

    private String status;

    public ExerciseDto(Long id, String name, String type, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
