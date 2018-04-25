package controller;

import model.Client;
import model.Issue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Catri on 25-Apr-18.
 */
public class ClientControllerTest2 {
    private ClientController controller;

    @Before
    public void setUp() throws Exception {
        controller = new ClientController();
    }

    @Test
    public void addClientSuccess() {
        int oldSize = controller.get_dataManager().Clients.size();
        String response = controller.AddClient("ana maria", "str T Mihali", "1");
        controller.get_dataManager().SaveChanges();
        assertEquals("Success", response);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize + 1);
    }

    @Test
    public void addClientIndexSuccess() {
        controller.AddClient("ana maria", "str T Mihali", "1");
        String result = controller.AddClientIndex(new Client("ana maria", "str T Mihali", "1"), 2018, 2, (float) 20.2);
        assertEquals( "Success", result);
    }

    @Test
    public void listIssueSuccess() {
        controller.AddClient("ana maria", "str T Mihali", "1");
        controller.AddClientIndex(new Client("ana maria", "str T Mihali", "1"), 2018, 2, (float) 20.2);
        String result = controller.ListIssue(new Client("ana maria", "str T Mihali", "1"));
        assertEquals( "Year: 2018, Month: 2, Penalty: 20.20\n", result);
    }

    @Test
    public void completeFlowSuccess() {
        this.addClientSuccess();
        this.addClientIndexSuccess();
        this.listIssueSuccess();

        assertTrue(controller.get_dataManager().Clients.contains(new Client("ana maria", "str T Mihali", "1")));
        assertTrue(controller.get_dataManager().Issues.contains(new Issue(new Client("ana maria", "str T Mihali", "1"), 2018, 2, 0, (float) 20.2)));
    }
}