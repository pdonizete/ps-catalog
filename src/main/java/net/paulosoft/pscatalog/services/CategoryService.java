package net.paulosoft.pscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.paulosoft.pscatalog.entities.Category;
import net.paulosoft.pscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
private CategoryRepository repository;
    @Transactional(readOnly = true)

public List <Category> listarTudo() {
	List <Category> list=repository.findAll();
	return list;
}
}
