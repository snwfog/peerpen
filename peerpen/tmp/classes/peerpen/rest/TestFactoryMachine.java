package peerpen.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import peerpen.rest.Test;

@Machine
public class TestFactoryMachine extends SingleNameFactoryMachine<Test> {
    public static final Name<Test> NAME = Name.of(Test.class, "Test");

    public TestFactoryMachine() {
        super(0, new StdMachineEngine<Test>(NAME, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected Test doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new Test(

                );
            }
        });
    }

}
