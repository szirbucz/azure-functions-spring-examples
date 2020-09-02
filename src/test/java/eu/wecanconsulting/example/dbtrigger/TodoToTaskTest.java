package eu.wecanconsulting.example.dbtrigger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.wecanconsulting.example.dbwrite.TodoTestData;
import eu.wecanconsulting.example.utils.CurrentTimeService;
import eu.wecanconsulting.example.utils.UUIDService;

@ExtendWith(MockitoExtension.class)

public class TodoToTaskTest {

	@InjectMocks
	private TodoToTask todoToTask;

	@Mock
	private CurrentTimeService currentTimeService;

	@Mock
	private UUIDService uuidService;

	@BeforeEach
	private void setup() {
		Mockito.lenient().doReturn(TaskTestData.CREATION_DATE).when(currentTimeService).getCurrentTime();
	}

	@Test
	@DisplayName("Empty List should be returned, when the input is empty List.")
	public void testConversionOfEmptyList() {
		assertEquals(List.of(), todoToTask.apply(List.of()));
	}

	@Test
	@DisplayName("Single Todo should be converted to single Task.")
	public void testConversionOfSingleItem() {
		assertEquals(List.of(TaskTestData.WASHING), todoToTask.apply(List.of(TodoTestData.WASHING)));
	}

	@Test
	@DisplayName("Multiple Todos should be converted to multiple Tasks.")
	public void testConversionOfMultipleItem() {
		assertEquals(List.of(TaskTestData.WASHING, TaskTestData.SHOPPING),
				todoToTask.apply(List.of(TodoTestData.WASHING, TodoTestData.SHOPPING)));
	}
}
