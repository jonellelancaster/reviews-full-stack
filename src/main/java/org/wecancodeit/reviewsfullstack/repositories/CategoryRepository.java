package org.wecancodeit.reviewsfullstack.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.reviewsfullstack.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
