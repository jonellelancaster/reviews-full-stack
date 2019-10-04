package org.wecancodeit.reviewsfullstack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import java.util.Collection;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityMangager;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Test
	public void shouldSaveAndLoadcategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		entityMangager.flush();
		entityMangager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category =result.get();
		assertThat(category.getName(), is("category"));
	}

	@Test
	public void shouldGeneratecategoryId() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		entityMangager.flush();
		entityMangager.clear();
		assertThat(categoryId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadReview() {
		Review review = new Review("name", "imageUrl", "content");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		entityMangager.flush();
		entityMangager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review =result.get();
		assertThat(review.getName(), is("name"));
	
	}
	@Test
	public void shouldEstablishRelationshipReviewTocategory() {
		Category lowestCarb = categoryRepo.save(new Category("Lowest Carb Count"));
		Category mediumCarb = categoryRepo.save(new Category("Medium Carb Count"));

		Review review = new Review("Eritryol", "imageUrl", "content", lowestCarb, mediumCarb);
		review = reviewRepo.save(review);
		long reviewId = review.getId();

		entityMangager.flush();
		entityMangager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getCategories(), containsInAnyOrder(lowestCarb, mediumCarb));
	}

	@Test
	public void shouldFindReviewsForcategories() {
		Category lowestCarb = categoryRepo.save(new Category("Lowest Carb Count"));

		Review review1 = reviewRepo.save(new Review("Eritryol", "imageUrl", "content", lowestCarb));
		Review review2 = reviewRepo.save(new Review("MonkFruit", "imageUrl", "content", lowestCarb));
		Review review3 = reviewRepo.save(new Review("MonkFruit", "imageUrl", "content"));

		entityMangager.flush();
		entityMangager.clear();
		Collection<Review> reviewsForcategory = reviewRepo.findByCategoriesContains(lowestCarb);
		assertThat(reviewsForcategory, containsInAnyOrder(review1, review2));

	}

	@Test
	public void shouldFindReviewsForcategoriesById() {

		Category lowestCarb = categoryRepo.save(new Category("Lowest Carb Count"));
		long categoryId = lowestCarb.getId();

		Review review1 = reviewRepo.save(new Review("Eritryol", "imageUrl", "content", lowestCarb));
		Review review2 = reviewRepo.save(new Review("MonkFruit", "imageUrl", "content", lowestCarb));

		entityMangager.flush();
		entityMangager.clear();
		Collection<Review> reviewsForcategory = reviewRepo.findByCategoriesId(categoryId);
		assertThat(reviewsForcategory, containsInAnyOrder(review1, review2));

	}

}
