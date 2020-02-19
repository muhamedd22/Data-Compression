
package lz78;

public class Tag {
    
   public int index;
   public char next_symbol;

    public Tag(int index, char next_symbol) {
        this.index = index;
        this.next_symbol = next_symbol;
    }
    
    public Tag(){
        this.index=0;
        this.next_symbol=' ';
    }
    
    
    
    
    
    
}
