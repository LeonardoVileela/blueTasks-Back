package project.api.rest.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import project.api.rest.domain.user.AppUser;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private LocalDate whenToDo;

	private Boolean done = false;

	@ManyToOne
	@JoinColumn(name = "app_user_id")
	private AppUser appUser;

	public Task() {

	}

	public Task(String description, LocalDate whenToDo, Boolean done, AppUser appUser) {
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
		this.appUser = appUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getWhenToDo() {
		return whenToDo;
	}

	public void setWhenToDo(LocalDate whenToDo) {
		this.whenToDo = whenToDo;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Long getId() {
		return id;
	}

}
