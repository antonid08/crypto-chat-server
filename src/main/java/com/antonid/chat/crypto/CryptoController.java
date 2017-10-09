package com.antonid.chat.crypto;

import com.antonid.chat.models.Encryption;
import com.antonid.chat.models.dao.EncryptionRepository;
import com.antonid.chat.models.dao.KeyRepository;
import com.antonid.chat.security.MyUserDetails;
import com.antonid.chat.models.User;
import com.antonid.chat.models.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CryptoController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptionRepository encryptionRepository;

    @Autowired
    private KeyRepository keyRepository;

    @RequestMapping(value = "/set_encryption", method = POST)
    public void setEncryption(@RequestBody Encryption encryption) { //todo удалять текущее шифрование из бд
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ((MyUserDetails) auth.getPrincipal()).getUser();

        keyRepository.save(encryption.getKey());
        encryptionRepository.save(encryption);

        user.setEncryption(encryption);
        userRepository.save(user);
    }
}
