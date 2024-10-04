package com.example.lab6_20212591.controller;

import com.example.lab6_20212591.entity.Pelicula;
import com.example.lab6_20212591.repository.PeliculaRepository;
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

public class PeliculaController {

    private final PeliculaRepository peliculaRepository;

    public PeliculaController(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @GetMapping("/Pelicula/lista")
    public String listarPeliculas(Model model) {
        model.addAttribute("lista", peliculaRepository.findAll());
        return "Peliculas/listaPeliculas";
    }

    @GetMapping("/Pelicula/ver")
    public String verDetallesPelicula(@RequestParam("id") int id) {
        return "Directores/detallesPelicula";
    }

    @GetMapping("/Pelicula/crear")
    public String crearPelicula(@ModelAttribute("pelicula") Pelicula pelicula) {
        return "Peliculas/formPelicula";
    }

    @GetMapping("/Pelicula/editar")
    public String editarPelicula(@ModelAttribute("pelicula") Pelicula pelicula, Model model,
                                      @RequestParam("id") int id) {

        Optional<Pelicula> optionalPelicula = peliculaRepository.findById(id);

        if (optionalPelicula.isPresent()) {
            pelicula = optionalPelicula.get();
            model.addAttribute("pelicula", pelicula);
            return "Peliculas/formPelicula";
        } else {
            return "Peliculas/listaPeliculas";
        }
    }

    @PostMapping("/Pelicula/guardar")
    public String guardarPelicula(@ModelAttribute("pelicula") @Valid Pelicula pelicula, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if(bindingResult.hasErrors()){
            if(pelicula.getId() != 0){
                Optional<Pelicula> optionalPelicula = peliculaRepository.findById(pelicula.getId());
                if(optionalPelicula.isPresent()){
                    pelicula = optionalPelicula.get();
                    model.addAttribute("pelicula",pelicula);
                }
            }
            return "Peliculas/formPelicula";
        }else {
            try {
                if (pelicula.getId() == 0) {
                    attr.addFlashAttribute("msg", "Pelicula creada exitosamente");
                } else {
                    attr.addFlashAttribute("msg", "Pelicula actualizada exitosamente");
                }
                peliculaRepository.save(pelicula);
                return "redirect:/Pelicula/lista";
            } catch (Exception e) {
                attr.addFlashAttribute("error", "Ocurrió un error al gestionar la pelicula");
                e.printStackTrace();
            }
        }
        return "redirect:/Pelicula/lista";
    }

    @GetMapping("/Pelicula/borrar")
    public String borrarPelicula(Model model, @RequestParam("id") int id, RedirectAttributes attr) {

        Optional<Pelicula> optionalPelicula = peliculaRepository.findById(id);

        if (optionalPelicula.isPresent()) {
            peliculaRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Pelicula borrada exitosamente");
        }
        return "redirect:/Pelicula/lista";

    }
}
