package peerpen.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkNotNull;

import restx.*;
import restx.factory.*;
import restx.security.*;
import static restx.security.Permissions.*;
import restx.description.*;
import restx.converters.MainStringConverter;
import static restx.common.MorePreconditions.checkPresent;

import javax.validation.Validator;
import static restx.validation.Validations.checkValid;

import java.io.IOException;
import java.io.PrintWriter;

@Component(priority = 0)

public class HelloResourceRouter extends RestxRouter {

    public HelloResourceRouter(final HelloResource resource, final ObjectMapper mapper, final MainStringConverter converter,
                    final Validator validator, final RestxSecurityManager securityManager) {
        super(
            "default", "HelloResourceRouter", new RestxRoute[] {
        new StdEntityRoute("default#HelloResource#sayHello", mapper, new StdRouteMatcher("GET", "/message"), HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<?> doRoute(RestxRequest request, RestxRouteMatch match) throws IOException {
                securityManager.check(request, hasRole("hello-role"));
                return Optional.of(resource.sayHello(
                        /* [QUERY] whom */ checkPresent(request.getQueryParam("whom"), "query param whom is required")
                ));
            }

            

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription whom = new OperationParameterDescription();
                whom.name = "whom";
                whom.paramType = OperationParameterDescription.ParamType.query;
                whom.dataType = "string";
                whom.required = true;
                operation.parameters.add(whom);


                operation.responseClass = "Message";
            }
        },
        new StdEntityRoute("default#HelloResource#sayHello2", mapper, new StdRouteMatcher("GET", "/hello/message2"), HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<?> doRoute(RestxRequest request, RestxRouteMatch match) throws IOException {
                securityManager.check(request, hasRole("hello-role"));
                return Optional.of(resource.sayHello2(
                        /* [QUERY] whom */ checkPresent(request.getQueryParam("whom"), "query param whom is required"),
                        /* [QUERY] who */ checkPresent(request.getQueryParam("who"), "query param who is required")
                ));
            }

            

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription whom = new OperationParameterDescription();
                whom.name = "whom";
                whom.paramType = OperationParameterDescription.ParamType.query;
                whom.dataType = "string";
                whom.required = true;
                operation.parameters.add(whom);

                OperationParameterDescription who = new OperationParameterDescription();
                who.name = "who";
                who.paramType = OperationParameterDescription.ParamType.query;
                who.dataType = "string";
                who.required = true;
                operation.parameters.add(who);


                operation.responseClass = "Message";
            }
        },
        new StdEntityRoute("default#HelloResource#sayHello2", mapper, new StdRouteMatcher("GET", "/hello/message2"), HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<?> doRoute(RestxRequest request, RestxRouteMatch match) throws IOException {
                securityManager.check(request, hasRole("hello-role"));
                return Optional.of(resource.sayHello2(
                        /* [QUERY] whom */ checkPresent(request.getQueryParam("whom"), "query param whom is required")
                ));
            }

            

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription whom = new OperationParameterDescription();
                whom.name = "whom";
                whom.paramType = OperationParameterDescription.ParamType.query;
                whom.dataType = "string";
                whom.required = true;
                operation.parameters.add(whom);


                operation.responseClass = "Message";
            }
        },
        });
    }

}
