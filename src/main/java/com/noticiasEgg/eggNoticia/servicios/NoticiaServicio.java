package com.noticiasEgg.eggNoticia.servicios;


import com.noticiasEgg.eggNoticia.entidades.Noticia;
import com.noticiasEgg.eggNoticia.excepciones.MiExcepcion;
import com.noticiasEgg.eggNoticia.repositorios.NoticiaRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author scandiani
 */
@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepo repo;

    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiExcepcion {

        Noticia noticia = new Noticia();
        if (titulo == null || titulo.isEmpty()) {
            throw new MiExcepcion("El titulo no puede ser nulo o vacio.");
        }

        if (cuerpo == null || cuerpo.isEmpty()) {
            throw new MiExcepcion("El cuerpo no puede ser nulo o vacio.");
        }
        noticia.setCuerpo(cuerpo);
        noticia.setTitulo(titulo);

        repo.save(noticia);
    }

    
    public void modificarNoticia(String id, String titulo, String cuerpo) {

        Optional<Noticia> respuesta = repo.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setCuerpo(cuerpo);
            noticia.setTitulo(titulo);

            repo.save(noticia);
        }
    }

    @Transactional(readOnly = true)
    public List<Noticia> listarNoticias() {
        List<Noticia> noticias = new ArrayList();

        noticias = repo.findAll();
        return noticias;
    }

    @Transactional(readOnly = true)
    public Noticia getOne(String id) {
        return repo.getOne(id);
    } 

}
