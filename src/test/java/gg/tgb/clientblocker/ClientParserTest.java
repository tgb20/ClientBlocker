package gg.tgb.clientblocker;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class ClientParserTest {

    List<String> allowClients1 = Arrays.asList("vanilla");
    List<String> allowClients2 = Arrays.asList("vanilla", "fabric");

    List<String> allowClients3 = Arrays.asList("vanilla", "fabric", "lunarclient");

    List<String> allowClients4 = Arrays.asList("lunarclient", "^lunarclient.*$");

    @Test
    public void testClientAllowed1Client() {
        boolean vanillaAllowed = ClientParser.clientAllowed(allowClients1, "vanilla");
        boolean fabricAllowed = ClientParser.clientAllowed(allowClients1, "fabric");
        assertEquals(true, vanillaAllowed);
        assertEquals(false, fabricAllowed);
    }

    @Test
    public void testClientAllowed2Clients() {
        boolean vanillaAllowed = ClientParser.clientAllowed(allowClients2, "vanilla");
        boolean fabricAllowed = ClientParser.clientAllowed(allowClients2, "fabric");
        assertEquals(true, vanillaAllowed);
        assertEquals(true, fabricAllowed);
    }

    @Test
    public void testClientAllowedNoRegex() {
        boolean lunarAllowed = ClientParser.clientAllowed(allowClients3, "lunarclient:7fd00a4");
        assertEquals(false, lunarAllowed);
    }

    @Test
    public void testClientAllowedWithRegex() {
        boolean lunarAllowed = ClientParser.clientAllowed(allowClients4, "lunarclient");
        assertEquals(true, lunarAllowed);

        boolean lunarVersionAllowed = ClientParser.clientAllowed(allowClients4, "lunarclient:7fd00a4");
        assertEquals(true, lunarVersionAllowed);
    }

}
