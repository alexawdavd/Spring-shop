package com.example.Shop.controller;

import com.example.Shop.models.*;
import com.example.Shop.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ShopController {
    @Autowired
    private TovarRepository tovarRepository;
    @Autowired
    private PoctsvchikRepository poctsvchikRepository;
    @Autowired
    private LicenziyaRepository licenziyaRepository;
    @Autowired
    private VozvratRepository vozvratRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private NakladnayaRepository nakladnayaRepository;

    @GetMapping("/")
    public String Main(Model model){
        Iterable<Tovar> tovars = tovarRepository.findAll();
        model.addAttribute("tovars", tovars);
        Iterable<Poctsvchik> poctsvchik = poctsvchikRepository.findAll();
        Iterable<Licenziya> licenziya = licenziyaRepository.findAll();
        model.addAttribute("poctsvchik", poctsvchik);
        model.addAttribute("licenziya", licenziya);
        return "tovar";
    }

    @PostMapping("/tovar/add")
    public String blogPostAdd(Tovar tovar, @RequestParam String nameTovar, @RequestParam Integer costTovar,
                              @RequestParam Integer kolvoTovar, @RequestParam String namePoctsvchik,
                              @RequestParam String nameTov, Model model, BindingResult bindingResult){
        Poctsvchik poctsvchik = poctsvchikRepository.findByNamePoctsvchik(namePoctsvchik);
        Licenziya licenziya = licenziyaRepository.findByNameTov(nameTov);
        if(nameTovar=="" || costTovar==null || kolvoTovar==null)
            return "tovar";
        else {
            if (nameTovar.equals(nameTov) && kolvoTovar <= poctsvchik.getKolvoPoctsvchik()) {
                tovar = new Tovar(nameTovar, costTovar, kolvoTovar, true, poctsvchik, licenziya);
                tovarRepository.save(tovar);
                return "redirect:/";
            }
            return "tovar";
        }
    }

    @GetMapping("/poctsvchik")
    public String MainPoctsvchik(Model model){
        Iterable<Poctsvchik> poctsvchiks = poctsvchikRepository.findAll();
        model.addAttribute("poctsvchiks", poctsvchiks);
        return "poctsvchik";
    }

    @GetMapping("/poctsvchik/add")
    public String bookAdd(Poctsvchik poctsvchik){
        return ("poctsvchik-add");
    }

    @PostMapping("/poctsvchik/add")
    public String poctsvchikPostAdd(@Valid Poctsvchik poctsvchik, BindingResult bindingResult, @RequestParam String namePoctsvchik){
        if(bindingResult.hasErrors())
            return "poctsvchik-add";
        poctsvchikRepository.save(poctsvchik);
        return "redirect:/poctsvchik";
    }


    @GetMapping("/licenziya")
    public String MainLicenziya(Model model){
        Iterable<Licenziya> licenziyas = licenziyaRepository.findAll();
        model.addAttribute("licenziyas", licenziyas);
        return "licenziya";
    }

    @GetMapping("/licenziya/add")
    public String licenziyaAdd(Licenziya licenziya){
        return ("licenziya-add");
    }

    @PostMapping("/licenziya/add")
    public String licenziyaPostAdd(@Valid Licenziya licenziya, BindingResult bindingResult, @RequestParam String nameTov){
        if(bindingResult.hasErrors())
            return "licenziya-add";
        licenziyaRepository.save(licenziya);
        return "redirect:/licenziya";
    }

    @GetMapping("/tovar/{idTovar}/remove")
    public String tovarDelete(@PathVariable(value = "idTovar") long idTovar, Model model){
        Tovar tovar = tovarRepository.findById(idTovar).orElseThrow();
        tovarRepository.delete(tovar);
        return "redirect:/";
    }

    @GetMapping("/tovar/{idTovar}/red")
    public String tovarRed(@PathVariable(value = "idTovar") long idTovar, Tovar tovar, Model model){
        if(!tovarRepository.existsById(idTovar)){
            return "redirect:/";
        }
        Optional<Tovar> tov = tovarRepository.findById(idTovar);
        ArrayList<Tovar> res = new ArrayList<>();
        tov.ifPresent(res::add);
        model.addAttribute("tovar1", res);
        return "tovar-red";
    }

    @PostMapping("/tovar/{idTovar}/red")
    public String tovarUpdate(@PathVariable(value = "idTovar") long idTovar, @Valid Tovar tovar,
                                     BindingResult bindingResult, @RequestParam String nameTovar, Model model){
        if(bindingResult.hasErrors())
        {
            ArrayList<Tovar> res = new ArrayList<>();
            res.add(tovar);
            model.addAttribute("tovar1", res);

            return "tovar-red";
        }
        Optional<Tovar> tov = tovarRepository.findById(idTovar);
        tovar.setProdano(true);
        tovar.setLicenziya(tov.get().getLicenziya());
        tovar.setPoctsvchik(tov.get().getPoctsvchik());
        tovarRepository.save(tovar);
        return "redirect:/";
    }

    @GetMapping("/licenziya/{idLicenziya}/red")
    public String licenziyaRed(@PathVariable(value = "idLicenziya") long idLicenziya, Licenziya licenziya, Model model){
        if(!licenziyaRepository.existsById(idLicenziya)){
            return "redirect:/licenziya";
        }
        Optional<Licenziya> lic = licenziyaRepository.findById(idLicenziya);
        ArrayList<Licenziya> res = new ArrayList<>();
        lic.ifPresent(res::add);
        model.addAttribute("licenziya1", res);
        return "licenziya-red";
    }

    @PostMapping("/licenziya/{idLicenziya}/red")
    public String licenziyaUpdate(@PathVariable(value = "idLicenziya") long idLicenziya, @Valid Licenziya licenziya,
                                     BindingResult bindingResult, @RequestParam String nameTov, Model model){
        if(bindingResult.hasErrors()){
            ArrayList<Licenziya> res = new ArrayList<>();
            res.add(licenziya);
            model.addAttribute("licenziya1", res);
            return "licenziya-red";
        }
            licenziyaRepository.save(licenziya);
            return "redirect:/licenziya";

    }

    @GetMapping("/licenziya/{idLicenziya}/remove")
    public String licenziyaDelete(@PathVariable(value = "idLicenziya") long idLicenziya, Model model){
        Licenziya licenziya = licenziyaRepository.findById(idLicenziya).orElseThrow();
        licenziyaRepository.delete(licenziya);
        return "redirect:/licenziya";
    }


    @GetMapping("/poctsvchik/{idPoctsvchik}/red")
    public String poctsvchikRed(@PathVariable(value = "idPoctsvchik") long idPoctsvchik, Poctsvchik poctsvchik, Model model){
        if(!poctsvchikRepository.existsById(idPoctsvchik)){
            return "redirect:/poctsvchik";
        }
        Optional<Poctsvchik> pos = poctsvchikRepository.findById(idPoctsvchik);
        ArrayList<Poctsvchik> res = new ArrayList<>();
        pos.ifPresent(res::add);
        model.addAttribute("poctsvchik1", res);
        return "poctsvchik-red";
    }

    @PostMapping("/poctsvchik/{idPoctsvchik}/red")
    public String poctsvchikUpdate(@PathVariable(value = "idPoctsvchik") long idPoctsvchik, @Valid Poctsvchik poctsvchik,
                                  BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            ArrayList<Poctsvchik> res = new ArrayList<>();
            res.add(poctsvchik);
            model.addAttribute("poctsvchik1", res);
            return "poctsvchik-red";
        }
        poctsvchikRepository.save(poctsvchik);
        return "redirect:/poctsvchik";

    }

    @GetMapping("/poctsvchik/{idPoctsvchik}/remove")
    public String poctsvchikDelete(@PathVariable(value = "idPoctsvchik") long idPoctsvchik, Model model){
        Poctsvchik poctsvchik = poctsvchikRepository.findById(idPoctsvchik).orElseThrow();
        poctsvchikRepository.delete(poctsvchik);
        return "redirect:/poctsvchik";
    }

    @GetMapping("/tovar/{nameTovar}/prod")
    public String tovarProd(@PathVariable(value = "nameTovar") String nameTovar, Model model){
        Tovar tovar = tovarRepository.findByNameTovar(nameTovar);
        tovar.setProdano(false);
        tovarRepository.save(tovar);
        Iterable<Tovar> tovars = tovarRepository.findAll();
        model.addAttribute("tovars", tovars);
        return "redirect:/";
    }

    @GetMapping("/check")
    public String Check(Model model){
        Iterable<Tovar> tovars = tovarRepository.findAll();
        model.addAttribute("tovars", tovars);
        return "check";
    }

    @GetMapping("/check/{nameTovar}/vozvrat")
    public String tovarVozvrat(@PathVariable(value = "nameTovar") String nameTovar, Model model){
        Tovar tovar = tovarRepository.findByNameTovar(nameTovar);
        tovar.setProdano(true);
        tovarRepository.save(tovar);
        Iterable<Tovar> tovars = tovarRepository.findAll();
        model.addAttribute("tovars", tovars);
        Vozvrat vozvrat = new Vozvrat(nameTovar, tovar.getCostTovar(), tovar.getKolvoTovar());
        vozvratRepository.save(vozvrat);
        return "redirect:/check";
    }

    @GetMapping("/vozvrat")
    public String Vozvrat(Model model){
        Iterable<Vozvrat> vozvrats = vozvratRepository.findAll();
        model.addAttribute("vozvrats", vozvrats);
        return "vozvrat";
    }

    @GetMapping("/employee")
    public String Employee(Model model){
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

    @GetMapping("/employee/{id}/remove")
    public String employeeDelete(@PathVariable(value = "id") long id, Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/employee";
    }

    @GetMapping("/nakladnaya")
    public String MainNakladnaya(Model model){
        Iterable<Nakladnaya> nakladnayas = nakladnayaRepository.findAll();
        model.addAttribute("nakladnayas", nakladnayas);
        Iterable<Tovar> tovars = tovarRepository.findAll();
        model.addAttribute("tovars", tovars);
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "nakladnaya";
    }

    @PostMapping("/nakladnaya/add")
    public String nakladnayaPostAdd(Nakladnaya nakladnaya, @RequestParam String nameTovar, @RequestParam String nameSostav,
                              @RequestParam String date, Model model, BindingResult bindingResult){
        if(date==""){
            Iterable<Nakladnaya> nakladnayas = nakladnayaRepository.findAll();
            model.addAttribute("nakladnayas", nakladnayas);
            Iterable<Tovar> tovars = tovarRepository.findAll();
            model.addAttribute("tovars", tovars);
            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("employees", employees);
            return "nakladnaya";
        }
        List<Nakladnaya> res = nakladnayaRepository.findByNameTovar(nameTovar);
        if(res.size() > 0){
            ObjectError error = new ObjectError("nameTovar", "Такое название уже есть");
            bindingResult.addError(error);
            return "redirect:/nakladnaya";
        }
        else {
                nakladnaya = new Nakladnaya(nameTovar, nameSostav, date);
                nakladnayaRepository.save(nakladnaya);

            return "redirect:/nakladnaya";
        }
    }

    @GetMapping("/nakladnaya/{idNakladnaya}/remove")
    public String nakladnayaDelete(@PathVariable(value = "idNakladnaya") long idNakladnaya, Model model){
        Nakladnaya nakladnaya = nakladnayaRepository.findById(idNakladnaya).orElseThrow();
        nakladnayaRepository.delete(nakladnaya);
        return "redirect:/nakladnaya";
    }

    @GetMapping("/licenziya/filter")
    public String blogFilter(Model model){
        return "licenziya-filter";
    }

    @PostMapping("/licenziya/filter/result")
    public String blogResult(@RequestParam String nameTov, Model model){
        List<Licenziya> result = licenziyaRepository.findByNameTovContaining(nameTov);
        model.addAttribute("result", result);
        return "/licenziya-filter";
    }

    @GetMapping("/poctsvchik/filter")
    public String poctsvchikFilter(Model model){
        return "poctsvchik-filter";
    }

    @PostMapping("/poctsvchik/filter/result")
    public String poctsvchikResult(@RequestParam String namePoctsvchik, Model model){
        List<Poctsvchik> result = poctsvchikRepository.findByNamePoctsvchikContaining(namePoctsvchik);
        model.addAttribute("result", result);
        return "/poctsvchik-filter";
    }

    @GetMapping("/employee/filter")
    public String employeeFilter(Model model){
        return "employee-filter";
    }

    @PostMapping("/employee/filter/result")
    public String employeeResult(@RequestParam String firstName, Model model){
        List<Employee> result = employeeRepository.findByFirstNameContaining(firstName);
        model.addAttribute("result", result);
        return "/employee-filter";
    }

    @GetMapping("/nakladnaya/filter")
    public String nakladnayaFilter(Model model){
        return "nakladnaya-filter";
    }

    @PostMapping("/nakladnaya/filter/result")
    public String nakladnayaResult(@RequestParam String nameTovar, Model model){
        List<Nakladnaya> nakladnayas = nakladnayaRepository.findByNameTovarContaining(nameTovar);
        model.addAttribute("nakladnayas", nakladnayas);
        return "nakladnaya-filter";
    }

    @GetMapping("/tovar/filter")
    public String tovarFilter(Model model){
        return "tovar-filter";
    }

    @PostMapping("/tovar/filter/result")
    public String tovarResult(@RequestParam String nameTovar, Model model){
        List<Tovar> tovars = tovarRepository.findByNameTovarContaining(nameTovar);
        model.addAttribute("tovars", tovars);
        return "tovar-filter";
    }

    @GetMapping("/check/filter")
    public String checkFilter(Model model){
        return "chek-filter";
    }

    @PostMapping("/check/filter/result")
    public String checkResult(@RequestParam String nameTovar, Model model){
        List<Tovar> tovars = tovarRepository.findByNameTovarContaining(nameTovar);
        model.addAttribute("tovars", tovars);
        return "chek-filter";
    }

    @GetMapping("/vozvrat/filter")
    public String vozvratFilter(Model model){
        return "vozvrat-filter";
    }

    @PostMapping("/vozvrat/filter/result")
    public String vozvratResult(@RequestParam String nameTovar, Model model){
        List<Vozvrat> vozvrats = vozvratRepository.findByNameTovarContaining(nameTovar);
        model.addAttribute("vozvrats", vozvrats);
        return "vozvrat-filter";
    }
}
