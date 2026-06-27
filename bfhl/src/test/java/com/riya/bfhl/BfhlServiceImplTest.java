package com.riya.bfhl;

import com.riya.bfhl.dto.BfhlRequest;
import com.riya.bfhl.dto.BfhlResponse;
import com.riya.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BfhlServiceImpl.
 * Covers all examples from the assignment + edge cases.
 */
class BfhlServiceImplTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Example A: ["a", "1", "334", "4", "R", "$"]
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Example A - basic mix of alpha, numbers, special char")
    void testExampleA() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("a", "1", "334", "4", "R", "$")
        );

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("riya_patial_02042005", response.getUserId());
        assertEquals("riya1432.be23@chitkarauniversity.edu.in", response.getEmail());
        assertEquals("2311981432", response.getRollNumber());

        assertEquals(Arrays.asList("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
        assertEquals(Arrays.asList("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Example B: ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Example B - multiple special chars and alphabets")
    void testExampleB() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b")
        );

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("5"), response.getOddNumbers());
        assertEquals(Arrays.asList("2", "4", "92"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "Y", "B"), response.getAlphabets());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Example C: ["A", "ABCD", "DOE"]
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Example C - multi-character alphabetic tokens only")
    void testExampleC() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("A", "ABCD", "DOE")
        );

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Edge Cases
    // ─────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Empty data array returns zeroed response")
    void testEmptyArray() {
        BfhlRequest request = new BfhlRequest(Collections.emptyList());
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only numbers - no alphabets or special chars")
    void testOnlyNumbers() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("3", "6", "11", "100")
        );

        BfhlResponse response = service.processData(request);

        assertEquals(Arrays.asList("3", "11"), response.getOddNumbers());
        assertEquals(Arrays.asList("6", "100"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("120", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only alphabets - no numbers")
    void testOnlyAlphabets() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("z", "m", "A")
        );

        BfhlResponse response = service.processData(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(Arrays.asList("Z", "M", "A"), response.getAlphabets());
        assertEquals("0", response.getSum());
        // chars: z,m,A → reversed AmZ → alternating: A,m,Z → "AmZ"
        assertEquals("AmZ", response.getConcatString());
    }

    @Test
    @DisplayName("Zero is even and contributes to sum")
    void testZeroIsEven() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("0", "7")
        );

        BfhlResponse response = service.processData(request);

        assertEquals(Arrays.asList("0"), response.getEvenNumbers());
        assertEquals(Arrays.asList("7"), response.getOddNumbers());
        assertEquals("7", response.getSum());
    }

    @Test
    @DisplayName("Alphabets converted to uppercase in response")
    void testAlphabetsUppercase() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("hello", "World")
        );

        BfhlResponse response = service.processData(request);

        assertEquals(Arrays.asList("HELLO", "WORLD"), response.getAlphabets());
    }

    @Test
    @DisplayName("user_id is always riya_patial_02042005")
    void testUserId() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("1"));
        BfhlResponse response = service.processData(request);
        assertEquals("riya_patial_02042005", response.getUserId());
    }

    @Test
    @DisplayName("sum is returned as String not number")
    void testSumIsString() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("10", "20"));
        BfhlResponse response = service.processData(request);
        assertInstanceOf(String.class, response.getSum());
        assertEquals("30", response.getSum());
    }
}
