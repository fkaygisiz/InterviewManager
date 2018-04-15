package com.fatih.interview.time;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "date_intervals")
@AssociationOverrides
(
   {
        @AssociationOverride(name = "dateTimeId.person",
              joinColumns = @JoinColumn(name = "user_id"))
   }
)
public class DateTime implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DateTimeId dateTimeId;

	private Boolean arranged;

	public Boolean isArranged() {
		return arranged;
	}

	public void setArranged(Boolean arranged) {
		this.arranged = arranged;
	}

	public DateTimeId getDateTimeId() {
		return dateTimeId;
	}

	public void setDateTimeId(DateTimeId dateTimeId) {
		this.dateTimeId = dateTimeId;
	}

}
