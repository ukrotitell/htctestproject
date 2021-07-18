package htc.testproject.controller;

import htc.testproject.entity.Document;
import htc.testproject.entity.People;
import htc.testproject.entity.TypeDocument;
import htc.testproject.service.DocumentService;
import htc.testproject.service.PeopleService;
import htc.testproject.service.TypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/people")
public class HumanController {

    @Autowired
    private PeopleService peopleService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private TypeDocumentService typeDocumentService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<People> people = peopleService.findAll();
        List<Integer> age = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            age.add(LocalDate.now().getYear() - LocalDate.parse(people.get(i).getBirthdate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).getYear());
        }

        List<Document> documents = documentService.findAll();
        List<Long> countOfEveryTypeOfDocument = typeDocumentService.findCountOfEveryTypeOfDocument();
        List<TypeDocument> allTypesOfDocuments = typeDocumentService.findAll();
        List<Long> peopleList = peopleService.findCountOfDocuments();
        model.addAttribute("peopleList", peopleList);
        model.addAttribute("age", age);
        model.addAttribute("allTypesOfDocuments", allTypesOfDocuments);
        model.addAttribute("countOfEveryTypeOfDocument", countOfEveryTypeOfDocument);
        model.addAttribute("documents", documents);
        model.addAttribute("people", people);
        return "list-people";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        People people = new People();
        model.addAttribute("people", people);
        return "people-form";
    }

    @PostMapping("/save")
    public String savePeople(@ModelAttribute("people") People people) {
        peopleService.save(people);
        return "redirect:/people/list";
    }

    @GetMapping("/showDocumentForm")
    public String showFormForAddDocument(Model model,
                                         @RequestParam("peopleId") int peopleId) {
        People people = peopleService.findById(peopleId);
        model.addAttribute("people", people);
        List<TypeDocument> typeDocumentList = typeDocumentService.findAll();
        model.addAttribute("typeDocumentList", typeDocumentList);

        Document document = new Document();
        model.addAttribute("document", document);


        return "list-documents";
    }

    @PostMapping("/saveDocument")
    public String saveDocument(@Valid @ModelAttribute("document") Document document,
                               BindingResult theBindingResult,
                               @RequestParam("typeDocumentId") int typeDocumentId,
                               @RequestParam("peopleId") int peopleId,
                               RedirectAttributes redirectAttributes


    ) {
        if (theBindingResult.hasErrors()) {
            logger.info("Введите серию и паспорт числовыми значениями (не больше 20 символов), дату dd.MM.yyyy");
            return "redirect:/people/showDocumentForm?peopleId=" + peopleId;
        }
        TypeDocument typeDocument = typeDocumentService.findById(typeDocumentId);
        People people = peopleService.findById(peopleId);

        List<Document> documents = documentService.findAll();
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getTypeDocument().getId() == typeDocumentId && documents.get(i).getPeople().getId() == peopleId) {
                redirectAttributes.addFlashAttribute("message1", "Вы уже сохранили документ");
                return "redirect:/people/showDocumentForm?peopleId=" + peopleId;
            }
        }

        document.setTypeDocument(typeDocument);
        document.setPeople(people);
        documentService.saveDocument(document);
        redirectAttributes.addFlashAttribute("message2", "Документ сохранен");
        return "redirect:/people/showDocumentForm?peopleId=" + peopleId;

    }
}
