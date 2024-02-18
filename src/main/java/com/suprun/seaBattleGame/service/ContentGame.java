package com.suprun.seaBattleGame.service;

public class ContentGame {
    public static final String LINE = "";
    public static final String MESSAGE = "Enter operation type, please: \n 1 - INFO. \n 2 - Sing in." +
            "\n 3 - Registration.\n 4 - Play GAME. \n 5 - EXIT.";
    public static final String PATH = "src/main/resources/data/data.txt";
    public static final String PATH_ERR = "File is not exist or is empty or incorrect path";
    public static final String READING_FAIL = "Reading file is fail ";
    public static final String QUESTION_EXIT_MESSAGE = "Do you want to exit o continue? \n" +
            "Enter: \n 1 if you want to exit. \n 2 if you want to continue.";
    public static final String CHOICE_1 = "1";
    public static final String CHOICE_2 = "2";
    public static final String WRONG_MESSAGE = "Incorrect input. Try again.";
    public static final String PLAYER_NAME_MESSAGE = "To log in enter your Name, please:";
    public static final String WRONG_NAME_PLAYER = "WRONG NAME PLAYER";
    public static final String PLAYER_AGE_MESSAGE = "Enter your AGE, please:";
    public static final String PLAYER_IS_EXISTS = "A player by that name already exists. Try again.";
    public static final String MESSAGE_PLAYER_NAME = "Player name - ";
    public static final String MESSAGE_PLAYER_AGE = "Player age - ";
    public static final String MESSAGE_VICTORIES_COUNT = "Victories count - ";
    public static final String MESSAGE_LESIONS_COUNT = "lesions count - ";
    public static final String GOOD_REGISTER_MESSAGE = ", you have been successfully registered";
    public static final String JSON_ADMIN_REGISTER = "{\"playersList\":{\"Admin\":{\"name\":\"Admin\",\"age\":32," +
            "\"password\":\"AlphaRomeo4c\",\"victoriesCount\":50,\"lesionsCount\":0,\"isActive\":true,\"role\":\"ADMIN\"}}}";
    public static final String PLAYER_FOR_REGISTR_NAME_MESSAGE = "Let's start registration. Enter your Name, please:";
    public static final String PLAYER_NOT_EXIST_MESSAGE = "A player by that name doesn't exist. Please register.";
    public static final String PLAYER_AGE_ERROR_MESSAGE = "Incorrect data has been entered.";
    public static final String PASSWORD_ASK_CODE_MESSAGE = "Enter your password, please: ";
    public static final String MESSAGE_SHIP_UNDER_FIRE = "Our ship is under fire!";
    public static final String MESSAGE_KEEP_GOING = "Nice shot, keep going!";
    public static final String MESSAGE_TRY_AGAIN = "The order is unclear, data format error!\nTry again!";
    public static final String MESSAGE_AWAITING_ORDERS = "Awaiting orders:";

    public static String MESSAGE_HEADER = "Your statistics: ";
    public static final String PASSWORD_FOR_REGISTER_CODE_MESSAGE = "Enter password. Password must contain:\n" +
            "- at least 8 characters\n" +
            "- at least 1 number\n" +
            "- at least 1 upper case letter\n" +
            "- must contain at least 1 lower case letter\n" +
            "- at least 1 special character\n" +
            "- not contain any spaces\n" +
            "Enter password, please: ";
    public static final String WRONG_PASSWORD_MESSAGE = "The password entered is incorrect.";
    public static final String BLOCK_PLAYER_MESSAGE = "Your account has been blocked for 24 hours.";
    public static final String INFO_BLOCK_PLAYER_MESSAGE = "Your account has been blocked for 24 hours. " +
            "There is time left to unlock the card: ";
    public static final String UNBLOCK_PLAYER_MESSAGE = "Your account has been unblocked.";
    public static final String UNKNOWN_OPERATION_MESSAGE = "Unknown operation, try again";
    public static final String HOURS_MESSAGE = " hours";
    public static final String GOODBYE_MESSAGE = "Thank you for taking advantage of our game. We look forward " +
            "to more battles. Have a nice day!";

    private ContentGame() {

    }
}
