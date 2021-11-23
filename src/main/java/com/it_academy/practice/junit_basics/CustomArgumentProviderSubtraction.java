package com.it_academy.practice.junit_basics;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;


import java.util.stream.Stream;

public class CustomArgumentProviderSubtraction implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(2, 2, 0),
                Arguments.of(3, 2, 1),
                Arguments.of(0, 2, -2),
                Arguments.of(10, 5, 5),
                Arguments.of(95, 25, 70),
                Arguments.of(18, 2, 16),
                Arguments.of(-2, 2, -4)
        );
    }
}
