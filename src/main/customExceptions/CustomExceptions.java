package main.customExceptions;

public class CustomExceptions extends Exception{
    public CustomExceptions(String message) {
        super(message);
    }

    public static class MessageArchiveNotFoundException extends CustomExceptions {
        public MessageArchiveNotFoundException(String message) {
            super(message);
        }
    }

    public static class InputImageNotFoundException extends CustomExceptions {
        public InputImageNotFoundException(String message) {
            super(message);
        }
    }

    public static class ImageSizeNotEnoughException extends CustomExceptions {
        public ImageSizeNotEnoughException(String message) {
            super(message);
        }
    }
}
