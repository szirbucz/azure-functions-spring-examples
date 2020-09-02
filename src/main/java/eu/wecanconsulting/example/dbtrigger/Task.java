package eu.wecanconsulting.example.dbtrigger;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Task {
	@EqualsAndHashCode.Exclude
	private UUID id;
	private Date createdDate;
	private TaskStatus status;
	private String description;
}
