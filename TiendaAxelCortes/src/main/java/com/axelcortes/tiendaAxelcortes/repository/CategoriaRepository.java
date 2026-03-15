/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.axelcortes.tiendaAxelcortes.repository;

import com.axelcortes.tiendaAxelcortes.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author User
 */

public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{
    public List<Categoria> findByActivoTrue();

}
