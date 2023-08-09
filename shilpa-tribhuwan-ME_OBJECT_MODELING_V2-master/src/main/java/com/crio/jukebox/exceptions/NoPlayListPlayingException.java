package com.crio.jukebox.exceptions;

public class NoPlayListPlayingException extends RuntimeException {
    public NoPlayListPlayingException() {
        super();
    }

    public NoPlayListPlayingException(String msg) {
        super(msg);
    }
}

