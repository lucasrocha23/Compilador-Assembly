/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador_assembly;

import java.awt.Color;

/**
 *
 * @author lucas
 */
public class HiliteWord {

    int _position;  
    String _word;
    Color _color;

    public HiliteWord(String word, int position, Color color) {
        _position = position;   
        _word = word;
        _color = color;
    }
}