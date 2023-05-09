package com.jenschen.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Configuration
public class JwtConfig {

    @Value("${jwt.key-store-path}")
    private String keyStorePath;

    @Value("${jwt.key-store-password}")
    private String keyStorePassword;

    @Value("${jwt.key-alias}")
    private String keyAlias;

    @Value("${jwt.private-key-password}")
    private String privateKeyPassword;

    @Bean
    public KeyStore keyStore(){
        try{
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStorePath);
            keyStore.load(resourceStream, keyStorePassword.toCharArray());
            return keyStore;
        }catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e){
            log.error("unable to load keystore: {}", keyStorePath, e);
        }

        throw new IllegalArgumentException("unable to load keystore");
    }

    @Bean
    public RSAPrivateKey jwtSigningKey(KeyStore keyStore){
        try{
            Key key = keyStore.getKey(keyAlias, privateKeyPassword.toCharArray());
            if(key instanceof RSAPrivateKey){
                return (RSAPrivateKey) key;
            }
        }catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e){
            log.error("unable to load private key from keystore: {} ", keyStorePath, e);
        }

        throw new IllegalArgumentException("unable to load private key");
    }

    @Bean
    public RSAPublicKey jwtValidationKey(KeyStore keyStore){
        try{
            Certificate certificate = keyStore.getCertificate(keyAlias);
            PublicKey key = certificate.getPublicKey();
            if(key instanceof RSAPublicKey){
                return (RSAPublicKey) key;
            }
        }catch (KeyStoreException e){
            log.error("unable to load public key from keystore: {} ", keyStorePath, e);
        }

        throw new IllegalArgumentException("unable to load public key");
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey){
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }
}
