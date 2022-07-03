/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Eyup Dal
 */
public class Bilet {
    
    private int tc ;
    private int no;
    private int ucret;
    
    private String tarih;
    private String tarife;
    private String tren_ad;
    private String koltuk_no;

    public Bilet() {
    }

    public Bilet(int tc, int no, int ucret, String tarih, String tarife, String tren_ad, String koltuk_no) {
        this.tc = tc;
        this.no = no;
        this.ucret = ucret;
        this.tarih = tarih;
        this.tarife = tarife;
        this.tren_ad = tren_ad;
        this.koltuk_no = koltuk_no;
    }
    
    

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getUcret() {
        return ucret;
    }

    public void setUcret(int ucret) {
        this.ucret = ucret;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getTarife() {
        return tarife;
    }

    public void setTarife(String tarife) {
        this.tarife = tarife;
    }

    public String getTren_ad() {
        return tren_ad;
    }

    public void setTren_ad(String tren_ad) {
        this.tren_ad = tren_ad;
    }

    public String getKoltuk_no() {
        return koltuk_no;
    }

    public void setKoltuk_no(String koltuk_no) {
        this.koltuk_no = koltuk_no;
    }
    
    
    
    
    
}
