package htc.testproject.dao;

import htc.testproject.entity.People;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PeopleDAOImpl implements PeopleDAO {

    private EntityManager entityManager;

    @Autowired
    public PeopleDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<People> findAll() {

        Session session = entityManager.unwrap(Session.class);


        Query<People> query = session.createQuery("from People", People.class);

        List<People> people = query.getResultList();

        return people;
    }

    @Override
    public void save(People people) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(people);
    }

    @Override
    public People findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<People> theQuery = currentSession.createQuery("from People where id=:peopleId", People.class);
        theQuery.setParameter("peopleId", id);

        People people = null;

        try {
            people = theQuery.getSingleResult();
        } catch (Exception e) {
            people = null;
        }
        return people;
    }

    @Override
    public List<Long> findCountOfDocuments() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Long> theQuery = currentSession.createQuery(
                " select  count(d.id)   from People p left join Document d on p.id=d.people.id group by p.id order by p.id",
                Long.class);


        List<Long> countOfDocuments = null;
        try {
            countOfDocuments = theQuery.getResultList();
        } catch (Exception e) {
            countOfDocuments = null;
        }
        return countOfDocuments;
    }

}
