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
public class OgrenciTar implements IYolcu{

    @Override
    public int Ucret_hesapla(int x) {
       
        return x/2;
    }
    
    
}
