package toptier.convert;

import com.google.firebase.auth.UserRecord;
import toptier.model.user.User;

import java.util.Optional;

public class UserRecordToUser {

    public static User convert(UserRecord userRecord) {
        User user = null;
        if (userRecord != null) {
            user = new User();  // only need email and username for now
            user.setEmail(Optional.ofNullable(userRecord.getEmail()).orElse(null));
            user.setUsername(Optional.ofNullable(userRecord.getDisplayName()).orElse(null));
        }
        return user;
    }
}
