package com.kalra.docker.client;

import java.util.List;

public record LanguageResponse(String country, List<String> languages) {}