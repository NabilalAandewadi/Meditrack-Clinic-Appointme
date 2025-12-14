package com.airtribe.meditrack.repository;

public interface Searchable {
    boolean matches(String query);

    // Default method
    default boolean isRelevant(String query) {
        return matches(query.toLowerCase());
    }
}
