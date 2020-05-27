package challenge;

import javax.persistence.*;

@Entity(name = "scripts")
@Table
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "actor")
	private String actor;

	@Column(name = "detail")
	private String quote;


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
			this.id = id;
	}

	public String getActor() {
		return this.actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getQuote() {
		return this.quote;
	}

	public void setQuote(String quote) {
		this.quote=quote;
	}

}
