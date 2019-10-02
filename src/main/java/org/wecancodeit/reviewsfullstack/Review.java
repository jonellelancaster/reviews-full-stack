package org.wecancodeit.reviewsfullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;
	
	private String reviewTitle;
	private String imageUrl;
	private String content;
	
	@ManyToMany
	private Collection<Catagory> catagories;

	public String getReviewTitle() {
		return reviewTitle;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getContent() {
		return content;
	}

	public long getId() {
		return id;
	}
	public Review() {
	}

	
	public Collection<Catagory> getCatagories() {
		return catagories;
	}
	public Review(String reviewTitle, String imageUrl, String content, Catagory...catagories) {
		this.reviewTitle = reviewTitle;
		this.imageUrl = imageUrl;
		this.content = content;
		this.catagories =new HashSet<>(Arrays.asList(catagories));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	}
