/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasEgg.eggNoticia.repositorios;

import com.noticiasEgg.eggNoticia.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author franc
 */
@Repository
public interface NoticiaRepo extends JpaRepository<Noticia, String> {
    
    
}
