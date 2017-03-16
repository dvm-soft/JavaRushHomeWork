package com.javarush.test.level23.lesson06.task02;

import static com.javarush.test.level23.lesson06.task02.Solution.Constants.*;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {

    static final class Constants
    {
        public final static String ACCESS_DENIED = "Access is denied.";
        public final static String USER_BANNED = "User is banned.";
        public final static String USER_NOT_AUTHORIZED = "User is not authorized.";
        public final static String SERVER_NOT_ACCESSIBLE = "Server is not accessible for now.";
    }
    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(SERVER_NOT_ACCESSIBLE);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(SERVER_NOT_ACCESSIBLE, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(USER_NOT_AUTHORIZED);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(USER_NOT_AUTHORIZED, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(USER_BANNED);
        }

        public BannedUserException(Throwable cause) {
            super(USER_BANNED, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(ACCESS_DENIED);
        }

        public RestrictionException(Throwable cause) {
            super(ACCESS_DENIED, cause);
        }
    }
}
