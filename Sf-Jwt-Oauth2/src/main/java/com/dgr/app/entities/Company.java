package com.dgr.app.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "COMPANIES")
public class Company implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Long companyId;

	@Column(length = 80)
	@Size(min = 4, max = 80)
	@NotBlank
	private String name;

	@Column(length = 80)
	@Size(min = 4, max = 80)
	@Email
	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createAt;

	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<BookableUnit> bookableUnits;

	public Company() {
		this.bookableUnits = new ArrayList<BookableUnit>();
	}

	@PrePersist
	public void prePersist() {

		createAt = new Date();
	}

	/** GET and SET */



	public String getName() {
		return name;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<BookableUnit> getBookableUnits() {
		return bookableUnits;
	}

	public void setBookableUnits(List<BookableUnit> bookableUnits) {
		this.bookableUnits = bookableUnits;
	}

	public void addBookableUnit(BookableUnit bookableUnit) {

		bookableUnits.add(bookableUnit);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
