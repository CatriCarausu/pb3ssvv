package controller;

import model.Client;
import model.Issue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Catri on 08-May-18.
 */
public class ClientControllerIncrementalIntegration {
    private ClientController controller;

    @Before
    public void setUp() throws Exception {
        controller = new ClientController();
    }

    @Test
    public void addClientSuccess() {
        String response = controller.AddClient("test name", "str nr", "1");
        controller.get_dataManager().SaveChanges();
        assertEquals("Success", response);
    }

    @Test
    public void addClientIndexSuccess() {
        controller.AddClient("test name", "str nr", "1");
        String result = controller.AddClientIndex(new Client("test name", "str nr", "1"), 2018, 2, (float) 20.2);
        assertEquals( "Success", result);
    }

    @Test
    public void listIssueSuccess() {
        controller.AddClient("test name", "str nr", "1");
        controller.AddClientIndex(new Client("test name", "str nr", "1"), 2018, 2, (float) 20.2);
        String result = controller.ListIssue(new Client("test name", "str nr", "1"));
        assertEquals( "Year: 2018, Month: 2, Penalty: 20.20\n", result);
    }

    @Test
    public void IncrementalA() {
        this.addClientSuccess();
        assertTrue(controller.get_dataManager().Clients.contains(new Client("test name", "str nr", "1")));
    }

    @Test
    public void IncrementalAB() {
        this.addClientSuccess();
        this.addClientIndexSuccess();

        assertTrue(controller.get_dataManager().Clients.contains(new Client("test name", "str nr", "1")));
        assertTrue(controller.get_dataManager().Issues.contains(new Issue(new Client("test name", "str nr", "1"), 2018, 2, 0, (float) 20.2)));
    }

    @Test
    public void IncrementalABC() {
        this.addClientSuccess();
        this.addClientIndexSuccess();
        this.listIssueSuccess();

        assertTrue(controller.get_dataManager().Clients.contains(new Client("test name", "str nr", "1")));
        assertTrue(controller.get_dataManager().Issues.contains(new Issue(new Client("test name", "str nr", "1"), 2018, 2, 0, (float) 20.2)));
    }
}
