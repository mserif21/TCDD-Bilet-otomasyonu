/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

/**
 *
 * @author Eyup Dal
 */
public class MemurTar implements IYolcu{

    @Override
    public int Ucret_hesapla(int x) {
    
        return x - x/4;
    }
    
}
