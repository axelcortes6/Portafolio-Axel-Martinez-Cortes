/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica01.ArbolService;

/**
 *
 * @author Axel Martinez Cortes 19/02/2026
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practica01.practica01.Repository.ArbolRepository;
import practica01.practica01.domain.Arbol;


@Service 
public class ArbolServiceImpl implements ArbolService {

    @Autowired
    private ArbolRepository arbolRepository;

    @Override
    @Transactional(readOnly = true) 
    public List<Arbol> getArboles() {
        return arbolRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Arbol getArbol(Arbol arbol) {
        return arbolRepository.findById(arbol.getIdArbol()).orElse(null);
    }

    @Override
    @Transactional 
    public void save(Arbol arbol) {
        arbolRepository.save(arbol);
    }
    @Override
    @Transactional
    public void delete(Arbol arbol) {
        arbolRepository.delete(arbol);
        
        
    }
}



