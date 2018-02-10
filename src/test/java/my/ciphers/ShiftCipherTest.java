package my.ciphers;

import my.ciphers.types.CaesarShiftCipher;
import org.junit.Assert;
import org.junit.Test;

public class ShiftCipherTest {

    @Test
    public void test() {

        int shift = 3;

        final CaesarShiftCipher cipher = new CaesarShiftCipher(shift);

        Assert.assertEquals("def", cipher.encode("abc"));
        Assert.assertEquals("abc", cipher.decode("def"));
    }

    @Test
    public void testCapsText() {

        int shift = 3;

        final CaesarShiftCipher cipher = new CaesarShiftCipher(shift);

        Assert.assertEquals("DEF", cipher.encode("ABC"));
        Assert.assertEquals("ABC", cipher.decode("DEF"));
    }
    
    @Test
    public void testMixedText() {

        int shift = 3;

        final CaesarShiftCipher cipher = new CaesarShiftCipher(shift);

        Assert.assertEquals("Def", cipher.encode("Abc"));
        Assert.assertEquals("aBC", cipher.decode("dEF"));
    }
}
