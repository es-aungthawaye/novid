package jdev.novid.model.usecase;

import java.io.Serializable;

import jdev.novid.common.value.Mobile;
import jdev.novid.component.ddd.Result;
import jdev.novid.model.domain.exception.MobileNotFoundException;
import jdev.novid.support.verification.exception.CodeAlreadyExpiredException;
import jdev.novid.support.verification.exception.TooManyAttemptsException;
import jdev.novid.support.verification.exception.VerificationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface DoSignIn {

    @Getter
    @AllArgsConstructor
    public static class Input implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        protected Mobile mobile;

        protected String code;

    }

    @Getter
    @AllArgsConstructor
    public static class Output implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        protected Result result;

        protected String token;

        protected Long userId;

    }

    public Output execute(Input input) throws MobileNotFoundException, VerificationNotFoundException,
            TooManyAttemptsException, CodeAlreadyExpiredException;
}
