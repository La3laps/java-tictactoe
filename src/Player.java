public class Player extends Cell {
    private String representation = " X ";

    @Override
    public void setRepresentation(String rep){
        this.representation = rep;
    }
    
    @Override
    public String getRepresentation(){
        return representation;
    }
}