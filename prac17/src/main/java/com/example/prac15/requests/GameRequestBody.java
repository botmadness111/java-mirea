package com.example.prac15.requests;

public record GameRequestBody(
        String name,
        String creationDate,
        Long authorId
) {
}
