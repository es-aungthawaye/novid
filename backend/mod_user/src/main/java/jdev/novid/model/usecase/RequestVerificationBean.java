package jdev.novid.model.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jdev.novid.component.ddd.Result;
import jdev.novid.model.domain.User;
import jdev.novid.model.domain.exception.MobileAlreadyTakenException;
import jdev.novid.model.query.UserQuery;
import jdev.novid.support.verification.VerificationService;
import jdev.novid.support.verification.exception.CodeRequestRejectedException;
import jdev.novid.support.verification.exception.TooManyRequestsException;

@Service
public class RequestVerificationBean implements RequestVerification {

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private UserQuery userQuery;

    @Override
    @Transactional(
        rollbackFor = Exception.class,
        noRollbackFor = { TooManyRequestsException.class, CodeRequestRejectedException.class })
    public Output execute(Input input)
            throws MobileAlreadyTakenException, TooManyRequestsException, CodeRequestRejectedException {

        Optional<User> optUser = this.userQuery.findUser(input.mobile);

        if (optUser.isPresent()) {

            throw new MobileAlreadyTakenException();

        }

        this.verificationService.requestForSignUp(input.mobile);

        return new Output(Result.SUCCESS);

    }

}
