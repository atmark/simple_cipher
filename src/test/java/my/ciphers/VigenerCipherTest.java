package my.ciphers;

import my.ciphers.types.VigenerCipher;
import my.ciphers.types.Cipher;
import org.junit.Assert;
import org.junit.Test;

public class VigenerCipherTest {

    @Test
    public void test() {

        final String key = "VIGENERECIPHER";

        final Cipher cipher = new VigenerCipher(key);
        final String text = "Beware the Jabberwock, my son! The jaws that bite, the claws that catch!";

        Assert.assertEquals("WMCEEIKLGRPIFVMEUGXQPWQVIOIAVEYXUEKFKBTALVXTGAFXYEVKPAGY", cipher.encode(text));
        Assert.assertEquals("BEWARETHEJABBERWOCKMYSONTHEJAWSTHATBITETHECLAWSTHATCATCH", cipher.decode(cipher.encode(text)));
    }
}
