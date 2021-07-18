package htc.testproject.service;

import htc.testproject.entity.People;

import java.util.List;

public interface PeopleService {
    List<People> findAll();

    void save(People people);

    People findById(int id);

    List<Long> findCountOfDocuments();
}
