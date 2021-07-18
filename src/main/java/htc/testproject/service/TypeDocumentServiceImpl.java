package htc.testproject.service;

import htc.testproject.dao.TypeDocumentDAO;
import htc.testproject.entity.TypeDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeDocumentServiceImpl implements TypeDocumentService {

    private TypeDocumentDAO typeDocumentDAO;

    @Autowired
    public TypeDocumentServiceImpl(TypeDocumentDAO typeDocumentDAO) {
        this.typeDocumentDAO = typeDocumentDAO;
    }

    @Override
    public List<TypeDocument> findAll() {
        return typeDocumentDAO.findAll();
    }

    @Override
    public TypeDocument findById(int id) {
        return typeDocumentDAO.findById(id);
    }

    @Override
    public List<Long> findCountOfEveryTypeOfDocument() {
        return typeDocumentDAO.findCountOfEveryTypeOfDocument();
    }
}
