package com.crio.jukebox.exceptions;

public class SongAlreadyExistException extends RuntimeException {
    public SongAlreadyExistException() {
        super();
    }

    public SongAlreadyExistException(String str) {
        super(str);
    }
}

