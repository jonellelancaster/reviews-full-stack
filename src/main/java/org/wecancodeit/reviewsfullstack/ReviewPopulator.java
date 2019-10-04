package org.wecancodeit.reviewsfullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {
	
	@Resource
	private ReviewRepository reviewRepo;
	@Resource
	private CategoryRepository categoryRepo;

	@Override
	public void run(String... args) throws Exception {
		
		Category highestGly = new Category("Highest Glycemic Affect");
		highestGly = categoryRepo.save(highestGly);
		
		Category mediumGly = new Category("Medium Glycemic Affect");
		mediumGly = categoryRepo.save(mediumGly);	
		
		Category lowestGly = new Category("Lowest Glycemic Affect");
		lowestGly = categoryRepo.save(lowestGly);
		
		Review sugar= new Review("Sucrose(table sugar)", "sugar.jpg", "Sugar has a glycemic index of 65 out of 100.", highestGly);
		sugar=reviewRepo.save(sugar);
		Review agave= new Review("Agave", "agave.jpg", "Agave has a glycemic index of 54 out of 100. ", mediumGly);
		agave=reviewRepo.save(agave);
		Review stevia= new Review("Stevia", "stevia.jpg", "Stevia has no affect on blood-sugar levels and has a glycemic index of 0.", lowestGly);
		stevia=reviewRepo.save(stevia);
		Review erythritol= new Review("Erythritol", "erythritol.jpg", "Erythritol has no affect on blood-sugar levels and has a glycemic index of 0.", lowestGly);
		erythritol=reviewRepo.save(erythritol);
	}

}
