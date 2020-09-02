package eu.wecanconsulting.example.dbwrite;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.wecanconsulting.example.dbtrigger.Todo;
import eu.wecanconsulting.example.utils.UUIDService;

@Component("AddTodo")
public class AddTodo implements Function<Todo, Todo>{

	@Autowired
	private UUIDService uuidService;

	@Override
	public Todo apply(final Todo item) {
		if (null == item.getId()) {
			item.setId(uuidService.generate());
		}
		return item;
	}

}
