package net.youssouf.hopital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.youssouf.hopital.entities.Patient;
import net.youssouf.hopital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name="size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw
                        ) {
        //List<Patient> patients = patientRepository.findAll();
        Page <Patient> patientPages = patientRepository.findByNameContainingIgnoreCase(kw, PageRequest.of(page, size));
        model.addAttribute("patients", patientPages.getContent());
        model.addAttribute("pages", new int[patientPages.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page ) {
        patientRepository.deleteById(id);
        return "redirect:/index?page=" + page+"&keyword=" + keyword;
    }
    @GetMapping("/formPatients")
    public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping("/save")
    public String save(@Valid Patient patient, BindingResult bindingResult, Model model,
                       @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword) {
        if(bindingResult.hasErrors()) { return "formPatients"; }
        patientRepository.save(patient);
        return "redirect:/index?page=" + page+"&keyword=" + keyword;
    }
    @GetMapping("/edit")
    public String editPatient(@RequestParam Long id, Model model,String keyword,int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException("Patient not found");
        model.addAttribute("patient", patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editPatient";
    }

}
