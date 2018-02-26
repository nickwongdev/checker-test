package com.blizzard.checker;

import com.nickwongdev.NoStubNullCases;
import com.nickwongdev.NullCases;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final NullCases NULL_CASES = new NullCases();
    private static final NoStubNullCases NO_STUB_NULL_CASES = new NoStubNullCases();
    private static final @Nullable String NULL_STRING = null;

    public static void main(final String[] args) {

        // Just a basic JDK test
        final Map<String, String> nullMap = new HashMap<>();
        System.out.println("Length: " + nullMap.get("stuff").length());
    }

    /**
     * All of these methods should have stubs
     */
    public static void doStubChecks() {

        // Should trigger
        final String annotated = NULL_CASES.getAnnotated();
        System.out.println("Length: " + annotated.length());

        // Should trigger
        final String defaulted = NULL_CASES.getDefaulted();
        System.out.println("Length: " + defaulted.length());

        // Should trigger
        final String maybeNull = NULL_CASES.getRandomString();
        System.out.println("Length: " + maybeNull.length());

        // Should NOT trigger
        final String notNull = NULL_CASES.getString();
        System.out.println("Length: " + notNull.length());

        // Should NOT trigger
        NULL_CASES.setNullableString(NULL_STRING);

        // Should trigger
        NULL_CASES.setNonNullString(NULL_STRING);
    }

    public static void doNoStubChecks() {
        // Should trigger
        final String annotated = NO_STUB_NULL_CASES.getAnnotated();
        System.out.println("Length: " + annotated.length());

        // Should trigger
        final String defaulted = NO_STUB_NULL_CASES.getDefaulted();
        System.out.println("Length: " + defaulted.length());

        // Should trigger
        final String maybeNull = NO_STUB_NULL_CASES.getRandomString();
        System.out.println("Length: " + maybeNull.length());

        // Should trigger
        final String notNull = NO_STUB_NULL_CASES.getString();
        System.out.println("Length: " + notNull.length());

        // Should trigger (Due to this being fatal, the next will not trigger)
        NO_STUB_NULL_CASES.setNullableString(NULL_STRING);

        // Should trigger
        NO_STUB_NULL_CASES.setNonNullString(NULL_STRING);
    }
}
