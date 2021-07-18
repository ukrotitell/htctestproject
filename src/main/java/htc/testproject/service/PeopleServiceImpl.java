package htc.testproject.service;

import htc.testproject.dao.PeopleDAO;
import htc.testproject.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    private PeopleDAO peopleDAO;

    @Autowired
    public PeopleServiceImpl(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    @Transactional
    public List<People> findAll() {
        return peopleDAO.findAll();
    }

    @Override
    @Transactional
    public void save(People people) {
        peopleDAO.save(people);
    }

    @Override
    @Transactional
    public People findById(int id) {
        return peopleDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Long> findCountOfDocuments() {
        return peopleDAO.findCountOfDocuments();
    }
}
