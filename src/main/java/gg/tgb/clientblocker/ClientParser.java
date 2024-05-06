package gg.tgb.clientblocker;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientParser {
    static boolean clientAllowed(List<String> allowedClients, String client) {

        boolean allowed = false;

        for (String allowedClient : allowedClients) {
            Pattern r = Pattern.compile(allowedClient);
            Matcher m = r.matcher(client);
            if (m.matches()) {
                allowed = true;
                break;
            }
        }

        return allowed;
    }
}
