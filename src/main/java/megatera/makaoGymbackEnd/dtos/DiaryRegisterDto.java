package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class DiaryRegisterDto {
    private String date;

    public DiaryRegisterDto() {
    }

    public DiaryRegisterDto(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
