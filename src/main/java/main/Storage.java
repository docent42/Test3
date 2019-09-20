package main;

import response.Pasta;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Storage
{
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    private static HashMap<String, Pasta> pastas = new HashMap<>();

    static String addPasta(Pasta pasta)
    {
        String link = randomString();
        pastas.put(link,pasta);
        return link;
    }

    static List<Pasta> getLastTenPastas()
    {
        return pastas.values().stream()
                .filter(Pasta::isAccess)
                .limit(10)
                .collect(Collectors.toList());
    }

    static Pasta getPasta(String link)
    {
        LocalDateTime expDate = LocalDateTime.parse(pastas.get(link).getExpDate().trim());

        if (expDate.compareTo(LocalDateTime.now()) > 0) return pastas.get(link);
        return null;
    }

    private static String randomString()
    {
        StringBuilder sb = new StringBuilder( 15 );
        for( int i = 0; i < 15; i++ )
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}


