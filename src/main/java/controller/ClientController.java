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
        String result = "";
        if(year > 0){
            if(month > 0){
                if(toPay >= 0){
                    if(ValidateClient(c.getName(), c.getAddress(), c.getIdClient()) == null){
                        //check if client exist
                        Boolean exist = false;

                        if (_dataManager.Clients.isEmpty()) {
                            return "Client does not exist!";
                        }

                        for(int i=0; i<_dataManager.Clients.size(); i++){
                            if(_dataManager.Clients.get(i).equals(c)){
                                exist = true;
                                break;
                            }
                        }

                        if(exist){
                            Issue i = new Issue(c, year, month, toPay, 0);

                            if(!_dataManager.Issues.isEmpty()) {
                                for(int j=0; j < _dataManager.Issues.size(); j++){
                                    if(_dataManager.Issues.get(j).getMonth() == month && _dataManager.Issues.get(j).getYear() == year
                                            && _dataManager.Issues.get(j).getClient().equals(c)){
                                        return "Monthly index already exists!";
                                    }
                                }
                            }

                            _dataManager.getInvoicesList().add(i);
                            _dataManager.SaveChanges();
                            result = "Success";
                        }else{
                            result = "Client does not exist!";
                        }
                    }
                }else{
                    result = "Money to pay can't be less than 0!";
                }
            }else{
                result = "Month can't be 0 or less!";
            }
        }else{
            result = "Year can't be 0 or less!";
        }
        return result;
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
