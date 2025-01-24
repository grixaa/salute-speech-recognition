package ru.itdt.ai.service;

import com.google.common.cache.Cache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.GenericType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import ru.itdt.ai.service.bean.SberGetTokenResponse;
import ru.itdt.ai.service.client.SberRestClient;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.TimeUnit.MINUTES;

@ApplicationScoped
public class AuthenticationService {

    @RestClient
    SberRestClient sberRestClient;

    final Cache<String, String> cache;
    private static final String KEY_CACHE = "token";

    public AuthenticationService() {
        this.cache = newBuilder()
                .maximumSize(1)
                .expireAfterWrite(28, MINUTES)
                .build();
    }

    public void putToken(final String token) {
        this.cache.put(KEY_CACHE, token);
    }

    public String getToken() {
        String token = cache.getIfPresent(KEY_CACHE);
        if (ofNullable(token).isEmpty()) {
            token = sberRestClient.getToken("SALUTE_SPEECH_PERS")
                    .readEntity(new GenericType<SberGetTokenResponse>(){})
                    .access_token();
            putToken(token);
        }

        return "Bearer " + token;
    }
}
