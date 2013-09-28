package peerpen.persistence;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import peerpen.persistence.UserRepository;

@Machine
public class UserRepositoryFactoryMachine extends SingleNameFactoryMachine<UserRepository> {
    public static final Name<UserRepository> NAME = Name.of(UserRepository.class, "UserRepository");

    public UserRepositoryFactoryMachine() {
        super(0, new StdMachineEngine<UserRepository>(NAME, BoundlessComponentBox.FACTORY) {
private final Factory.Query<java.lang.String> adminPassword = Factory.Query.byName(Name.of(java.lang.String.class, "restx.admin.password")).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
adminPassword
                ));
            }

            @Override
            protected UserRepository doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new UserRepository(
satisfiedBOM.getOne(adminPassword).get().getComponent()
                );
            }
        });
    }

}
