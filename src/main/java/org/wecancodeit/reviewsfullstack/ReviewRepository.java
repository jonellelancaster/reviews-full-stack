package org.wecancodeit.reviewsfullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCatagoriesContains(Catagory catagory);

	Collection<Review> findByCatagoriesId(Long id);
}
