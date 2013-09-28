package peerpen.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import peerpen.rest.HelloResourceRouter;

@Machine
public class HelloResourceRouterFactoryMachine extends SingleNameFactoryMachine<HelloResourceRouter> {
    public static final Name<HelloResourceRouter> NAME = Name.of(HelloResourceRouter.class, "HelloResourceRouter");

    public HelloResourceRouterFactoryMachine() {
        super(0, new StdMachineEngine<HelloResourceRouter>(NAME, BoundlessComponentBox.FACTORY) {
private final Factory.Query<peerpen.rest.HelloResource> resource = Factory.Query.byClass(peerpen.rest.HelloResource.class).mandatory();
private final Factory.Query<com.fasterxml.jackson.databind.ObjectMapper> mapper = Factory.Query.byClass(com.fasterxml.jackson.databind.ObjectMapper.class).mandatory();
private final Factory.Query<restx.converters.MainStringConverter> converter = Factory.Query.byClass(restx.converters.MainStringConverter.class).mandatory();
private final Factory.Query<javax.validation.Validator> validator = Factory.Query.byClass(javax.validation.Validator.class).mandatory();
private final Factory.Query<restx.security.RestxSecurityManager> securityManager = Factory.Query.byClass(restx.security.RestxSecurityManager.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
resource,
mapper,
converter,
validator,
securityManager
                ));
            }

            @Override
            protected HelloResourceRouter doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new HelloResourceRouter(
satisfiedBOM.getOne(resource).get().getComponent(),
satisfiedBOM.getOne(mapper).get().getComponent(),
satisfiedBOM.getOne(converter).get().getComponent(),
satisfiedBOM.getOne(validator).get().getComponent(),
satisfiedBOM.getOne(securityManager).get().getComponent()
                );
            }
        });
    }

}
