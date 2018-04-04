package controller;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Catri on 23-Mar-18.
 */
public class ClientControllerTest {
    private ClientController controller;
    private String emptyMessage = "Id, Name or address cannot be empty!";
    private String invalidCharacterMessage = "Invalid character: ";

    @Before
    public void setUp() throws Exception {
        controller = new ClientController();
    }

    @Test
    public void addClient() throws Exception {
        int oldSize = controller.get_dataManager().Clients.size();
        String response = controller.AddClient("ana maria", "str T Mihali", "1");
        String success = "Success";
        assertEquals(response, success);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize + 1);

        oldSize = controller.get_dataManager().Clients.size();
        response = controller.AddClient("a    f", "str T Mihali", "2");
        assertEquals(response, success);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize + 1);

        oldSize = controller.get_dataManager().Clients.size();
        response = controller.AddClient("ana", "str T Mihali", "3");
        assertEquals(response, success);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize + 1);

        oldSize = controller.get_dataManager().Clients.size();
        response = controller.AddClient("a", "str T Mihali", "4");
        assertEquals(response, success);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize + 1);

    }

    @Test
    public void addClientFailsWhenNameEmpty(){
        int oldSize = controller.get_dataManager().Clients.size();
        String response = controller.AddClient("", "test", "test");
        assertEquals(response, emptyMessage);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);
    }

    @Test
    public void addClientFailsWhenAddressEmpty(){
        int oldSize = controller.get_dataManager().Clients.size();
        String response = controller.AddClient("test", "", "test");
        assertEquals(response, emptyMessage);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);
    }

    @Test
    public void addClientFailsWhenIdEmpty(){
        int oldSize = controller.get_dataManager().Clients.size();
        String response = controller.AddClient("test", "test", "");
        assertEquals(response, emptyMessage);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);
    }

    @Test
    public void addClientFailsWhenClientAlreadyExists(){
        int oldSize = controller.get_dataManager().Clients.size();
        controller.AddClient("test", "test", "test");
        String response = controller.AddClient("test", "test", "test");
        String alreadyExistsMessage = "Client already exists!";
        assertEquals(response, alreadyExistsMessage);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize + 1);
    }

    @Test
    public void addClientFailsWhenNameContainsIllegalCharacters(){
        int oldSize = controller.get_dataManager().Clients.size();
        String response = controller.AddClient("ana3", "test", "test");
        assertEquals(response, invalidCharacterMessage + '3');
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);

        response = controller.AddClient("      ", "test", "test");
        assertEquals(response, emptyMessage);
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);

        response = controller.AddClient("ana3", "test", "test");
        assertEquals(response, invalidCharacterMessage + '3');
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);

        response = controller.AddClient("1", "test", "test");
        assertEquals(response, invalidCharacterMessage + '1');
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);

        response = controller.AddClient("1 3", "test", "test");
        assertEquals(response, invalidCharacterMessage + '1');
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);

        response = controller.AddClient("123", "test", "test");
        assertEquals(response, invalidCharacterMessage + '1');
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);

        response = controller.AddClient("///", "test", "test");
        assertEquals(response, invalidCharacterMessage + '/');
        assertEquals(controller.get_dataManager().Clients.size(), oldSize);
    }

    @Test
    public void addClientFailsWhenDataSizeOutOfHeapSize() {
        try{
            controller.AddClient(this.generateString(Integer.MAX_VALUE), "str T Mihali", "5");
            assertTrue(false);
        } catch (OutOfMemoryError e){
            assertTrue(true);
        }

        try{
            controller.AddClient(this.generateString(Integer.MAX_VALUE - 1), "str T Mihali", "5");
            assertTrue(false);
        } catch (OutOfMemoryError e){
            assertTrue(true);
        }

        try{
            controller.AddClient("test", this.generateString(Integer.MAX_VALUE),  "5");
            assertTrue(false);
        } catch (OutOfMemoryError e){
            assertTrue(true);
        }

        try{
            controller.AddClient("test", this.generateString(Integer.MAX_VALUE - 1),  "5");
            assertTrue(false);
        } catch (OutOfMemoryError e){
            assertTrue(true);
        }

        try{
            controller.AddClient("name", "str T Mihali", this.generateString(Integer.MAX_VALUE));
            assertTrue(false);
        } catch (OutOfMemoryError e){
            assertTrue(true);
        }

        try{
            controller.AddClient("name", "str T Mihali", this.generateString(Integer.MAX_VALUE - 1));
            assertTrue(false);
        } catch (OutOfMemoryError e){
            assertTrue(true);
        }
    }

    private String generateString(int length) {
        Random random = new Random();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String space = " ";
        String alpha = upper + lower + space;
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < length; i++) {
            s.append(alpha.charAt(random.nextInt(alpha.length())));
        }

        return s.toString();
    }

}