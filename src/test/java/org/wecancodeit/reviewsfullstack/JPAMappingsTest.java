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
	private CatagoryRepository catagoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Test
	public void shouldSaveAndLoadCatagory() {
		Catagory catagory = catagoryRepo.save(new Catagory("catagory"));
		long catagoryId = catagory.getId();
		entityMangager.flush();
		entityMangager.clear();

		Optional<Catagory> result = catagoryRepo.findById(catagoryId);
		catagory =result.get();
		assertThat(catagory.getCatagoryName(), is("catagory"));
	}

	@Test
	public void shouldGenerateCatagoryId() {
		Catagory catagory = catagoryRepo.save(new Catagory("catagory"));
		long catagoryId = catagory.getId();
		entityMangager.flush();
		entityMangager.clear();
		assertThat(catagoryId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadReview() {
		Review review = new Review("review title", "imageUrl", "content");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		entityMangager.flush();
		entityMangager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review =result.get();
		assertThat(review.getReviewTitle(), is("review title"));
	}

	@Test
	public void shouldEstablishRelationshipReviewToCatagory() {
		Catagory lowestCarb = catagoryRepo.save(new Catagory("Lowest Carb Count"));
		Catagory mediumCarb = catagoryRepo.save(new Catagory("Medium Carb Count"));

		Review review = new Review("Eritryol", "imageUrl", "content", lowestCarb, mediumCarb);
		review = reviewRepo.save(review);
		long reviewId = review.getId();

		entityMangager.flush();
		entityMangager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getCatagories(), containsInAnyOrder(lowestCarb, mediumCarb));
	}

	@Test
	public void shouldFindReviewsForCatagories() {
		Catagory lowestCarb = catagoryRepo.save(new Catagory("Lowest Carb Count"));

		Review review1 = reviewRepo.save(new Review("Eritryol", "imageUrl", "content", lowestCarb));
		Review review2 = reviewRepo.save(new Review("MonkFruit", "imageUrl", "content", lowestCarb));
		Review review3 = reviewRepo.save(new Review("MonkFruit", "imageUrl", "content"));

		entityMangager.flush();
		entityMangager.clear();
		Collection<Review> reviewsForCatagory = reviewRepo.findByCatagoriesContains(lowestCarb);
		assertThat(reviewsForCatagory, containsInAnyOrder(review1, review2));

	}

	@Test
	public void shouldFindReviewsForCatagoriesById() {

		Catagory lowestCarb = catagoryRepo.save(new Catagory("Lowest Carb Count"));
		long catagoryId = lowestCarb.getId();

		Review review1 = reviewRepo.save(new Review("Eritryol", "imageUrl", "content", lowestCarb));
		Review review2 = reviewRepo.save(new Review("MonkFruit", "imageUrl", "content", lowestCarb));

		entityMangager.flush();
		entityMangager.clear();
		Collection<Review> reviewsForCatagory = reviewRepo.findByCatagoriesId(catagoryId);
		assertThat(reviewsForCatagory, containsInAnyOrder(review1, review2));

	}

}
