import org.example.dto.ItemDTO;
import org.example.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemServiceTest {

    private final ItemService itemService = new ItemService();

    @BeforeEach
    public void clearFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("items.txt"));
        writer.close();

        List<ItemDTO> products = new ArrayList<>();
        products.add(new ItemDTO(1, "code1", "name1", 10.0, 1));
        products.add(new ItemDTO(2, "code2", "name2", 20.0, 2));

        itemService.saveToFile(products);
    }

    @Test
    public void saveToFileTest() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("items.txt")));

        System.out.println(content);

        assertTrue(content.contains("1,code1,name1,10.0,1"));
        assertTrue(content.contains("2,code2,name2,20.0,2"));
    }

    @Test
    public void readFromFileTest() throws IOException {
        List<ItemDTO> products = itemService.readFromFile();

        assertEquals(2, products.size());

        ItemDTO item1 = products.get(0);
        assertEquals(1, item1.getId());
        assertEquals("code1", item1.getCode());
        assertEquals("name1", item1.getName());
        assertEquals(10.0, item1.getPrice());
        assertEquals(1, item1.getQuantity());

        ItemDTO item2 = products.get(1);
        assertEquals(2, item2.getId());
        assertEquals("code2", item2.getCode());
        assertEquals("name2", item2.getName());
        assertEquals(20.0, item2.getPrice());
        assertEquals(2, item2.getQuantity());
    }
}