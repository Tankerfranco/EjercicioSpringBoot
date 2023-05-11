/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasEgg.eggNoticia.controladores;

import com.noticiasEgg.eggNoticia.entidades.Noticia;
import com.noticiasEgg.eggNoticia.excepciones.MiExcepcion;
import com.noticiasEgg.eggNoticia.repositorios.NoticiaRepo;
import com.noticiasEgg.eggNoticia.servicios.NoticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author franc
 */
@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    @Autowired
    private NoticiaRepo repo;
    //Muestra el formulario
    @GetMapping("/registrar")
    public String registrar() {
        return "noticia_form.html";
    }
    //Obtiene los datos del formulario
    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) {
        try {
            noticiaServicio.crearNoticia(titulo, cuerpo);
        } catch (MiExcepcion ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "noticia_form.html";
        }
        return "redirect:/";
    }
    //Muestra la noticia dependiendo de su id
    @GetMapping("/mostrarNoticia/{id}")
    public String modificarPrueba(@PathVariable String id, ModelMap model) {
        Noticia noticia = noticiaServicio.getOne(id);
        model.put("noticia", noticia);
        return "noticia.html";
    }
    //Muestra la lista de noticas
    @GetMapping("/lista")
    public String lista(ModelMap model) {
        List<Noticia> noticia = noticiaServicio.listarNoticias();
        model.put("listaNoticias", noticia);
        return "lista_noticias.html";
    }
    //Muestra la pagina para modificar
    @GetMapping("/modificar/{id}")
    public String modificarNoticia(@PathVariable String id, ModelMap model) {
        model.put("noticia", noticiaServicio.getOne(id));
        return "modificarNoticia.html";
    }
    //Obtencion de los nuevos datos de la noticia
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo) {
        noticiaServicio.modificarNoticia(id, titulo, cuerpo);
        return "redirect:/";
    }
    //Borrado de items de la lista
    @GetMapping("/delete/{id}")
    public String borrar(@PathVariable String id){
        repo.deleteById(id);
        return "redirect:/noticia/lista";
    }
 

}
