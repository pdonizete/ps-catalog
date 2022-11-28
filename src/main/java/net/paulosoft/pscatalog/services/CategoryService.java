package net.paulosoft.pscatalog.services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.paulosoft.pscatalog.dto.CategoryDTO;
import net.paulosoft.pscatalog.entities.Category;
import net.paulosoft.pscatalog.repositories.CategoryRepository;
@Service
public class CategoryService {
	@Autowired
private CategoryRepository repository;
    @Transactional(readOnly = true)

public List <CategoryDTO> listarTudo() {
	List <Category> list=repository.findAll();
	return list.stream().map(CategoryDTO::new).collect(Collectors.toList());


}
    @Transactional(readOnly = true)    
    public CategoryDTO findById(Long id) {
   
    
        Optional<Category> obj = repository.findById(id);
        Category entity=obj.get();
        return new CategoryDTO(entity);

    }
}
