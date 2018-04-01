package model;

import java.util.Objects;

public class Issue {
    private Client Client;
    private int Year;
    private int Month;
    private float ToPay;
    private float Paid;
    
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

    public model.Client getClient() {
        return Client;
    }

    public void setClient(model.Client client) {
        Client = client;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public float getToPay() {
        return ToPay;
    }

    public void setToPay(float toPay) {
        ToPay = toPay;
    }

    public float getPaid() {
        return Paid;
    }

    public void setPaid(float paid) {
        Paid = paid;
    }
}
