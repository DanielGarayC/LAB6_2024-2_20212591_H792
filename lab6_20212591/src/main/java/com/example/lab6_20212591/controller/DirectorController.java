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

    @GetMapping("/Director/ver")
    public String verDetallesDirector(@RequestParam("id") int id) {
        return "Directores/detallesDirector";
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
        if(bindingResult.hasErrors()){
            if(director.getId() != 0){
                Optional<Director> optionalDirector = directorRepository.findById(director.getId());
                if(optionalDirector.isPresent()){
                    director = optionalDirector.get();
                    model.addAttribute("director",director);
                }
            }
            return "Directores/formDirector";
        }else {
            try {
                if (director.getId() == 0) {
                    attr.addFlashAttribute("msg", "Director creado exitosamente");
                } else {
                    attr.addFlashAttribute("msg", "Director actualizado exitosamente");
                }
                directorRepository.save(director);
                return "redirect:/Director/lista";
            } catch (Exception e) {
                attr.addFlashAttribute("error", "Ocurrió un error al gestionar el director");
                e.printStackTrace();
            }
        }
        return "redirect:/Director/lista";
    }

    @GetMapping("/Director/borrar")
    public String borrarDirector(Model model, @RequestParam("id") int id, RedirectAttributes attr) {

        Optional<Director> optionalDirector = directorRepository.findById(id);

        if (optionalDirector.isPresent()) {
            directorRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Director borrado exitosamente");
        }
        return "redirect:/Director/lista";

    }
}
