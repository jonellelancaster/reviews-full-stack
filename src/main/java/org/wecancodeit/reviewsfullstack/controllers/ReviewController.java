package org.wecancodeit.reviewsfullstack.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.reviewsfullstack.models.Category;
import org.wecancodeit.reviewsfullstack.models.Review;
import org.wecancodeit.reviewsfullstack.notfound.CategoryNotFoundException;
import org.wecancodeit.reviewsfullstack.notfound.ReviewNotFoundException;
import org.wecancodeit.reviewsfullstack.repositories.CategoryRepository;
import org.wecancodeit.reviewsfullstack.repositories.ReviewRepository;

@Controller

public class ReviewController {

	@Resource
	ReviewRepository reviewRepo;
//	@Resource
//	CategoryRepository categoryRepo;

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id") long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
		throw new ReviewNotFoundException();
	}

	@RequestMapping("/reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("reviews");
	}

//	@RequestMapping("/category")
//	public String findOneCategory(@RequestParam(value = "id") long id, Model model) throws CategoryNotFoundException {
//		Optional<Category> category = categoryRepo.findById(id);
//		if (category.isPresent()) {
//			model.addAttribute("categories", category.get());
//			return "category";
//		}
//		throw new CategoryNotFoundException();
//	}
//
//	@RequestMapping("/categories")
//	public String findAllCategories(Model model) {
//		model.addAttribute("categories", categoryRepo.findAll());
//		return ("categories");
//
//	}

}
