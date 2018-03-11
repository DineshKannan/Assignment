package assignment.nobroker.com.nobrokerassignment.models;

import java.io.Serializable;

public class AmenitiesMap implements Serializable
{
    private boolean INTERCOM;

    public boolean getINTERCOM() { return this.INTERCOM; }

    public void setINTERCOM(boolean INTERCOM) { this.INTERCOM = INTERCOM; }

    private boolean AC;

    public boolean getAC() { return this.AC; }

    public void setAC(boolean AC) { this.AC = AC; }

    private boolean RWH;

    public boolean getRWH() { return this.RWH; }

    public void setRWH(boolean RWH) { this.RWH = RWH; }

    private boolean HK;

    public boolean getHK() { return this.HK; }

    public void setHK(boolean HK) { this.HK = HK; }

    private boolean INTERNET;

    public boolean getINTERNET() { return this.INTERNET; }

    public void setINTERNET(boolean INTERNET) { this.INTERNET = INTERNET; }

    private boolean LIFT;

    public boolean getLIFT() { return this.LIFT; }

    public void setLIFT(boolean LIFT) { this.LIFT = LIFT; }

    private boolean CLUB;

    public boolean getCLUB() { return this.CLUB; }

    public void setCLUB(boolean CLUB) { this.CLUB = CLUB; }

    private boolean GP;

    public boolean getGP() { return this.GP; }

    public void setGP(boolean GP) { this.GP = GP; }

    private boolean FS;

    public boolean getFS() { return this.FS; }

    public void setFS(boolean FS) { this.FS = FS; }

    private boolean STP;

    public boolean getSTP() { return this.STP; }

    public void setSTP(boolean STP) { this.STP = STP; }

    private boolean PARK;

    public boolean getPARK() { return this.PARK; }

    public void setPARK(boolean PARK) { this.PARK = PARK; }

    private boolean SC;

    public boolean getSC() { return this.SC; }

    public void setSC(boolean SC) { this.SC = SC; }

    private boolean PB;

    public boolean getPB() { return this.PB; }

    public void setPB(boolean PB) { this.PB = PB; }

    private boolean CPA;

    public boolean getCPA() { return this.CPA; }

    public void setCPA(boolean CPA) { this.CPA = CPA; }

    private boolean SECURITY;

    public boolean getSECURITY() { return this.SECURITY; }

    public void setSECURITY(boolean SECURITY) { this.SECURITY = SECURITY; }

    private boolean POOL;

    public boolean getPOOL() { return this.POOL; }

    public void setPOOL(boolean POOL) { this.POOL = POOL; }

    private boolean GYM;

    public boolean getGYM() { return this.GYM; }

    public void setGYM(boolean GYM) { this.GYM = GYM; }

    private boolean VP;

    public boolean getVP() { return this.VP; }

    public void setVP(boolean VP) { this.VP = VP; }

    private boolean SERVANT;

    public boolean getSERVANT() { return this.SERVANT; }

    public void setSERVANT(boolean SERVANT) { this.SERVANT = SERVANT; }

    @Override
    public String toString() {
        return "AmenitiesMap{" +
                "INTERCOM=" + INTERCOM +
                ", AC=" + AC +
                ", RWH=" + RWH +
                ", HK=" + HK +
                ", INTERNET=" + INTERNET +
                ", LIFT=" + LIFT +
                ", CLUB=" + CLUB +
                ", GP=" + GP +
                ", FS=" + FS +
                ", STP=" + STP +
                ", PARK=" + PARK +
                ", SC=" + SC +
                ", PB=" + PB +
                ", CPA=" + CPA +
                ", SECURITY=" + SECURITY +
                ", POOL=" + POOL +
                ", GYM=" + GYM +
                ", VP=" + VP +
                ", SERVANT=" + SERVANT +
                '}';
    }
}