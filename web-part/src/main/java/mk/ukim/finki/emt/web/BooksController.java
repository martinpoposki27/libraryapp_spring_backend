package mk.ukim.finki.emt.web;


import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.service.AuthorService;
import mk.ukim.finki.emt.service.BookService;
import mk.ukim.finki.emt.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final CountryService countryService;
    private final AuthorService authorService;

    public BooksController(BookService bookService, CountryService countryService, AuthorService authorService) {
        this.bookService = bookService;
        this.countryService = countryService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

//    @DeleteMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable Long id) {
//        this.productService.deleteById(id);
//        return "redirect:/products";
//    }
//
//    @GetMapping("/edit-form/{id}")
//    public String editProductPage(@PathVariable Long id, Model model) {
//        if (this.productService.findById(id).isPresent()) {
//            Product product = this.productService.findById(id).get();
//            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
//            List<Category> categories = this.categoryService.listCategories();
//            model.addAttribute("manufacturers", manufacturers);
//            model.addAttribute("categories", categories);
//            model.addAttribute("product", product);
//            model.addAttribute("bodyContent", "add-product");
//            return "master-template";
//        }
//        return "redirect:/products?error=ProductNotFound";
//    }
//
//    @GetMapping("/add-form")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String addProductPage(Model model) {
//        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
//        List<Category> categories = this.categoryService.listCategories();
//        model.addAttribute("manufacturers", manufacturers);
//        model.addAttribute("categories", categories);
//        model.addAttribute("bodyContent", "add-product");
//        return "master-template";
//    }
//
//    @PostMapping("/add")
//    public String saveProduct(
//            @RequestParam(required = false) Long id,
//            @RequestParam String name,
//            @RequestParam Double price,
//            @RequestParam Integer quantity,
//            @RequestParam Long category,
//            @RequestParam Long manufacturer) {
//        if (id != null) {
//            this.productService.edit(id, name, price, quantity, category, manufacturer);
//        } else {
//            this.productService.save(name, price, quantity, category, manufacturer);
//        }
//        return "redirect:/products";
//    }
}
