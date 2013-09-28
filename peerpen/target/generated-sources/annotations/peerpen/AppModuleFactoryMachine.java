package peerpen;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import peerpen.AppModule;


@Machine
public class AppModuleFactoryMachine extends DefaultFactoryMachine {
    private static final AppModule module = new AppModule();

    public AppModuleFactoryMachine() {
        super(0, new MachineEngine[] {
            new StdMachineEngine<restx.SignatureKey>(Name.of(restx.SignatureKey.class, "signatureKey"), BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public restx.SignatureKey doNewComponent(SatisfiedBOM satisfiedBOM) {
	                    return module.signatureKey(
	        
	                    );
                }
            },
            new StdMachineEngine<java.lang.String>(Name.of(java.lang.String.class, "restx.admin.password"), BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public java.lang.String doNewComponent(SatisfiedBOM satisfiedBOM) {
	                    return module.restxAdminPassword(
	        
	                    );
                }
            },
            new StdMachineEngine<java.lang.String>(Name.of(java.lang.String.class, "app.name"), BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public java.lang.String doNewComponent(SatisfiedBOM satisfiedBOM) {
	                    return module.appName(
	        
	                    );
                }
            },
            new StdMachineEngine<restx.security.BasicPrincipalAuthenticator>(Name.of(restx.security.BasicPrincipalAuthenticator.class, "basicPrincipalAuthenticator"), BoundlessComponentBox.FACTORY) {
        private final Factory.Query<peerpen.persistence.UserRepository> userRepository = Factory.Query.byClass(peerpen.persistence.UserRepository.class).mandatory();
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        userRepository
                    ));
                }

                @Override
                public restx.security.BasicPrincipalAuthenticator doNewComponent(SatisfiedBOM satisfiedBOM) {
	                    return module.basicPrincipalAuthenticator(
	        satisfiedBOM.getOne(userRepository).get().getComponent()
	                    );
                }
            },
        });
}
}
