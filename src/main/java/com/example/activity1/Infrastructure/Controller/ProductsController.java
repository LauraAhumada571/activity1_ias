package com.example.activity1.Infrastructure.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProduct(){
        return "Este es el método para consultar todos los productos";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct(){
        return "Este es un método para crear un producto";
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public String updateProduct(){
        return "Este es un método para actualizar un producto";
    }

    @RequestMapping(value = "/products", method = RequestMethod.DELETE)
    public String deleteProduct(){
        return "Este es un método para eliminar un producto";
    }

}
