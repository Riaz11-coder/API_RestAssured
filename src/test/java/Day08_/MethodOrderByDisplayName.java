package Day08_;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class MethodOrderByDisplayName {
    @DisplayName("A. Test this first")
    @Test
    public void testA(){

    }
    @DisplayName("Z. Test this Last")
    @Test
    public void testZ(){

    }
    @DisplayName("B . Test this Second")
    @Test
    public void testB(){

    }
}
