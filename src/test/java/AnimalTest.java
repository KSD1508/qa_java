import com.example.Animal;
import com.example.Lion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AnimalTest {
    Animal animal = new Animal();

    @ParameterizedTest
    @MethodSource("generateData")
    public void getFoodTest(String animalKind, List<String> foods) throws Exception {
        assertEquals(foods, animal.getFood(animalKind));
    }

    @Test
    void shouldFailedGetAnimalFood() {
        Throwable error = assertThrows(Exception.class, () -> {
            animal.getFood("unknown");
        });

        assertEquals(   "Неизвестный вид животного, используйте значение Травоядное или Хищник", error.getMessage());
    }

    @Test
    public void testGetFamily() {
        assertEquals("Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи", animal.getFamily());
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("Травоядное", Arrays.asList("Трава", "Различные растения")),
                Arguments.of("Хищник", Arrays.asList("Животные", "Птицы", "Рыба"))
        );
    }
}
