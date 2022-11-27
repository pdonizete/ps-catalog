package net.paulosoft.pscatalog.resources;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.paulosoft.pscatalog.entities.Category;
import net.paulosoft.pscatalog.services.CategoryService;
@RestController
@RequestMapping("/categories")
public class CategoryResource {
	@Autowired
	private CategoryService service;
	@GetMapping
public ResponseEntity<List<Category>> Mostrar() {
List <Category> lista=service.listarTudo();
	return ResponseEntity.ok().body(lista);
}
}
