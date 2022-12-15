package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import org.junit.Assert;
import ru.job4j.chess.firuges.black.PawnBlack;

import java.security.PublicKey;
import java.util.Arrays;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenOccupiedCellException()
            throws OccupiedCellException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell startPosition = bishopBlack.position();
        PawnBlack pawnBlack = new PawnBlack(Cell.D7);
        logic.add(bishopBlack);
        logic.add(pawnBlack);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(startPosition, Cell.E6);
        });
        assertThat(exception.getMessage()).isEqualTo("That place locked  another figure");
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell startPosition = bishopBlack.position();
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(startPosition, Cell.A8);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from C8 to A8");
    }

    @Test
    public void whenPositionIsRightBishopBlack() {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell startPosition = bishopBlack.position();
        logic.add(bishopBlack);
        assertEquals(startPosition, Cell.C8);
    }

    @Test
    public void whenCopyPositionIsRightBishopBlack() {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        logic.add(bishopBlack);
        Cell startPosition = bishopBlack.position();
        assertEquals(startPosition, bishopBlack.copy(startPosition).position());
    }

    @Test
    public void whenWayIsCorrect() throws OccupiedCellException, FigureNotFoundException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        Cell dest = Cell.G5;
        Cell[] wat = bishopBlack.way(dest);
        Cell[] wayCopy = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        logic.move(bishopBlack.position(), dest);
        Assert.assertArrayEquals(wat, wayCopy);
    }
}