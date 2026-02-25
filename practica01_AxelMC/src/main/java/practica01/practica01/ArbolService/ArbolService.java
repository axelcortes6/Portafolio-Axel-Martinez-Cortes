/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package practica01.practica01.ArbolService;
import java.util.List;
import practica01.practica01.domain.Arbol;

/**
 *
 * @author Axel Martinez
 */

public interface ArbolService {
    public List<Arbol> getArboles();
    public Arbol getArbol(Arbol arbol);
    public void save(Arbol arbol);
    public void delete(Arbol arbol);
    
    
    
    
}




