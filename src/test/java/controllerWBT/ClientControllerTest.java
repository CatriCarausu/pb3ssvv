package controllerWBT;

import controller.ClientController;
import model.Client;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Catri on 23-Mar-18.
 */
public class ClientControllerTest {
    private ClientController controller;

    @Before
    public void setUp() throws Exception {
        controller = new ClientController();
    }

    @Test
    public void addClientIndexSuccess() {
        controller.AddClient("ana", "str", "1234");
        controller.get_dataManager().SaveChanges();
        String result = controller.AddClientIndex(new Client("ana", "str", "1234"), 2018, 2, (float)2.2);
        assertEquals(result, "Success");
    }

    @Test
    public void addClientIndexInvalidYear() {
        controller.AddClient("ana", "str", "1234");
        controller.get_dataManager().SaveChanges();
        String result = controller.AddClientIndex(new Client("ana", "str", "1234"), 0, 2, (float)2.2);
        assertEquals(result, "Year can't be 0 or less!");
    }

    @Test
    public void addClientIndexInvalidMonth() {
        controller.AddClient("ana", "str", "1234");
        controller.get_dataManager().SaveChanges();
        String result = controller.AddClientIndex(new Client("ana", "str", "1234"), 2000, 0, (float)2.2);
        assertEquals(result, "Month can't be 0 or less!");
    }

    @Test
    public void addClientIndexInvalidPayAmount() {
        controller.AddClient("ana", "str", "1234");
        controller.get_dataManager().SaveChanges();
        String result = controller.AddClientIndex(new Client("ana", "str", "1234"), 2000, 10, (float)-10);
        assertEquals(result, "Money to pay can't be less than 0!");
    }

    @Test
    public void addClientIndexInexistentClient() {
        String result = controller.AddClientIndex(new Client("anamariaaa", "str", "1234"), 2000, 10, (float)10);
        assertEquals(result, "Client does not exist!");
    }

    @Test
    public void addClientIndexExistingMonth(){
        controller.AddClient("ana", "str", "1234");
        String result = controller.AddClientIndex(new Client("ana", "str", "1234"), 2000, 10, (float)10);
        assertEquals(result, "Success");
        result = controller.AddClientIndex(new Client("ana", "str", "1234"), 2000, 10, (float)10);
        assertEquals(result, "Monthly index already exists!");
    }

    @Test
    public void addClientIndexInvalidClientData(){
        String result = controller.AddClientIndex(new Client("ana3", "str", "1234"), 2000, 10, (float)10);
        assertEquals("Invalid client data!", result);
    }

    @Test
    public void addClientIndexWhenClientRepoEmpty(){
        String result = controller.AddClientIndex(new Client("ana", "str", "1234"), 2000, 10, (float)10);
        assertEquals("Client does not exist!", result);
    }

    @Test
    public void addClientIndexWhenIndexRepoEmpty(){
        controller.AddClient("ana", "str", "1234");
        controller.get_dataManager().SaveChanges();
        String result = controller.AddClientIndex(new Client("ana", "str", "1234"), 2018, 2, (float)2.2);
        assertEquals("Success", result);
    }
}