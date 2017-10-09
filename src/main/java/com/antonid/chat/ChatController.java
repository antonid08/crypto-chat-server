package com.antonid.chat;

import com.antonid.chat.crypto.Cipher;
import com.antonid.chat.crypto.CipherProvider;
import com.antonid.chat.models.User;
import com.antonid.chat.models.dao.UserRepository;
import com.antonid.chat.security.MyUserDetails;
import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Duration;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/is_interlocutor_exists/{username}", method = GET)
    public @ResponseBody
    Boolean isInterlocutorExists(@PathVariable("username") String username) {
        return userRepository.findByUsername(username) != null;
    }

    @RequestMapping(value = "/message/to/{username}", method = POST)
    public ResponseEntity message(@RequestBody Message message, @PathVariable("username") String username) {
        User sender = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Cipher senderCipher = new CipherProvider().getCipher(sender.getEncryption().getType());

        String decryptedMessage = senderCipher.decrypt(message.getText(), sender.getEncryption().getKey()
                .getPrivateKey());

        User receiver = userRepository.findByUsername(username);
        if (receiver == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("No such receiver: %s", username));
        }

        Cipher receiverCipher = new CipherProvider().getCipher(receiver.getEncryption().getType());
        String encryptedMessage = receiverCipher.encrypt(decryptedMessage, receiver.getEncryption().getKey()
                .getPublicKey());

        sendMessage(receiver, new Message(message.getSenderUsername(), encryptedMessage));

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    private void sendMessage(User receiver, Message message) {
        if (receiver.getFcmToken() == null) {
            throw new IllegalStateException(String.format("User: %s has null fcmToken. Can't send message.", receiver));
        }

        FcmClient client = new FcmClient(PropertiesBasedSettings.createFromDefault());

        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(1))
                .build();

        FcmMessageResponse response = client.send(new DataUnicastMessage(options, receiver.getFcmToken(),
                message));

        new DataUnicastMessage(options, receiver.getFcmToken(),
                message).getPayload();

    }

}
