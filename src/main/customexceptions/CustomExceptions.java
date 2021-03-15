package main.customexceptions;

public class CustomExceptions extends Exception{
    public CustomExceptions(String message) {
        super(message);
    }

    public static class MessageArchiveNotFound extends CustomExceptions {
        public MessageArchiveNotFound(String message) {
            super(message);
        }
    }

    public static class InputImageNotFound extends CustomExceptions {
        public InputImageNotFound(String message) {
            super(message);
        }
    }
}
