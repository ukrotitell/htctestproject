package htc.testproject.dao;

import htc.testproject.entity.Document;

import java.util.List;

public interface DocumentDAO {
    void saveDocument(Document document);

    List<Document> findAll();


}
