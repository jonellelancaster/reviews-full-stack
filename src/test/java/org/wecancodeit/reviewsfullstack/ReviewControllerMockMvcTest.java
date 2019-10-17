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
import org.wecancodeit.reviewsfullstack.controllers.ReviewController;
import org.wecancodeit.reviewsfullstack.models.Category;
import org.wecancodeit.reviewsfullstack.models.Review;
import org.wecancodeit.reviewsfullstack.repositories.CategoryRepository;
import org.wecancodeit.reviewsfullstack.repositories.ReviewRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;

	@Mock
	private Review review;
	@Mock
	private Review review2;
	
	@Mock
	Category category;
	@Mock
	Category category2;

	@Test
	public void shouldRouteToSingleReview() throws Exception {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
		
	}
	@Test
	public void shouldBeOkForSingleReview()throws Exception {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	@Test
	public void shouldBeNotOkForSingleReview()throws Exception {
		long arbitraryReviewId = 0;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	
	}
	
	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception{
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews", is(review)));
	}
	@Test
	public void shouldRouteToAllReviewsView() throws Exception{
		mvc.perform(get("/reviews")).andExpect(view().name(is("reviews")));
		
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception{
		mvc.perform(get("/reviews")).andExpect(status().isOk());
	}
	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception{
		Collection<Review> allReviews = Arrays.asList(review, review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
//	@Test
//	public void shouldRouteToSinglecategory() throws Exception {
//		long arbitraryCategoryId = 1;
//		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
//		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
//	}
//	@Test
//	public void shouldBeOkForSinglecategory()throws Exception {
//		long arbitraryCategoryId = 1;
//		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
//		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
//	
//	}
//	@Test
//	public void shouldBeNotOkForSinglecategory()throws Exception {
//		long arbitraryCategoryId = 0;
//		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
//		mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
//	}
//	@Test
//	public void shouldPutSingleCategoryIntoModel() throws Exception{
//		when(categoryRepo.findById(2L)).thenReturn(Optional.of(category));
//		mvc.perform(get("/category?id=2")).andExpect(model().attribute("categories", is(category)));
//	}
//	
//	@Test
//	public void shouldBeOkForAllCategories() throws Exception{
//		mvc.perform(get("/categories")).andExpect(status().isOk());
//	}
//	
//	@Test
//	public void shouldRouteToAllCategoriesView() throws Exception{
//		mvc.perform(get("/categories")).andExpect(view().name(is("categories")));
//	
//	}
//	@Test
//	public void shouldPutAllcategoriesIntoModel() throws Exception{
//		Collection<Category> allCategories = Arrays.asList(category, category2);
//		when(categoryRepo.findAll()).thenReturn(allCategories);
//		mvc.perform(get("/categories")).andExpect(model().attribute("categories", is(allCategories)));
//	}
//	
	

}
