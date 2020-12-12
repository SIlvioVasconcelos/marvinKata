package service;

import data.dao.ConsultantDao;
import data.dto.ConsultantDto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class MentoringServiceTest {

    @Test
    public void should_return_true_with_2_good_consultants() {
        ConsultantDao consultantDao = Mockito.mock(ConsultantDao.class);
        Mockito.when(consultantDao.getConsultantByName("Obi-Wan")).thenReturn(new ConsultantDto("ObiWan", true,false, LocalDate.now().minusYears(2), 3));
        Mockito.when(consultantDao.getConsultantByName("Anakin")).thenReturn(new ConsultantDto("Anakin",false, true, LocalDate.now().minusYears(1), 2));
        MentoringService mentoringService = new MentoringService(consultantDao);
        Assert.assertTrue(mentoringService.checkConsultantCompatibility("Obi-Wan", "Anakin"));
    }

}
