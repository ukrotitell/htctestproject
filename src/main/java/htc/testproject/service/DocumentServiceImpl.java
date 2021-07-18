package htc.testproject.service;

import htc.testproject.dao.DocumentDAO;
import htc.testproject.entity.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentDAO documentDAO;

    public DocumentServiceImpl(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Override
    @Transactional
    public void saveDocument(Document document) {
        documentDAO.saveDocument(document);
    }

    @Override
    public List<Document> findAll() {
        return documentDAO.findAll();
    }


}
