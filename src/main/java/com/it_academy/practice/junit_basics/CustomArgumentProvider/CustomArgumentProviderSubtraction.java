package com.it_academy.practice.junit_basics.CustomArgumentProvider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;


import java.util.stream.Stream;

public class CustomArgumentProviderSubtraction implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(0, 1, -1),
                Arguments.of(-1, 1, -2),
                Arguments.of(1, -1, 2),
                Arguments.of(1, 1, 0),
                Arguments.of(-1, -1, 0),
                Arguments.of(-1.5, -1, -0.5),
                Arguments.of(-1, -1.5, 0.5),
                Arguments.of(-1.5, -1.5, 0),
                Arguments.of(19, 2, 17)

        );
    }
}
