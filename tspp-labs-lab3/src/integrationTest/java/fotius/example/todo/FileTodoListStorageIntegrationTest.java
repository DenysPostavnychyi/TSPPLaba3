package fotius.example.todo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class FileTodoListStorageIntegrationTest {

    @BeforeEach
    void beforeEach() {
        // implement setup if necessary
    }

    @AfterEach
    void afterEach() {
        // implement cleanup if necessary
    }

    @Test
    void testSave() {
        // tspp-labs/build/tmp/my-list.txt
        Path tmpFile = getBuildTmpDir().resolve(Paths.get("my-list.txt"));
        FileTodoListStorage storage = new FileTodoListStorage(tmpFile);

        List<String> items = Arrays.asList("item1", "item2", "item3");
        TodoList list = new TodoList(items);

        storage.save(list);
    }

    @Test
    void testLoad() {
        // tspp-labs/build/tmp/my-list.txt
        Path tmpFile = getBuildTmpDir().resolve(Paths.get("my-list.txt"));
        FileTodoListStorage storage = new FileTodoListStorage(tmpFile);

        List<String> items = Arrays.asList("item1", "item2", "item3");

        TodoList list = storage.load();

        Assertions.assertEquals(list.items(), items);
    }

    public static Path getBuildTmpDir() {
        final Path tmp = Paths.get(System.getProperty("user.dir")).resolve("build").resolve("tmp");
        if (!Files.exists(tmp)) {
            try {
                return Files.createDirectory(tmp);
            } catch (IOException ioEx) {
                throw new RuntimeException(ioEx);
            }
        }
        return tmp;
    }
}
