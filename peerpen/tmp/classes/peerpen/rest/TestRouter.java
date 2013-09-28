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

public class TestRouter extends RestxRouter {

    public TestRouter(final Test resource, final ObjectMapper mapper, final MainStringConverter converter,
                    final Validator validator, final RestxSecurityManager securityManager) {
        super(
            "default", "TestRouter", new RestxRoute[] {
        new StdEntityRoute("default#Test#sayHello", mapper, new StdRouteMatcher("GET", "/test"), HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<?> doRoute(RestxRequest request, RestxRouteMatch match) throws IOException {
                securityManager.check(request, hasRole("hello-role"));
                return Optional.of(resource.sayHello(
                        /* [QUERY] whom */ checkPresent(request.getQueryParam("whom"), "query param whom is required"),
                        /* [QUERY] what */ checkPresent(request.getQueryParam("what"), "query param what is required")
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

                OperationParameterDescription what = new OperationParameterDescription();
                what.name = "what";
                what.paramType = OperationParameterDescription.ParamType.query;
                what.dataType = "string";
                what.required = true;
                operation.parameters.add(what);


                operation.responseClass = "Message";
            }
        },
        new StdEntityRoute("default#Test#hello", mapper, new StdRouteMatcher("GET", "/bobby"), HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<?> doRoute(RestxRequest request, RestxRouteMatch match) throws IOException {
                securityManager.check(request, hasRole("hello-role"));
                return Optional.of(resource.hello(
                        /* [QUERY] who */ checkPresent(request.getQueryParam("who"), "query param who is required"),
                        /* [QUERY] what */ checkPresent(request.getQueryParam("what"), "query param what is required")
                ));
            }

            

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription who = new OperationParameterDescription();
                who.name = "who";
                who.paramType = OperationParameterDescription.ParamType.query;
                who.dataType = "string";
                who.required = true;
                operation.parameters.add(who);

                OperationParameterDescription what = new OperationParameterDescription();
                what.name = "what";
                what.paramType = OperationParameterDescription.ParamType.query;
                what.dataType = "string";
                what.required = true;
                operation.parameters.add(what);


                operation.responseClass = "string";
            }
        },
        new StdEntityRoute("default#Test#fdafd", mapper, new StdRouteMatcher("GET", "/getArray"), HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<?> doRoute(RestxRequest request, RestxRouteMatch match) throws IOException {
                securityManager.check(request, hasRole("hello-role"));
                return Optional.of(resource.fdafd(
                        
                ));
            }

            

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "string[]";
            }
        },
        new StdEntityRoute("default#Test#getObject", mapper, new StdRouteMatcher("GET", "/getOject"), HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<?> doRoute(RestxRequest request, RestxRouteMatch match) throws IOException {
                securityManager.check(request, hasRole("hello-role"));
                return Optional.of(resource.getObject(
                        
                ));
            }

            

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "TestObject";
            }
        },
        new StdEntityRoute("default#Test#getObjectList", mapper, new StdRouteMatcher("GET", "/getOjectList"), HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<?> doRoute(RestxRequest request, RestxRouteMatch match) throws IOException {
                securityManager.check(request, hasRole("hello-role"));
                return Optional.of(resource.getObjectList(
                        
                ));
            }

            

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "TestObject>";
            }
        },
        });
    }

}
