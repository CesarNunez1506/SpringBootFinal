package com.vatta.controller;

import com.vatta.dto.ProductDTO;
import com.vatta.dto.CategoryDTO; // Asegúrate de tener un DTO para las categorías
import com.vatta.service.AdminService;
import com.vatta.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final CategoryService categoryService;

    public AdminController(AdminService adminService, CategoryService categoryService) {
        this.adminService = adminService;
        this.categoryService = categoryService;
    }

    // Vista del Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    // Lista de productos
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", adminService.getAllProducts());
        return "admin/products/list";
    }

    // Crear un nuevo producto
    @GetMapping("/products/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO()); // Para crear un nuevo producto
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/products/create";
    }

    // Guardar un nuevo producto
    @PostMapping("/products")
    public String createProduct(@ModelAttribute("productDTO") ProductDTO productDTO) {
        adminService.createProduct(productDTO);
        return "redirect:/admin/products"; // Redirige a la lista de productos
    }

    // Editar un producto
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        ProductDTO productDTO = adminService.getProductDTOById(id);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/products/edit";
    }

    // Actualizar un producto
    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("productDTO") ProductDTO productDTO) {
        adminService.updateProduct(id, productDTO);
        return "redirect:/admin/products";
    }

    // Lista de categorías
    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/categories/list"; // Asegúrate de tener esta vista
    }

    // Crear una nueva categoría
    @GetMapping("/categories/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO()); // Asegúrate de tener un DTO para la categoría
        return "admin/categories/create"; // Asegúrate de tener esta vista
    }

    // Guardar una nueva categoría
    @PostMapping("/categories")
    public String createCategory(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO); // Asegúrate de tener este método en CategoryService
        return "redirect:/admin/categories"; // Redirige a la lista de categorías
    }

    // Editar una categoría
    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        CategoryDTO categoryDTO = categoryService.getCategoryDTOById(id); // Asegúrate de tener este método
        model.addAttribute("categoryDTO", categoryDTO);
        return "admin/categories/edit"; // Asegúrate de tener esta vista
    }

    // Actualizar una categoría
    @PostMapping("/categories/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO); // Asegúrate de tener este método en CategoryService
        return "redirect:/admin/categories"; // Redirige a la lista de categorías
    }

    // Eliminar una categoría
    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id); // Asegúrate de tener este método en CategoryService
        return "redirect:/admin/categories"; // Redirige a la lista de categorías
    }
}