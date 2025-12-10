package org.example.textflowproject.common.exception;

public class WorkNotFoundException extends RuntimeException {
    public WorkNotFoundException(Long workId) {
        super("작품을 찾을 수 없습니다: " + workId);
    }
    
    public WorkNotFoundException(String message) {
        super(message);
    }
}
