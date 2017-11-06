package toptier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import toptier.manager.user.IUserManager;
import toptier.model.user.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    // create auth controller and move auth-specific methods there | todo

    @Autowired
    private IUserManager userManager;

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@Valid @RequestBody User user) {
        return userManager.registerUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") String id) {
        return userManager.getUserById(id);
    }

    @RequestMapping(value = "{id}/logout")
    public boolean logout(@PathVariable("id") String id) {
        return userManager.logoutUser(id);
    }

    // todo: login, custom token creation and validation (see UserManager/AuthContext for more details)

}
