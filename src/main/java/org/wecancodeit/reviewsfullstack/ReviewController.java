package org.wecancodeit.reviewsfullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ReviewController {

	@Resource
	ReviewRepository reviewRepo;
	@Resource
	CatagoryRepository catagoryRepo;

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id") long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
		throw new ReviewNotFoundException();
	}

	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("reviews");
	}

	@RequestMapping("/catagory")
	public String findOneCatagory(@RequestParam("id") long id, Model model) throws CatagoryNotFoundException {
		Optional<Catagory> catagory = catagoryRepo.findById(id);
		if (catagory.isPresent()) {
			model.addAttribute("catagories", catagory.get());
			return "catagory";
		}
		throw new CatagoryNotFoundException();
	}
@RequestMapping
	public String findAllCatagories(Model model) {
	model.addAttribute("catagories", catagoryRepo.findAll());
	return ("catagories");
		
	}

}
