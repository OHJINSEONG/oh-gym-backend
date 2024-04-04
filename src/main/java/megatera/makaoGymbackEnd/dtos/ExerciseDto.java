package megatera.makaoGymbackEnd.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExerciseDto {
    private Long id;

    private String name;

    private String type;

    private String status;

    @JsonCreator
    public ExerciseDto(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("status") String status
    ) {
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
