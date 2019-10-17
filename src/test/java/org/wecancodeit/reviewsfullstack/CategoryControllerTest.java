package org.wecancodeit.reviewsfullstack;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wecancodeit.reviewsfullstack.controllers.CategoryController;
import org.wecancodeit.reviewsfullstack.controllers.ReviewController;
import org.wecancodeit.reviewsfullstack.models.Category;
import org.wecancodeit.reviewsfullstack.notfound.CategoryNotFoundException;
import org.wecancodeit.reviewsfullstack.repositories.CategoryRepository;

public class CategoryControllerTest {
	
	@InjectMocks
	private CategoryController underTest;

	@Mock
	private Category category;
	
	@Mock
	private Category category2;
	
	@Mock
	private CategoryRepository categoryRepo;
	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
	}
		@Test
		public void shouldAddSinglecategoryToModel() throws CategoryNotFoundException{
			long arbituaryCategoryId = 1;
			when(categoryRepo.findById(arbituaryCategoryId)).thenReturn(Optional.of(category));

			underTest.findOneCategory(arbituaryCategoryId, model);
			verify(model).addAttribute("categories", category);
		}
		
		
		@Test
		public void shouldAddAllcategoriesToModel() {
			Collection<Category> allcategories =Arrays.asList(category, category2);
			when(categoryRepo.findAll()).thenReturn(allcategories);
			underTest.findAllCategories(model);
			verify(model).addAttribute("categories", allcategories);
		}
			
		
	
	
	
}
