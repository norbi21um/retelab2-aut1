package hu.bme.aut.retelab2.service.helper;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SecretGenerator {
    private static final char[] CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private static final Random RND = new Random();

    public  String generate() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 6; i++)
            sb.append(CHARS[RND.nextInt(CHARS.length)]);
        return sb.toString();
    }
}
