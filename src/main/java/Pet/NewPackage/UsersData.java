package Pet.NewPackage;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.function.BiFunction;

@Singleton
public class UsersData {

    HashMap<String, String> level = new HashMap<>();
    HashMap<String, String> usr = new HashMap<>();

    public UsersData() {
        System.err.println("inside Default const");

        usr.put("HD", "12345");
        level.put("HD", "ADMIN");

        usr.put("ST", "12345");
        level.put("ST", "VIEWER");
    }


    /*@Inject
    MessageDigest md;
    {
        try {
            md.update(toChapter1);
            MessageDigest tc1 = md.clone();
            byte[] toChapter1Digest = tc1.digest();
            md.update(toChapter2);
        } catch (CloneNotSupportedException cnse) {
            throw new DigestException("couldn't make digest of partial content");
        }
    }*/

    @PostConstruct
    public void constructor() {

    }

    /*public Single<Optional<UsersData>> validUser(String name){
        return Single.just(Optional.ofNullable(this.get(name)));
    }*/

    public String getUserPassword(String username) {
        System.out.println("------>>>" + String.valueOf(usr.get(username)));
        return usr.get(username);
    }

    public String getUserRole(String username) {
        System.out.println(level.get(username));
        return level.get(username);
    }
}
