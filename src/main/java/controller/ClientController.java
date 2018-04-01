package controller;

import java.util.ArrayList;

import repository.DataManager;
import model.*;

public class ClientController {
	private DataManager _dataManager;

    public DataManager get_dataManager() {
        return _dataManager;
    }

    public ClientController(){
        _dataManager = new DataManager();
    }
    
    private String ValidateClient(String name, String address, String id){
        boolean hasAlpha = false;
        if(!name.equals("") && !address.equals("") && !id.equals("")){
            for(int i=0; i<name.length(); i++){
                if((!Character.isUpperCase(name.charAt(i))) && (!Character.isLowerCase(name.charAt(i))) && (!Character.isSpaceChar(name.charAt(i)))){
                    return "Invalid character: " + name.charAt(i);
                }
                if (Character.isAlphabetic(name.charAt(i))) {
                    hasAlpha = true;
                }
            }

            if(!hasAlpha) {
                return "Id, Name or address cannot be empty!";
            }
            return null;

        }else{
            return "Id, Name or address cannot be empty!";
        }
    }
    
    public String AddClient(String name, String address, String id){
        //validation
        String valid;
        if((valid = ValidateClient(name, address,id)) != null){
            return valid;
        }
        Client c = new Client(name, address,id);
        //uniqueness
        for(int j=0; j<_dataManager.Clients.size(); j++){
            if(_dataManager.Clients.get(j).equals(c)){
                return "Client already exists!";
            }
        }
        try{
            _dataManager.Clients.add(c);
            _dataManager.SaveChanges();
            return "Success";
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String AddClientIndex(Client c, int year, int month, float toPay){
        if(year > 0){
            if(month > 0){
                if(toPay >= 0){
                    //validate client attributes
                    String valid;
                    if((valid = ValidateClient(c.getName(), c.getAddress(), c.getIdClient())) == null){
                        //check if client exist
                        Boolean exist = false;
                        for(int i=0; i<_dataManager.Clients.size(); i++){
                            if(_dataManager.Clients.get(i).equals(c)){
                                exist = true;
                                break;
                            }
                        }
                        if(exist){
                            Issue i = new Issue(c, year, month, toPay, 0);
                            //uniqueness
                            for(int j=0; j<_dataManager.Issues.size(); j++){
                                if(_dataManager.Issues.get(j).equals(i)){
                                    return "Monthly index already exists!";
                                }
                            }

                            _dataManager.SaveChanges();
                            return null;
                        }else{
                            return "Client does not exist!";
                        }
                    }else{
                        return valid;
                    }
                }else{
                    return "Money to pay can't be less than 0!";
                }
            }else{
                return "Month can't be 0 or less!";
            }
        }else{
            return "Year can't be 0 or less!";
        }
    }
    
    public String ListIssue(Client c){
        String s = "";
        String pen = "";
        Double total = 0.0;
        Issue last = null, beforeLast;       
        for(int i=0; i<_dataManager.Issues.size(); i++){
        	if(_dataManager.Issues.get(i).getClient().equals(c)){
            	 pen += String.format("Year: %d, Month: %d, Penalty: %2.0f\n",
                         _dataManager.Issues.get(i).getYear(), _dataManager.Issues.get(i).getMonth(), _dataManager.Issues.get(i).getToPay());
            	 s += pen;
        	}            
        }
        if (s.equals("")) {
            s = "No invoices";
        }
        return s;
    }
    
}
