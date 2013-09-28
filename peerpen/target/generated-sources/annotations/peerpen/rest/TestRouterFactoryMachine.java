package peerpen.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import peerpen.rest.TestRouter;

@Machine
public class TestRouterFactoryMachine extends SingleNameFactoryMachine<TestRouter> {
    public static final Name<TestRouter> NAME = Name.of(TestRouter.class, "TestRouter");

    public TestRouterFactoryMachine() {
        super(0, new StdMachineEngine<TestRouter>(NAME, BoundlessComponentBox.FACTORY) {
private final Factory.Query<peerpen.rest.Test> resource = Factory.Query.byClass(peerpen.rest.Test.class).mandatory();
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
            protected TestRouter doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new TestRouter(
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
