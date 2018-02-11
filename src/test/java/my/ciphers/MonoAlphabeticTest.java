package my.ciphers;

import my.ciphers.types.Cipher;
import my.ciphers.types.MonoAlphabeticCipher;
import org.junit.Assert;
import org.junit.Test;

public class MonoAlphabeticTest {

    @Test
    public void test() {

        final Cipher cipher = new MonoAlphabeticCipher();
        final String text = "Sanfoundry";

        Assert.assertEquals("LQFYGXFRKN", cipher.encode(text));
        Assert.assertEquals("sanfoundry", cipher.decode(cipher.encode(text)));
    }
}
