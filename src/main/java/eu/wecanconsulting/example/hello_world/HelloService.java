package eu.wecanconsulting.example.hello_world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	@Autowired
	ApplicationContext applicationContext;


	public String sayHello(final String name) {

		return "Hello " + name;
	}
}
