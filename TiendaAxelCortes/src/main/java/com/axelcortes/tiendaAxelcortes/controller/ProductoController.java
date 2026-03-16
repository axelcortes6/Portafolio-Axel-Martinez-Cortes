/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axelcortes.tiendaAxelcortes.controller;

import com.axelcortes.tiendaAxelcortes.domain.Producto;
import com.axelcortes.tiendaAxelcortes.service.CategoriaService;
import com.axelcortes.tiendaAxelcortes.service.ProductoService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Se inyecta CategoriaService porque al crear o editar un producto necesitamos la lista de categorías
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        
        // Aquí traemos las categorías activas para el combobox (dropdown) de agregar producto
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        
        return "producto/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Producto producto, Model model) {
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        return "producto/modifica"; 
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, @RequestParam MultipartFile imagenFile, RedirectAttributes redirectAttributes) {
        productoService.save(producto, imagenFile);
        redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/producto/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idProducto, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            productoService.delete(idProducto);
        } catch (IllegalArgumentException e) {
            titulo = "error"; 
            detalle = "producto.error01"; // Cambiado a error de producto
        } catch (IllegalStateException e) {
            titulo = "error"; 
            detalle = "producto.error02"; // Cambiado a error de producto
        } catch (Exception e) {
            titulo = "error"; 
            detalle = "producto.error03"; // Cambiado a error de producto
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String modificar(@PathVariable("idProducto") Integer idProducto, Model model, RedirectAttributes redirectAttributes) {
        Optional<Producto> productoOpt = productoService.getProducto(idProducto);
        if (productoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("producto.error01", null, Locale.getDefault()));
            return "redirect:/producto/listado";
        }
        
        model.addAttribute("producto", productoOpt.get());
        
        // Al modificar también ocupamos cargar las categorías para el combobox
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        
        return "producto/modifica"; 
    }
}