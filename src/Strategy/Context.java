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
public class Context {
    
    private IYolcu yolcu;

    public Context(IYolcu yolcu) {
        this.yolcu = yolcu;
    }
    
    public int Strategy(int x){
      return yolcu.Ucret_hesapla(x);
   }
}
