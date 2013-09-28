package peerpen.rest;

import peerpen.domain.Message;
import peerpen.domain.TestObject;

import java.util.ArrayList;
import peerpen.persistence.UserRepository;
import org.joda.time.DateTime;
import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.RolesAllowed;

@Component
@RestxResource
public class Test {
	@GET("/test")
	@RolesAllowed(UserRepository.User.HELLO_ROLE)
	public Message sayHello(String whom, String what) {
		return new Message().setMessage(String.format("hello %s, it's %s, %s",
				whom, DateTime.now().toString("HH:mm:ss"), what));
	}

	@GET("/bobby")
	@RolesAllowed(UserRepository.User.HELLO_ROLE)
	public String hello(String who, String what) {

		return who + " " + what;
	}

	@GET("/getArray")
	@RolesAllowed(UserRepository.User.HELLO_ROLE)
	public String[] fdafd() {
		String[] array = new String[3];
		array[0] = "sdfads";
		array[1] = "dfads";

		return array;
	}

	@GET("/getOject")
	@RolesAllowed(UserRepository.User.HELLO_ROLE)
	public TestObject getObject() {
		return new TestObject("Grand Theft Auto", "Franklin", "5", "9900",
				"900", "game");
	}

	@GET("/getOjectList")
	@RolesAllowed(UserRepository.User.HELLO_ROLE)
	public ArrayList<TestObject> getObjectList() {
		ArrayList<TestObject> hello = new ArrayList<TestObject>();
		hello.add(new TestObject("Grand Theft Auto", "Franklin", "5", "9900",
				"900", "game"));

		hello.add(new TestObject("title 1", "blau", "5", "9900", "900", "game"));
		return hello;
	}
}