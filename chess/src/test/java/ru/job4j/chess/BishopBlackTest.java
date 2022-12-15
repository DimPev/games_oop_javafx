package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopBlackTest {
    @Test
    public void whenPositionIsRightBishopBlack() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell startPosition = bishopBlack.position();
        assertEquals(startPosition, Cell.C8);
    }

    @Test
    public void whenCopyPositionIsRightBishopBlack() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell startPosition = bishopBlack.position();
        assertEquals(startPosition, bishopBlack.copy(startPosition).position());
    }

    @Test
    public void whenWayIsCorrect() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] way = bishopBlack.way(Cell.G5);
        Cell[] steps = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Assert.assertArrayEquals(way, steps);
    }
}
