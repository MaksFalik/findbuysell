package com.example.findbuysell.controllers;

import com.example.findbuysell.models.Product;
import com.example.findbuysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    // Обработка GET-запроса для отображения списка продуктов.
    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        // Получение списка продуктов с учетом фильтра по наименованию (если указано).
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("products", productService.listProducts(title));
        return "products";
    }
    // Обработка GET-запроса для отображения информации о конкретном продукте.
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        // Получение информации о продукте по его ID.
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }
    // Обработка POST-запроса для создания нового продукта.
    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Product product, Principal principal) throws IOException, IOException {
        // Сохранение нового продукта и его изображений.
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        // Удаление продукта по указанному ID.
        productService.deleteProduct(id);
        return "redirect:/";
    }
}