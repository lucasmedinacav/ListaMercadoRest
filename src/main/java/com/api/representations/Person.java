package com.api.representations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedNativeQuery(name = "Person.findCampaingsNotRegisteredAndPeriodIsValid",
		query = "select distinct(c.*) from Campaign c, Person p where p.team_id = c.team_id and c.team_id = :idTeam and CURRENT_DATE <= c.dt_finish and " +
				"NOT EXISTS(select * from campaign_person ca where ca.campaign_id = c.campaign_id and ca.person_id = :idPerson)", resultClass = Campaign.class)
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@Column(name = "dt_birth")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dtBirth;
	
	@NotNull
	@Column(name = "email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "people")
	private List<Campaign> campaigns;
	
	public Person(){}
	
	public Person(String name, Date dtBirth, String email){
		super();
		this.name = name;
		this.dtBirth = dtBirth;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getDtBirth() {
		return dtBirth;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public List<Campaign> getCampaigns() {
		if (campaigns == null)
			campaigns = new ArrayList<>();
		return campaigns;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDtBirth(Date dtBirth) {
		this.dtBirth = dtBirth;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campaigns == null) ? 0 : campaigns.hashCode());
		result = prime * result + ((dtBirth == null) ? 0 : dtBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		Person other = (Person) obj;
		if (campaigns == null) {
			if (other.campaigns != null)
				return false;
		} else if (!campaigns.equals(other.campaigns))
			return false;
		if (dtBirth == null) {
			if (other.dtBirth != null)
				return false;
		} else if (!dtBirth.equals(other.dtBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}
}
