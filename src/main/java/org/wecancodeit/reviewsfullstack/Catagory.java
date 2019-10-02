package org.wecancodeit.reviewsfullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Catagory {

	@Id
	@GeneratedValue
	private long id;

	private String catagoryName;
	
	
	@ManyToMany(mappedBy= "catagories")
	private Collection<Review> reviews;

	public long getId() {
		return id;
	}

	public Object getCatagoryName() {
		return catagoryName;
	}
	

	public Collection<Review> getReviews() {
		return reviews;
	}

	public Catagory() {
	}

	public Catagory(String catagoryName) {
		this.catagoryName = catagoryName;

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
		Catagory other = (Catagory) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
