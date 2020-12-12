package service;

import data.dto.ConsultantDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

public class ConsultantCompatibilityChecker {


    public static boolean match(ConsultantDto mentor, ConsultantDto candidate) {
        return isMentorEligible(mentor) &&
                isCandidateEligible(candidate) &&
                isCandidateMoreExperimentThanCandidate(mentor, candidate) &&
                mentorNotHasAlreadyCandidate(mentor, candidate);
    }

    private static boolean mentorNotHasAlreadyCandidate(ConsultantDto mentor, ConsultantDto candidate) {
        return !Optional.ofNullable(mentor.getApprentices()).orElse(Collections.emptyList()).contains(candidate.getName());
    }

    private static boolean isMentorEligible(ConsultantDto mentor) {
        LocalDate referenceEntranceDate = LocalDate.now().minusYears(1);
        return mentor.isAgreeToBeMentor() && mentor.getEntranceDate().isBefore(referenceEntranceDate);
    }

    private static boolean isCandidateEligible(ConsultantDto candidate) {
        LocalDate referenceEntranceDate = LocalDate.now().minusYears(2);
        return candidate.isAgreeToBeMentored() && candidate.getEntranceDate().isAfter(referenceEntranceDate)
                && candidate.getMentorName() == null;
    }

    private static boolean isCandidateMoreExperimentThanCandidate(ConsultantDto mentor, ConsultantDto candidate) {
        return mentor.getEntranceDate().isBefore(candidate.getEntranceDate())
                && mentor.getYearsOfExperience() > candidate.getYearsOfExperience();
    }
}
