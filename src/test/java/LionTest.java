import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LionTest {
    @Mock
    Feline feline;

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void shouldSuccessCreateLion(String sex, boolean hasMane) throws Exception {
        Lion lion = new Lion(sex, feline);

        assertEquals(hasMane, lion.doesHaveMane());

    }

    @Test
    void shouldFailedCreateLionByUnknownSex() {
        String unknownSex = "unknownSex";
        Throwable error = assertThrows(Exception.class, () -> {
            new Lion(unknownSex, feline);
        });

        assertEquals("Используйте допустимые значения пола животного - самей или самка", error.getMessage());
    }

    @Test
    void testGetKittens() throws Exception {
        when(feline.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самец", feline);

        assertEquals(1, lion.getKittens());
    }

    @Test
    void testGetFoods() throws Exception {
        when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        Lion lion = new Lion("Самец", feline);

        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
    }
}
