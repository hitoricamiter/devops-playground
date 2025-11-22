package ru.zaikin.microb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.zaikin.microb.entity.Product;
import ru.zaikin.microb.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Product> all() {
        return repo.findAll();
    }

    @GetMapping("/seed")
    public List<Product> seed() {
        repo.save(new Product("Apple", "Red apple", 0.5));
        repo.save(new Product("Bread", "Brown bread", 1.2));
        repo.save(new Product("Milk", "1L milk", 0.9));
        return repo.findAll();
    }

    @DeleteMapping("/clear")
    public String clear() {
        repo.deleteAll();
        return "All products deleted!";
    }

    @GetMapping("/hi")
    public String getHi() {
        String url = "http://backend-svc-2:8082/welcome";
        ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class);
        return response.getBody();
    }
}
