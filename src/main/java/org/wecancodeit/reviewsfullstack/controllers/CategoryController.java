package org.wecancodeit.reviewsfullstack.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.reviewsfullstack.models.Category;
import org.wecancodeit.reviewsfullstack.notfound.CategoryNotFoundException;
import org.wecancodeit.reviewsfullstack.repositories.CategoryRepository;

@Controller

public class CategoryController {

	
	@Resource
	CategoryRepository categoryRepo;
	
	
	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value = "id") long id, Model model) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(id);
		if (category.isPresent()) {
			model.addAttribute("categories", category.get());
			return "category";
		}
		throw new CategoryNotFoundException();
	}

	@RequestMapping("/categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return ("categories");

	}
}
