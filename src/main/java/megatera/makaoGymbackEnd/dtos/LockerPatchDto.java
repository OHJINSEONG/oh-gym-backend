package megatera.makaoGymbackEnd.dtos;

public class LockerPatchDto {
    private String type;

    public LockerPatchDto() {
    }

    public LockerPatchDto(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
