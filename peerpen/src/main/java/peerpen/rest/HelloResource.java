package peerpen.rest;

import peerpen.domain.Message;
import peerpen.persistence.UserRepository;
import org.joda.time.DateTime;
import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.RolesAllowed;

@Component
@RestxResource
public class HelloResource {
	@GET("/message")
	@RolesAllowed(UserRepository.User.HELLO_ROLE)
	public Message sayHello(String whom) {
		return new Message().setMessage(String.format("hello %s, it's %s",
				whom, DateTime.now().toString("HH:mm:ss")));
	}

	@GET("/hello/message2")
	@RolesAllowed(UserRepository.User.HELLO_ROLE)
	public Message sayHello2(String whom, String who) {
		return new Message().setMessage(String.format(
				"hello %s, it's %s aisdhaoisdjoaidjioadjoadjsoa", whom,
				DateTime.now().toString("HH:mm:ss")));
	}

	@GET("/hello/message2")
	@RolesAllowed(UserRepository.User.HELLO_ROLE)
	public Message sayHello2(String whom) {
		return new Message().setMessage(String.format("hello %s, it's %s",
				whom, DateTime.now().toString("HH:mm:ss")));
	}

}
