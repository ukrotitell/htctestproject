package htc.testproject.dao;

import htc.testproject.entity.Document;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO {

    private EntityManager entityManager;

    @Autowired
    public DocumentDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void saveDocument(Document document) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(document);
    }

    @Override
    public List<Document> findAll() {
        Session session = entityManager.unwrap(Session.class);


        Query<Document> query = session.createQuery("from Document", Document.class);

        List<Document> documents = query.getResultList();

        return documents;
    }

}
