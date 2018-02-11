package my.ciphers.types;

public class MonoAlphabeticCipher implements Cipher {

    public final static char p[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'};
    public final static char ch[] = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O',
            'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C',
            'V', 'B', 'N', 'M'};

    @Override
    public String encode(String s) {
        s = s.toLowerCase();
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (p[j] == s.charAt(i)) {
                    encoded.append(ch[j]);
                    break;
                }
            }
        }
        return encoded.toString();
    }

    @Override
    public String decode(String s) {
        s = s.toUpperCase();
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (ch[j] == s.charAt(i)) {
                    encoded.append(p[j]);
                    break;
                }
            }
        }
        return encoded.toString();
    }

}
