package org.wecancodeit.reviewsfullstack.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String imageUrl;
	@Lob
	private String content;
	
	@ManyToMany
	private Collection<Category> categories;

	public String getName() {
		return name;
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

	
	public Collection<Category> getCategories() {
		return categories;
	}
	public Review(String name, String imageUrl, String content, Category...categories) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.content = content;
		this.categories =new HashSet<>(Arrays.asList(categories));
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
