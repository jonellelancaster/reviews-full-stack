package org.wecancodeit.reviewsfullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.reviewsfullstack.controllers.CategoryController;
import org.wecancodeit.reviewsfullstack.models.Category;
import org.wecancodeit.reviewsfullstack.repositories.CategoryRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryMvcTest {
	@Resource
	private MockMvc mvc;

	@MockBean
	private CategoryRepository categoryRepo;

	@Mock
	Category category;
	@Mock
	Category category2;



	@Test
	public void shouldRouteToSinglecategory() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}

	@Test
	public void shouldBeOkForSinglecategory() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());

	}

	@Test
	public void shouldBeNotOkForSinglecategory() throws Exception {
		long arbitraryCategoryId = 0;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleCategoryIntoModel() throws Exception {
		when(categoryRepo.findById(2L)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=2")).andExpect(model().attribute("categories", is(category)));
	}

	@Test
	public void shouldBeOkForAllCategories() throws Exception {
		mvc.perform(get("/categories")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToAllCategoriesView() throws Exception {
		mvc.perform(get("/categories")).andExpect(view().name(is("categories")));

	}

	@Test
	public void shouldPutAllcategoriesIntoModel() throws Exception {
		Collection<Category> allCategories = Arrays.asList(category, category2);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/categories")).andExpect(model().attribute("categories", is(allCategories)));
	}

}
