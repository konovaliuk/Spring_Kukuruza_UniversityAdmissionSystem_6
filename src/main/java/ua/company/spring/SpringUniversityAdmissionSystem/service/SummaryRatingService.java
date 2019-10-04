package ua.company.spring.SpringUniversityAdmissionSystem.service;

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
public class SummaryRatingService {
    private IDaoRequest daoRequest;

    public SummaryRatingService(IDaoRequest daoRequest) {
        this.daoRequest = daoRequest;
    }

    @Transactional(readOnly = true)
    public Optional<Request> getUserRequest(User user) {
        return daoRequest.findByUser(user);
    }

    @Transactional(readOnly = true)
    public List<Request> getRequestsByEducationOptionOrderByRating(EducationOption educationOption) {
        List<Request> requests = daoRequest.findByEducationOption(educationOption);
        return requests.stream()
                .sorted(Comparator.comparingInt(Request::getRating).reversed())
                .collect(Collectors.toList());
    }
}
