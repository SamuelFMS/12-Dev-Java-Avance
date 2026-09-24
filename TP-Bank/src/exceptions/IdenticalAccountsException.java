package exceptions;

public class IdenticalAccountsException extends Exception{
    public IdenticalAccountsException(){
        super("L'émetteur et le receveur ne peux pas etre identique");
    }
}
