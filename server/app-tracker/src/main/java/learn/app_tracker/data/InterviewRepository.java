package learn.app_tracker.data;

import learn.app_tracker.models.Interview;

import java.util.List;

public interface InterviewRepository {
    List<Interview> findAll() throws DataException;

    List<Interview> findAllByApplicationId(int appId) throws DataException;

    Interview findById(int id) throws DataException;

    Interview add(Interview interview) throws DataException;

    boolean update(Interview interview) throws DataException;

    boolean deleteById(int id) throws DataException;
}
