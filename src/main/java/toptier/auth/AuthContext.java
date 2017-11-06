package toptier.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import toptier.convert.UserRecordToUser;
import toptier.model.user.User;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class AuthContext {

    public static void init() {
        SecurityContextHolder.setContext(new SecurityContextImpl());
    }

    public static void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email, password);
        authToken.setAuthenticated(true);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(email, password));
    }

    public static void unauthenticate() {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
    }

    public static UserRecord getLoggedInUserRecord() {
        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        UserRecord loggedInUser = null;
        if (authToken.isAuthenticated()) {
            try {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                loggedInUser = FirebaseAuth.getInstance().getUserByEmailAsync(user.getEmail()).get();
            } catch (InterruptedException e) {
                System.out.println("--- logged in user retrieval interrupted:\n" + e);
            } catch (ExecutionException e) {
                System.out.println("--- logged in user retrieval failure:\n" + e);
            }
        } else {
            System.out.println("--- cannot get logged in user: no user currently authenticated");
        }

        return loggedInUser;
    }

    public static User getLoggedInUser() {
        return Optional.ofNullable(getLoggedInUserRecord())
                .map(UserRecordToUser::convert)
                .orElse(null);
    }
}
