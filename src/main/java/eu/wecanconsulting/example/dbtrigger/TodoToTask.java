package eu.wecanconsulting.example.dbtrigger;

import java.sql.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.wecanconsulting.example.utils.CurrentTimeService;
import eu.wecanconsulting.example.utils.UUIDService;

@Component("TodoToTask")
public class TodoToTask implements Function<List<Todo>, List<Task>> {

	@Autowired
	private CurrentTimeService currentTimeService;

	@Autowired
	private UUIDService uuidService;

	@Override
	public List<Task> apply(final List<Todo> ToDos) {
		return ToDos.stream().map(this::convert).collect(Collectors.toList());
	}

	private Task convert(final Todo toDo) {
		final Task result = new Task();
		result.setId(uuidService.generate());
		result.setCreatedDate(new Date(currentTimeService.getCurrentTime()));
		result.setStatus(TaskStatus.CREATED);
		result.setDescription(toDo.getTask());
		return result;
	}

}
