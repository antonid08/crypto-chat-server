package com.antonid.chat;


import com.antonid.chat.models.User;
import com.antonid.chat.models.dao.UserRepository;
import com.antonid.chat.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class FirebaseTokenController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/set_firebase_token", method = POST)
    public void message(@RequestBody String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ((MyUserDetails) auth.getPrincipal()).getUser();
        user.setFcmToken(token);
        userRepository.save(user);
    }

}
