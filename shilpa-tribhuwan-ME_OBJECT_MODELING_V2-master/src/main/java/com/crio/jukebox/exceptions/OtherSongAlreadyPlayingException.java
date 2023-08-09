package com.crio.jukebox.exceptions;

public class OtherSongAlreadyPlayingException extends RuntimeException {
    public OtherSongAlreadyPlayingException() {
        super();
    }

    public OtherSongAlreadyPlayingException(String msg) {
        super(msg);
    }
}
