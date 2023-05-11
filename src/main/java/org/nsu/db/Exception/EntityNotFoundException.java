package org.nsu.db.Exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(){
        super();
    }
    public EntityNotFoundException(String msg){
        super(msg);
    }
}
