package toptier.manager.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;
import toptier.auth.AuthContext;
import toptier.convert.UserRecordToUser;
import toptier.model.user.User;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class UserManager implements IUserManager {

    @Override
    public String registerUser(User newUser) {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
            .setDisplayName(newUser.getUsername())
            .setEmail(newUser.getEmail())
            .setPassword(newUser.getPassword())
            .setEmailVerified(false)
            .setDisabled(false);

        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().createUserAsync(createRequest).get();
            AuthContext.authenticate(userRecord.getEmail(), newUser.getPassword());
            System.out.println("+++ successfully created user: " + userRecord.getUid());    // logging | todo
        } catch (InterruptedException e) {
            System.out.println("--- create user interrupted:\n" + e);
        } catch (ExecutionException e) {
            System.out.println("--- create user execution failure:\n" + e);
        }

        return Optional.ofNullable(userRecord)
                .map(UserRecord::getUid)
                .orElseThrow(() -> new RuntimeException("--- user created without id"));
    }

    @Override
    public boolean logoutUser(String id) {
        if (AuthContext.getLoggedInUserRecord().getUid().equals(id)) {
            AuthContext.unauthenticate();
            System.out.println("+++ successfully logged out user: " + id);
            return true;
        } else {
            System.out.println(String.format("--- logout failure: user (%s) attempted to logout user (%s)",
                    AuthContext.getLoggedInUserRecord().getUid(), id));
            return false;
        }
    }


    @Override
    public User getUserById(String id) {
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUserAsync(id).get();
        } catch (InterruptedException e) {
            System.out.println("--- get user interrupted:\n" + e);
        } catch (ExecutionException e) {
            System.out.println("--- get user execution failure:\n" + e);
        }

        return Optional.ofNullable(userRecord)
                .map(UserRecordToUser::convert)
                .orElse(null);
    }

    // todo: login: generate token, return it to front end, wait for its validation, and update auth context

    /*public String generateAuthToken(String email, String password) throws InterruptedException, ExecutionException {
        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmailAsync(email).get();
        if (userRecord == null) {
            System.out.println("--- cannot generate auth token for non-existent user: " + email);
            return null;    // return descriptive string | todo
        }
        if (password.equals(userRecord.getProviderData()[0].))

        String uid = "some-uid";

        String customToken = null;
        try {
            customToken = FirebaseAuth.getInstance().createCustomTokenAsync(uid).get();
        } catch (InterruptedException e) {
            System.out.println("--- auth user interrupted:\n" + e);
        } catch (ExecutionException e) {
            System.out.println("--- auth user execution failure:\n" + e);
        }

        // Send token back to client | todo
        System.out.println("Created custom token: " + customToken);
    }*/
}
