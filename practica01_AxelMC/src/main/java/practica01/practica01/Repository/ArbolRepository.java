/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica01.Repository;

import java.util.List;
import practica01.practica01.domain.Arbol;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Axel Martinez Cortes
 */

@Repository
public interface ArbolRepository extends JpaRepository<Arbol, Long> {

    
    
}
    
    
    
   
