package htc.testproject.service;

import htc.testproject.entity.Document;

import java.util.List;

public interface DocumentService {
    void saveDocument(Document document);

    List<Document> findAll();


}
