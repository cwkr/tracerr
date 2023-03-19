package de.cwkr.util;

import java.security.SecureRandom;
import java.util.Random;
import org.apache.commons.lang3.Validate;

/**
 * Simple Universally Unique Lexicographically Sortable Identifier (ULID) generator
 *
 * @author Christian Winkler
 * @since 1.3.0
 * @see <a href="https://github.com/ulid/spec">https://github.com/ulid/spec</a>
 */
public final class Ulids {
    private static final int MASK = 0x1F;
    private static final int MASK_BITS = 5;
    private static final long MAX_TIMESTAMP = 0xFFFFFFFFFFFFL;
    private static final String CROCKFORD_BASE32_CHARS = "0123456789ABCDEFGHJKMNPQRSTVWXYZ";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private Ulids() {
    }

    /**
     * Generate ULID using {@code System.currentTimeMillis()} and {@link SecureRandom}
     * @return new random ULID with current timestamp
     */
    public static String ulid() {
        return ulidOf(System.currentTimeMillis(), SECURE_RANDOM);
    }

    /**
     * Generate ULID using given parameters
     *
     * @param timestamp time in milliseconds as unix epoch
     * @param random random number generator to use for entropy
     * @return generated ULID
     */
    public static String ulidOf(long timestamp, Random random) {
        Validate.inclusiveBetween(0L, MAX_TIMESTAMP, timestamp);
        Validate.notNull(random, "random must not be null");
        char[] buffer = new char[26];

        // write 48 bit (10 chars ~ 50 bit) timestamp
        writeBase32(buffer, timestamp, 10, 0);
        // write 40 bit (8 chars) entropy
        writeBase32(buffer, random.nextLong(), 8, 10);
        // write 40 bit (8 chars) entropy
        writeBase32(buffer, random.nextLong(), 8, 18);

        return new String(buffer);
    }

    private static void writeBase32(char[] buffer, long value, int count, int offset) {
        for (int i = 0; i < count; i++) {
            int index = (int) ((value >>> ((count - i - 1) * MASK_BITS)) & MASK);
            buffer[offset + i] = CROCKFORD_BASE32_CHARS.charAt(index);
        }
    }
}
