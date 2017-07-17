package com.api.representations;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "team", targetEntity = Campaign.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Campaign> campaigns;
	
	@OneToMany(mappedBy = "team", targetEntity = Person.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Person> people;
	
	public Team(){}
	
	public Team(String name){
		super();
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	
	public List<Person> getPeople() {
		return people;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campaigns == null) ? 0 : campaigns.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((people == null) ? 0 : people.hashCode());
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
		Team other = (Team) obj;
		if (campaigns == null) {
			if (other.campaigns != null)
				return false;
		} else if (!campaigns.equals(other.campaigns))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (people == null) {
			if (other.people != null)
				return false;
		} else if (!people.equals(other.people))
			return false;
		return true;
	}
	
}
