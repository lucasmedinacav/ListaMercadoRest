package com.api.representations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "campaign_id", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@Column(name = "dt_start")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dtStart;
	
	@Column(name = "has_changes")
	@NotNull
	private boolean hasChanges;
	
	@Column(name = "dt_finish")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dtFinish;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "campaign_person",
			joinColumns = {@JoinColumn(name = "campaign_id", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)})
	private List<Person> people;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	public Campaign(){}
	
	public Campaign(String name, Date dtStart, Date dtFinish, Team team){
		super();
		this.name = name;
		this.dtStart = dtStart;
		this.dtFinish = dtFinish;
		this.team = team;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getDtStart() {
		return dtStart;
	}
	
	public Date getDtFinish() {
		return dtFinish;
	}
	
	public List<Person> getPeople() {
		if (people == null)
			people = new ArrayList<>();
		return people;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}
	
	public void setDtFinish(Date dtFinish) {
		this.dtFinish = dtFinish;
	}
	
	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public boolean isHasChanges() {
		return hasChanges;
	}
	
	public void setHasChanges(boolean hasChanges) {
		this.hasChanges = hasChanges;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtFinish == null) ? 0 : dtFinish.hashCode());
		result = prime * result + ((dtStart == null) ? 0 : dtStart.hashCode());
		result = prime * result + (hasChanges ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((people == null) ? 0 : people.hashCode());
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
		Campaign other = (Campaign) obj;
		if (dtFinish == null) {
			if (other.dtFinish != null)
				return false;
		} else if (!dtFinish.equals(other.dtFinish))
			return false;
		if (dtStart == null) {
			if (other.dtStart != null)
				return false;
		} else if (!dtStart.equals(other.dtStart))
			return false;
		if (hasChanges != other.hasChanges)
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
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}
}
