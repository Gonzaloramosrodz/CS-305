package com.snhu.sslserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
public class ChecksumController {

    @GetMapping("/hash")
    public String getChecksum(@RequestParam(value = "data", defaultValue = "Hello World Check Sum!") String data) {
        try {
            // Calculate SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            String encodedHash = Base64.getEncoder().encodeToString(hash);

            // Add name to fulfill project requirement
            return "Developer: Gonzalo Ramos\nData: " + data + "\nSHA-256 Checksum (Base64): " + encodedHash;

        } catch (NoSuchAlgorithmException e) {
            return "Error generating checksum: " + e.getMessage();
        }
    }
}
