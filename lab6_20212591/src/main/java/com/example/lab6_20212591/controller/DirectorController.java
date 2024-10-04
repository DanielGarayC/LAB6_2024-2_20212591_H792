package com.example.lab6_20212591.controller;

import com.example.lab6_20212591.entity.Director;
import com.example.lab6_20212591.entity.Pelicula;
import com.example.lab6_20212591.repository.DirectorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller

public class DirectorController {

    private final DirectorRepository directorRepository;

    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @GetMapping("/Director/lista")
    public String listarDirectores(Model model) {
        model.addAttribute("lista", directorRepository.findAll());
        return "Directores/listaDirectores";
    }

    @GetMapping("/Director/crear")
    public String crearDirector(@ModelAttribute("director") Director director) {
        return "Directores/formDirector";
    }

    @GetMapping("/Director/editar")
    public String editarDirector(@ModelAttribute("director") Director director, Model model,
                                      @RequestParam("id") int id) {

        Optional<Director> optionalDirector = directorRepository.findById(id);

        if (optionalDirector.isPresent()) {
            director = optionalDirector.get();
            model.addAttribute("director", director);
            return "Directores/formDirector";
        } else {
            return "Directores/listaDirectores";
        }
    }

    @PostMapping("/Director/guardar")
    public String guardarDirector(@ModelAttribute("director") @Valid Director director, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        return "redirect:/Director/lista";
    }
}
