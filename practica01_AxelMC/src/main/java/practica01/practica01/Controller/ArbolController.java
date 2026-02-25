/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica01.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import practica01.practica01.ArbolService.ArbolService;
import practica01.practica01.domain.Arbol;

/**
 *
 * @author Axel Martinez
 */
@Controller
@RequestMapping("/arbol")
public class ArbolController {
    
    @Autowired
    private ArbolService arbolService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var arboles = arbolService.getArboles();
        model.addAttribute("arboles", arboles);
        return "/arbol/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Arbol arbol) {
       
        arbolService.save(arbol);
        return "redirect:/arbol/listado";
    }

    @GetMapping("/eliminar/{idArbol}")
    public String eliminar(Arbol arbol) {
        arbolService.delete(arbol);
        return "redirect:/arbol/listado";
    }
    
    @GetMapping("/modificar/{idArbol}")
    public String modificar(Arbol arbol, Model model) {
        arbol = arbolService.getArbol(arbol);
        model.addAttribute("arbol", arbol);
        return "/arbol/modifica";
        
        
        
    }
    
    
}


