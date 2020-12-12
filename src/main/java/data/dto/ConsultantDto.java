package data.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ConsultantDto {

    private String name;

    private boolean agreeToBeMentor;

    private boolean agreeToBeMentored;

    private LocalDate entranceDate;

    private int yearsOfExperience;

    private String mentorName;

    private List<String> apprentices;

    public ConsultantDto(String name, boolean agreeToBeMentor, boolean agreeToBeMentored, LocalDate entranceDate, int yearsOfExperience) {
        this.name = name;
        this.agreeToBeMentor = agreeToBeMentor;
        this.agreeToBeMentored = agreeToBeMentored;
        this.entranceDate = entranceDate;
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getApprentices() {
        if (this.apprentices == null) {
            this.apprentices = new ArrayList<>();
        }
        return this.apprentices;
    }
}
