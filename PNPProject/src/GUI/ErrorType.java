package GUI;

public enum ErrorType {
    NAME(0,"Name-Error", "The name you entered is not valid!\nPlease check the following criteria:\n• The name is not null\n• The name is not empty\n• The name is three or more characters long");



    private final int id;
    private final String name;
    private final String message;

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }

    private ErrorType(int id, String name, String message){
        this.id = id;
        this.name = name;
        this.message = message;
    }
}
