package org.example.textflowproject.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s를 찾을 수 없습니다: %d", resourceName, id));
    }
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
