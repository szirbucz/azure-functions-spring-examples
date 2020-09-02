package eu.wecanconsulting.example.dbwrite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.wecanconsulting.example.dbtrigger.Todo;
import eu.wecanconsulting.example.dbwrite.AddTodo;
import eu.wecanconsulting.example.utils.UUIDService;

@ExtendWith(MockitoExtension.class)
class AddTodoTest {

	@Mock
	private UUIDService uuidService;

	@InjectMocks
	private AddTodo addTodo;

	@Test
	@DisplayName("The original id should be kept when it is not null.")
	void testExistingIdIsKept() {
		final Todo item = addTodo.apply(TodoTestData.SHOPPING);
		assertEquals(TodoTestData.SHOPPING.getId(), item.getId());
	}

	@Test
	@DisplayName("A new id should be generated when it is null.")
	void testIdIsGeneratedWhenNotExisted() {
		Mockito.doReturn(TodoTestData.RANDOM_UUID).when(uuidService).generate();
		final Todo item = addTodo.apply(TodoTestData.NULLID);
		assertEquals(TodoTestData.RANDOM_UUID, item.getId());
	}

}
