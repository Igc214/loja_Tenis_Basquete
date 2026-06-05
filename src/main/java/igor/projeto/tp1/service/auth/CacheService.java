package igor.projeto.tp1.service.auth;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CacheService {

    private static final Duration TOKEN_TTL = Duration.ofMinutes(5);
    private static final SecureRandom RANDOM = new SecureRandom();

    private final Map<String, TokenEntry> tokens = new ConcurrentHashMap<>();

    public String getTokenSenha(String login) {
        limparExpirados();

        String token;
        do {
            token = String.format("%06d", RANDOM.nextInt(1_000_000));
        } while (tokens.containsKey(token));

        tokens.put(token, new TokenEntry(login, Instant.now().plus(TOKEN_TTL)));
        return token;
    }

    public boolean checkToken(String login, String token) {
        TokenEntry entry = tokens.get(token);
        if (entry == null || entry.expirou()) {
            invalidateToken(token);
            return false;
        }

        return entry.login().equals(login);
    }

    public void invalidateToken(String token) {
        tokens.remove(token);
    }

    private void limparExpirados() {
        tokens.entrySet().removeIf(entry -> entry.getValue().expirou());
    }

    private record TokenEntry(String login, Instant expiresAt) {
        boolean expirou() {
            return Instant.now().isAfter(expiresAt);
        }
    }
}
