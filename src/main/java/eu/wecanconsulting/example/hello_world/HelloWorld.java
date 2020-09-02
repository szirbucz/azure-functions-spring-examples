package eu.wecanconsulting.example.hello_world;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("HelloWorld")
public class HelloWorld implements Function<Optional<String>, String> {

	private static final String DEFAULT_NAME = "OSN";

	@Autowired
	private HelloService helloService;

	@Override
	public String apply(final Optional<String> name) {
		return helloService.sayHello(name.orElse(DEFAULT_NAME));
	}
}
