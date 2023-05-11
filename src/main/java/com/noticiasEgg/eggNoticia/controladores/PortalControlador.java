/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasEgg.eggNoticia.controladores;

import com.noticiasEgg.eggNoticia.entidades.Noticia;
import com.noticiasEgg.eggNoticia.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author franc
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private NoticiaServicio notiService;
    //Muestra el index de la pagina.
    @GetMapping("/")
    public String index(ModelMap model){
        List<Noticia> noticias = notiService.listarNoticias();
        model.put("noticiaLista", noticias);
        return "index.html";
    }
    
}
