package htc.testproject.dao;

import htc.testproject.entity.TypeDocument;

import java.util.List;

public interface TypeDocumentDAO {
    List<TypeDocument> findAll();

    TypeDocument findById(int id);

    List<Long> findCountOfEveryTypeOfDocument();
}
