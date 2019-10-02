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
	private Catagory catagory;
	
	@Mock
	private Catagory catagory2;
	
	@Mock
	private ReviewRepository reviewRepo;
	@Mock
	private CatagoryRepository catagoryRepo;
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
	public void shouldAddSingleCatagoryToModel() throws CatagoryNotFoundException{
		long arbituaryCatagoryId = 1;
		when(catagoryRepo.findById(arbituaryCatagoryId)).thenReturn(Optional.of(catagory));

		underTest.findOneCatagory(arbituaryCatagoryId, model);
		verify(model).addAttribute("catagories", catagory);
	}
	
	
	@Test
	public void shouldAddAllCatagoriesToModel() {
		Collection<Catagory> allCatagories =Arrays.asList(catagory, catagory2);
		when(catagoryRepo.findAll()).thenReturn(allCatagories);
		underTest.findAllCatagories(model);
		verify(model).addAttribute("catagories", allCatagories);
	}
		
	
	}
	
	
	
	
	
	
	


