package jdev.novid.model.service;

import jdev.novid.common.value.Mobile;
import jdev.novid.model.domain.Account;
import jdev.novid.model.domain.User;
import jdev.novid.model.domain.exception.MobileAlreadyTakenException;

public interface UserService {

    public User createUser(Mobile mobile, String name, String nric) throws MobileAlreadyTakenException;

    public Account createAccount(User user);

}