package service;

import data.dao.ConsultantDao;
import data.dto.ConsultantDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MentoringService {

    private ConsultantDao consultantDao;

    public boolean checkConsultantCompatibility(String mentorName, String mentoredName) {
        ConsultantDto mentor = consultantDao.getConsultantByName(mentorName);
        ConsultantDto mentored = consultantDao.getConsultantByName(mentoredName);
        return ConsultantCompatibilityChecker.match(mentor, mentored);
    }

    public void saveMentorMentoredRelation(String mentorName, String mentoredName) {
        ConsultantDto mentor = consultantDao.getConsultantByName(mentorName);
        ConsultantDto mentored = consultantDao.getConsultantByName(mentoredName);
        mentor.getApprentices().add(mentoredName);
        mentored.setMentorName(mentorName);
        consultantDao.saveOrUpdateConsultant(mentor);
        consultantDao.saveOrUpdateConsultant(mentored);
    }


}