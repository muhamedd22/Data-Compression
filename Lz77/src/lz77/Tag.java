/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz77;

/**
 *
 * @author mbakr
 */
public class Tag {
    public int position;
    public int length;
    public char nextSymbol;

    Tag() {
        position  = 0;
        length = 0;
        nextSymbol = ' ';
    }
    
    
}
