package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoRequest;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.EducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Request;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class SummaryRatingService {
    private final IDaoRequest daoRequest;

    @Transactional(readOnly = true)
    public Optional<Request> getUserRequest(User user) {
        log.info("Try to get user request by userId");
        return daoRequest.findByUser(user);
    }

    @Transactional(readOnly = true)
    public List<Request> getRequestsByEducationOptionOrderByRating(EducationOption educationOption) {
        log.info("Try to get user requests by an education option");
        List<Request> requests = daoRequest.findByEducationOption(educationOption);
        return requests.stream()
                .sorted(Comparator.comparingInt(Request::getRating).reversed())
                .collect(Collectors.toList());
    }
}
