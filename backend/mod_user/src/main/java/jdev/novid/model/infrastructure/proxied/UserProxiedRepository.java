package jdev.novid.model.infrastructure.proxied;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jdev.novid.common.identity.UserId;
import jdev.novid.common.value.Mobile;
import jdev.novid.component.persistence.PersistenceQualifiers;
import jdev.novid.model.domain.User;
import jdev.novid.model.infrastructure.UserRepository;

@Component
@Qualifier(PersistenceQualifiers.PROXIED)
public class UserProxiedRepository implements UserRepository {

    @Autowired
    @Qualifier(PersistenceQualifiers.JPA)
    private UserRepository userJpaRepository;

    @Autowired
    @Qualifier(PersistenceQualifiers.AEROSPIKE)
    private UserRepository userAerospikeRepository;

    @Override
    public void save(User domain) {

        this.userJpaRepository.save(domain);
        this.userAerospikeRepository.save(domain);

    }

    @Override
    public void delete(UserId id) {

        this.userJpaRepository.delete(id);
        this.userAerospikeRepository.delete(id);

    }

    @Override
    public User get(UserId id) {

        Optional<User> optUser = this.userAerospikeRepository.findById(id);

        if (optUser.isPresent()) {

            return optUser.get();

        }

        User user = this.userJpaRepository.get(id);

        this.userAerospikeRepository.save(user);

        return user;

    }

    @Override
    public Optional<User> findById(UserId id) {

        Optional<User> optUser = this.userAerospikeRepository.findById(id);

        if (optUser.isPresent()) {

            return optUser;

        }

        optUser = this.userJpaRepository.findById(id);

        if (optUser.isPresent()) {

            this.userAerospikeRepository.save(optUser.get());

        }

        return optUser;

    }

    @Override
    public Optional<User> find(Mobile mobile) {

        Optional<User> optUser = this.userAerospikeRepository.find(mobile);

        if (optUser.isPresent()) {

            return optUser;

        }

        optUser = this.userJpaRepository.find(mobile);

        if (optUser.isPresent()) {

            this.userAerospikeRepository.save(optUser.get());

        }

        return optUser;

    }

}
