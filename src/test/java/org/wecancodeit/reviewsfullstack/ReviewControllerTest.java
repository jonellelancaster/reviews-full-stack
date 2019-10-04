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


public class ReviewControllerTest {

	@InjectMocks
	private ReviewController underTest;
	@Mock
	private Review review;
	@Mock
	private Review review2;
	
	@Mock
	private Category category;
	
	@Mock
	private Category category2;
	
	@Mock
	private ReviewRepository reviewRepo;
	@Mock
	private CategoryRepository categoryRepo;
	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbituaryReviewId = 1;
		when(reviewRepo.findById(arbituaryReviewId)).thenReturn(Optional.of(review));

		underTest.findOneReview(arbituaryReviewId, model);
		verify(model).addAttribute("reviews", review);
	}
	
	@Test
	public void shouldFindAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review,review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
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
	
	
	
	
	
	
	


