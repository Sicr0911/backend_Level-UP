package com.ecomerket.security;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class TokenJwtConfig {
    public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("EstaEsUnaClaveSecretaMuySegura123456!".getBytes());

    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static final String PREFIX_TOKEN = "Bearer ";

    public static final String AUTHORITIES = "authorities";
}