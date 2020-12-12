import data.dto.ConsultantDto;
import service.ConsultantCompatibilityChecker;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;


public class ConsultantCompatibilityCheckerTest {

    @Test
    public void should_match() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", true, false, LocalDate.now().minusYears(2), 3);
        ConsultantDto candidate = new ConsultantDto("Anakin", false, true, LocalDate.now().minusYears(1), 2);
        Assert.assertTrue(ConsultantCompatibilityChecker.match(mentor, candidate));
    }


    @Test
    public void should_not_match_if_mentor_disagree() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", false, false, LocalDate.now().minusYears(2), 3);
        ConsultantDto candidate = new ConsultantDto("Anakin", true, true, LocalDate.now().minusYears(1), 2);
        Assert.assertFalse(ConsultantCompatibilityChecker.match(mentor, candidate));
    }

    @Test
    public void should_not_match_if_candidate_disagree() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", true, false, LocalDate.now().minusYears(2), 3);
        ConsultantDto candidate = new ConsultantDto("Anakin", false, false, LocalDate.now().minusYears(1), 2);
        Assert.assertFalse(ConsultantCompatibilityChecker.match(mentor, candidate));
    }

    @Test
    public void should_not_match_if_candidate_older_than_2_years() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", true, false, LocalDate.now().minusYears(2), 3);
        ConsultantDto candidate = new ConsultantDto("Anakin", false, true, LocalDate.now().minusYears(2), 3);
        Assert.assertFalse(ConsultantCompatibilityChecker.match(mentor, candidate));
    }

    @Test
    public void should_not_match_if_mentor_younger_than_1_years() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", true, false, LocalDate.now().minusMonths(1), 3);
        ConsultantDto candidate = new ConsultantDto("Anakin", false, true, LocalDate.now().minusYears(1), 2);
        Assert.assertFalse(ConsultantCompatibilityChecker.match(mentor, candidate));
    }

    @Test
    public void should_not_match_if_candidate_older_than_mentor() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", true, false, LocalDate.now().minusMonths(13), 3);
        ConsultantDto candidate = new ConsultantDto("Anakin", false, true, LocalDate.now().minusMonths(14), 2);
        Assert.assertFalse(ConsultantCompatibilityChecker.match(mentor, candidate));
    }

    @Test
    public void should_not_match_if_candidate_more_experiment_than_mentor() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", true, false, LocalDate.now().minusYears(2), 3);
        ConsultantDto candidate = new ConsultantDto("Anakin", false, true, LocalDate.now().minusYears(1), 4);
        Assert.assertFalse(ConsultantCompatibilityChecker.match(mentor, candidate));
    }

    @Test
    public void should_not_match_if_candidate_has_already_mentor() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", true, false, LocalDate.now().minusYears(2), 4);
        ConsultantDto candidate = new ConsultantDto("Anakin", false, true, LocalDate.now().minusYears(1), 3);
        candidate.setMentorName(mentor.getName());
        Assert.assertFalse(ConsultantCompatibilityChecker.match(mentor, candidate));
    }

    @Test
    public void should_not_match_if_mentor_has_already_anakin_as_apprentice() {
        ConsultantDto mentor = new ConsultantDto("ObiWan", true, false, LocalDate.now().minusYears(2), 4);
        mentor.getApprentices().add("Anakin");
        ConsultantDto candidate = new ConsultantDto("Anakin", false, true, LocalDate.now().minusYears(1), 3);
        Assert.assertFalse(ConsultantCompatibilityChecker.match(mentor, candidate));
    }


}
