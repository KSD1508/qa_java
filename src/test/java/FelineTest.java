import com.example.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FelineTest {
    Feline feline = new Feline();
    @Test
   public void testEatMeat() throws Exception {
        assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
    }
    @Test
    public void testGetFamily(){
       assertEquals("Кошачьи", feline.getFamily());
    }
    @Test
    public void testGetKittens(){
        assertEquals(1, feline.getKittens());
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    public void testGetKittens(int arguments){
        assertEquals(arguments, feline.getKittens(arguments));
    }

    static IntStream argsProviderFactory() {
        return IntStream.range(0, 10);
    }
}
