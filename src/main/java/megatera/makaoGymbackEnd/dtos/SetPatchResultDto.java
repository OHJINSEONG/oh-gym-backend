package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class SetPatchResultDto {
    private List<SetPatchDto> sets;

    public SetPatchResultDto() {
    }

    public SetPatchResultDto(List<SetPatchDto> sets) {
        this.sets = sets;
    }

    public List<SetPatchDto> getSets() {
        return sets;
    }
}
