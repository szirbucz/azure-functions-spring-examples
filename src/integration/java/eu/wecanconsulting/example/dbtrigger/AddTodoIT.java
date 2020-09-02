package eu.wecanconsulting.example.dbtrigger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import eu.wecanconsulting.example.Config;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Config.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AddTodoIT {

	private static final String PATH = "/AddTodo";
	private static final String BREAD = "Bake some bread";
	private static final String PUSH_UPS = "Do 10 push-ups";
	@Autowired
	private WebTestClient client;

	@Test
	@DisplayName("Existing ID should not be changed.")
	public void testTodoWithIdKeepsOriginalId() {
		final Todo todo = createTodo(PUSH_UPS);

		final UUID randomUUID = UUID.randomUUID();
		todo.setId(randomUUID);
		client.post().uri(PATH).body(Mono.just(todo), Todo.class).exchange()
		.expectBody(Todo.class).value((result) -> assertEquals(randomUUID, result.getId()));
	}

	@Test
	@DisplayName("ID should be generated when it is not existing.")
	public void testTodoWithoutIdGetsAnId() {
		final Todo todo = createTodo(BREAD);
		client.post().uri(PATH).body(Mono.just(todo), Todo.class).exchange()
		.expectBody(Todo.class).value((result) -> assertNotNull(result.getId()));

	}

	private Todo createTodo(final String task) {
		final Todo todo = new Todo();
		todo.setTask(task);
		return todo;
	}

}
