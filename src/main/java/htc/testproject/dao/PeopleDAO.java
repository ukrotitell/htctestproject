package htc.testproject.dao;

import htc.testproject.entity.People;

import java.util.List;

public interface PeopleDAO {
    List<People> findAll();

    void save(People people);

    People findById(int id);

    List<Long> findCountOfDocuments();

}
