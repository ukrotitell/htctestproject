package htc.testproject.service;

import htc.testproject.entity.TypeDocument;

import java.util.List;

public interface TypeDocumentService {
    List<TypeDocument> findAll();

    TypeDocument findById(int id);

    List<Long> findCountOfEveryTypeOfDocument();
}
