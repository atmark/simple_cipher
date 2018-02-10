package my.ciphers.types;

public class CaesarShiftCipher implements Cipher {

    private final int shift;

    public CaesarShiftCipher(int shift) {
        this.shift = shift;
    }

    @Override
    public String decode(String text) {
        return encode(text, 26 - shift);
    }

    @Override
    public String encode(String text) {
        return encode(text, shift);
    }

    public String encode(String text, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : text.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }

}
