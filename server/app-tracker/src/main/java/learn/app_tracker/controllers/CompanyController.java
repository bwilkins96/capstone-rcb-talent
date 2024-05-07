package learn.app_tracker.controllers;

import learn.app_tracker.domain.CompanyService;
import learn.app_tracker.models.Company;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Company> findAll() {
        return service.findAll();
    }

    @GetMapping("/{companyId}")
    public Company findById(@PathVariable int companyId) {
        return service.findById(companyId);
    }

}
