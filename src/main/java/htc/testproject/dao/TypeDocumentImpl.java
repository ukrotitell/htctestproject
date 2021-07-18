package htc.testproject.dao;

import htc.testproject.entity.TypeDocument;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TypeDocumentImpl implements TypeDocumentDAO {

    private EntityManager entityManager;

    @Autowired
    public TypeDocumentImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<TypeDocument> findAll() {
        Session session = entityManager.unwrap(Session.class);


        Query<TypeDocument> query = session.createQuery("from TypeDocument", TypeDocument.class);

        List<TypeDocument> typeDocumentList = query.getResultList();

        return typeDocumentList;
    }

    @Override
    public TypeDocument findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<TypeDocument> theQuery = currentSession.createQuery(" from TypeDocument   where id=:typeDocumentId ", TypeDocument.class);
        theQuery.setParameter("typeDocumentId", id);

        TypeDocument typeDocument = null;

        try {
            typeDocument = theQuery.getSingleResult();
        } catch (Exception e) {
            typeDocument = null;
        }
        return typeDocument;
    }

    @Override
    public List<Long> findCountOfEveryTypeOfDocument() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Long> theQuery = currentSession.createQuery(
                " select  count(d.id)   from TypeDocument td left join Document d on td.id=d.typeDocument.id group by td.name order by td.id",
                Long.class);


        List<Long> countOfEveryTypeOfDocuments = null;
        try {
            countOfEveryTypeOfDocuments = theQuery.getResultList();
        } catch (Exception e) {
            countOfEveryTypeOfDocuments = null;
        }
        return countOfEveryTypeOfDocuments;
    }
}
