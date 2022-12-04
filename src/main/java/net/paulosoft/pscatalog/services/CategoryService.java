package net.paulosoft.pscatalog.services;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.paulosoft.pscatalog.dto.CategoryDTO;
import net.paulosoft.pscatalog.entities.Category;
import net.paulosoft.pscatalog.repositories.CategoryRepository;
import net.paulosoft.pscatalog.services.exceptions.DatabaseException;

@Service
public class CategoryService {
	@Autowired
private CategoryRepository repository;
    @Transactional(readOnly = true)

public Page<CategoryDTO> listarTudoPaginado(PageRequest pageRequest) {
	Page <Category> list=repository.findAll(pageRequest);
    return list.map(x -> new CategoryDTO(x));

}
    @Transactional(readOnly = true)    
    public CategoryDTO findById(Long id) {
   
    
        Optional<Category> obj = repository.findById(id);
        Category entity=obj.orElseThrow(()->new EntityNotFoundException("Entity not found"));
        return new CategoryDTO(entity);

    }
    @Transactional
    public CategoryDTO insert (CategoryDTO dto) {
    Category entity=new Category();
    entity.setName(dto.getName());
    entity=repository.save(entity);
    return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
            try {
                    Category entity = repository.getOne(id);
                    entity.setName(dto.getName());
                    entity = repository.save(entity);
                    return new CategoryDTO(entity);
            }
            catch (javax.persistence.EntityNotFoundException  e) {
                    throw new EntityNotFoundException("Id not found " + id);
            }
    }
    public void delete(Long id) {
        try {
                repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
                throw new EntityNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
                throw new DatabaseException("Integrity violation");
        }
}

}
