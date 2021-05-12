package inverno_2019_2020;

public class RegisterException extends Exception {

    public RegisterException(String msg) {
        super(msg);
    }

    public RegisterException() {
        super("Erro nas datas dos registos");
    }
}
