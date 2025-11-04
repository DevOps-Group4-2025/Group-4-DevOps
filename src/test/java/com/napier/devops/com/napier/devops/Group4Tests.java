package com.napier.devops;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Minimal smoke tests to ensure Surefire finds at least one test.
 * This test avoids loading Spring context or any DB connections so it is
 * safe to run in CI regardless of environment.
 */
public class Group4Tests {

    @Test
    void placeholderTest() {
        // simple assertion to ensure the test framework runs
        assertTrue(1 + 1 == 2);
    }
}
