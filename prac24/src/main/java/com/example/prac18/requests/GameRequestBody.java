package com.example.prac18.requests;

public record GameRequestBody(
        String name,
        String creationDate,
        Long authorId
) {
}
