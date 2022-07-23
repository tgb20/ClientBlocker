package gg.tgb.clientblocker;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientParser {
    static boolean clientAllowed(List<String> allowedClients, String client) {

        boolean allowed = false;

        for(int i = 0; i < allowedClients.size(); i++) {
            Pattern r = Pattern.compile(allowedClients.get(i));
            Matcher m = r.matcher(client);
            if(m.matches()) {
                allowed = true;
                break;
            }
        }

        return allowed;
    }
}
