package model;

import java.util.Objects;

public class Issue {
	public Client Client;
    public int Year;
    public int Month;
    public float ToPay;
    public float Paid;
    
    public Issue(){
        this.Client = new Client();
        this.Year = 0;
        this.Month = 0;
        this.ToPay = 0;
        this.Paid = 0;
    }
    
    public Issue(Issue issue){
        this.Client = issue.Client;
        this.Year = issue.Year;
        this.Month = issue.Month;
        this.ToPay = issue.ToPay;
        this.Paid = issue.Paid;
    }
    
    public Issue(Client client, int year, int month, float toPay, float paid){
        this.Client = client;
        this.Year = year;
        this.Month = month;
        this.ToPay = toPay;
        this.Paid = paid;
    }
    
    @Override
    public String toString(){
        String r = String.format("%s" + System.getProperty("line.separator") + "%d, %d, %2.0f, %2.0f", 
                this.Client.toString(), this.Year, this.Month, this.ToPay, this.Paid);
        return r;
    }
    
    @Override 
    public boolean equals(Object object){
        if(object == null) return false;
        if(!(object instanceof Issue)) return false;
        Issue i = (Issue)object;
        if(i.Client == null) return false;
        if((i.Month == this.Month) && (i.Year == this.Year) && (i.Client.equals(this.Client))) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.Client);
        hash = 71 * hash + this.Year;
        hash = 71 * hash + this.Month;
        hash = 71 * hash + Float.floatToIntBits(this.ToPay);
        hash = 71 * hash + Float.floatToIntBits(this.Paid);
        return hash;
    }
}
