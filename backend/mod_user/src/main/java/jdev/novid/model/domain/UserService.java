package jdev.novid.model.domain;

import jdev.novid.common.identity.UserId;
import jdev.novid.common.value.Mobile;
import jdev.novid.common.value.Nric;
import jdev.novid.model.domain.exception.MobileAlreadyTakenException;

public interface UserService {

    public User createUser(Mobile mobile, String name, Nric nric) throws MobileAlreadyTakenException;

    public Account createAccount(User user);

    public Account renew(UserId userId);

}
