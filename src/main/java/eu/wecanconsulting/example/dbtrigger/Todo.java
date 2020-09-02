package eu.wecanconsulting.example.dbtrigger;

import java.util.UUID;

import lombok.Data;

@Data
public class Todo {

	private UUID id;
	private String task;

}
