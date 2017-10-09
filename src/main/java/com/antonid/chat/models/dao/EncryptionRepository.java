package com.antonid.chat.models.dao;

import com.antonid.chat.models.Encryption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncryptionRepository extends JpaRepository<Encryption, Long> {

}
