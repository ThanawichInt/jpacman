package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {
    @Mock
    private BoardFactory boardFactory;
    @Mock
    private LevelFactory levelFactory;
    @Mock
    private Blinky blinky;

    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        MockitoAnnotations.initMocks(this);
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);
        MapParser mapParser = new MapParser(levelFactory, boardFactory);
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");
        mapParser.parseMap(map);

        Mockito.verify(levelFactory, Mockito.times(1)).createGhost();
        Mockito.verify(boardFactory, Mockito.times(10)).createGround();
    }

    /**
     * Test for the parseMap method (bad map).
     */
    @Test
    public void testParseMapWrong1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            MockitoAnnotations.initMocks(this);
            assertNotNull(boardFactory);
            assertNotNull(levelFactory);
            MapParser mapParser = new MapParser(levelFactory, boardFactory);
            ArrayList<String> map = new ArrayList<>();
        /*
        Create a map with inconsistent size between
        each row or contain invalid characters
        */
        //Missing #
            map.add("############");
            map.add("#P        G#");
            map.add("#########");
            mapParser.parseMap(map);
        });
        Assertions.assertEquals("Input text lines are not of equal width.", thrown.getMessage());
    }
}
