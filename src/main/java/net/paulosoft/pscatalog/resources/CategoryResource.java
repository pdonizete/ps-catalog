package net.paulosoft.pscatalog.resources;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.paulosoft.pscatalog.dto.CategoryDTO;
import net.paulosoft.pscatalog.services.CategoryService;
@RestController
@RequestMapping("/categories")
public class CategoryResource {
	@Autowired
	private CategoryService service;
	@GetMapping
public ResponseEntity<List<CategoryDTO>> Mostrar() {
List <CategoryDTO> lista=service.listarTudo();
	return ResponseEntity.ok().body(lista);
}
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
		CategoryDTO dto = service.findById(id);
return ResponseEntity.ok().body(dto);
	}
@PostMapping
public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
	dto = service.insert(dto);
	URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);

}

}
