public class Player extends Cell {
    private String representation = "   ";

    @Override
    public void setRepresentation(String rep){
        this.representation = rep;
    }

    @Override
    public String getRepresentation(){
        return representation;
    }
}