package org.wecancodeit.reviewsfullstack.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.reviewsfullstack.models.Category;
import org.wecancodeit.reviewsfullstack.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCategoriesContains(Category category);

	Collection<Review> findByCategoriesId(Long id);
}
