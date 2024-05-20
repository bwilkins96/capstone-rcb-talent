package learn.app_tracker.data;

import learn.app_tracker.models.Interview;

import java.util.List;

public interface InterviewRepository {
    List<Interview> findAll();

    List<Interview> findAllByApplicationId(int appId);

    Interview findById(int id);

    Interview add(Interview interview);

    boolean update(Interview interview);

    boolean deleteById(int id);
}
