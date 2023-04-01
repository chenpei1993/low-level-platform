package com.jenschen.helper;

import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.AlgorithmUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtHelper {
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public JwtHelper(RSAPublicKey publicKey, RSAPrivateKey privateKey){
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String createJwtForClaims(String subject, Map<String, String> data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.now().toEpochMilli());
        calendar.add(Calendar.DATE, 1);


        JWT jwt = JWT.create().setSubject(subject);

        data.forEach(jwt::setPayload);

        JWTSigner signer = JWTSignerUtil.rs256(privateKey);

        return jwt.setNotBefore(new Date())
                .setExpiresAt(calendar.getTime())
                .setSigner(signer)
                .sign();
    }
}
