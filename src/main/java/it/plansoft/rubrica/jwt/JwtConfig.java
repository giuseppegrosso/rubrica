package it.plansoft.rubrica.jwt;/* ggrosso created on 06/02/2021 inside the package - it.plansoft.rubrica.jwt */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * classe di lettura delle variabili per jwt.
 */
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationDaysAfter;

    public JwtConfig() {
    }

    public JwtConfig(String secretKey, String tokenPrefix, Integer tokenExpirationDaysAfter) {
        this.secretKey = secretKey;
        this.tokenPrefix = tokenPrefix;
        this.tokenExpirationDaysAfter = tokenExpirationDaysAfter;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationDaysAfter() {
        return tokenExpirationDaysAfter;
    }

    public void setTokenExpirationDaysAfter(Integer tokenExpirationDaysAfter) {
        this.tokenExpirationDaysAfter = tokenExpirationDaysAfter;
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
