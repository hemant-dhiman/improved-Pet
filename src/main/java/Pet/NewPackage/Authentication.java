package Pet.NewPackage;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class Authentication implements AuthenticationProvider {

    @Inject
    public UsersData usr;


    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        String username = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();
        System.out.println(username + ", " + password);

        if (password.equals(usr.getUserPassword(username))) {
            UserDetails details = new UserDetails(username, Collections.singletonList(usr.getUserRole(username)));
            return Flowable.just(details);
        } else {
            return Flowable.just(new AuthenticationFailed("Credentials are Not Valid"));
        }
    }
}


/*        return Flowable.create(emitter -> {
             //if (password.equals("smith123")) {
            //UserDetails details = new UserDetails(userName, Collections.singletonList("ADMIN"));
            //return Flowable.just(details);
        //} else {
          //  return Flowable.just(new AuthenticationFailed("Authentication FAILED"));
        //}
            String userName = authenticationRequest.getIdentity().toString();
            String passWord = authenticationRequest.getSecret().toString();
            System.out.println(userName+", "+passWord);

            usr.validUser(userName).subscribe(usrCheck -> {
                if(usrCheck.isEmpty()){
                    emitter.onNext(new AuthenticationFailed("credentials are not valid!"));
                }else {
                    if (passWord.equals(usr.getUserPassword(userName))) {
                        //UserDetails details = new UserDetails(userName, Collections.singletonList(usr.getUserRole(userName)));
                        //return Flowable.just(details);
                        emitter.onNext(new UserDetails(userName, Collections.singletonList(usr.getUserRole(userName))));
                    } else {
                        //return Flowable.just(new AuthenticationFailed());
                        emitter.onNext(new AuthenticationFailed("Invalid username and password"));
                    }
                }
                    emitter.onComplete();
            });

        }, BackpressureStrategy.ERROR);

    }
}*/