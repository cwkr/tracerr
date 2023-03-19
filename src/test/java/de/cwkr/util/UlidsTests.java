package de.cwkr.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UlidsTests {

    @Test
    public void ulid() {
        String ulid = Ulids.ulid();
        assertNotNull(ulid);
        assertEquals(26, ulid.length());
    }

    @Test
    public void ulidOf_min(@Mock Random random) {
        when(random.nextLong()).thenReturn(0L);
        String ulid = Ulids.ulidOf(0L, random);
        verify(random, times(2)).nextLong();
        assertEquals("00000000000000000000000000", ulid);
    }

    @Test
    public void ulidOf_fixed(@Mock Random random) {
        when(random.nextLong()).thenReturn(1234567890L, 987654321L);
        String ulid = Ulids.ulidOf(1590739572530L, random);
        verify(random, times(2)).nextLong();
        assertEquals("01E9FQF5SJ014SC0PJ00XDWT5H", ulid);
    }

    @Test
    public void ulidOf_max(@Mock Random random) {
        when(random.nextLong()).thenReturn(Long.MAX_VALUE);
        String ulid = Ulids.ulidOf(0xFFFFFFFFFFFFL, random);
        verify(random, times(2)).nextLong();
        assertEquals("7ZZZZZZZZZZZZZZZZZZZZZZZZZ", ulid);
    }

    @Test
    public void ulidOf_invalidTimestamp() {
        assertThrows(IllegalArgumentException.class, () -> {
            Ulids.ulidOf(Long.MAX_VALUE, null);
        });
    }
}
