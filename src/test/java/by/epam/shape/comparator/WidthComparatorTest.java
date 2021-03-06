package by.epam.shape.comparator;

import by.epam.shape.entity.Point;
import by.epam.shape.entity.Rectangle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class WidthComparatorTest {

    Rectangle first;
    Rectangle second;
    Rectangle third;
    WidthComparator comparator;

    @BeforeMethod
    public void setUp() {
        first = new Rectangle(new Point(-2.0, -1.0), new Point(0.0, -1.0), new Point(0.0, -2.0), new Point(-2.0, -2.0));
        second = new Rectangle(new Point(2.0, 2.0), new Point(4.0, 2.0), new Point(4.0, 1.0), new Point(2.0, 1.0));
        third = new Rectangle(new Point(2.0, 6.0), new Point(8.0, 6.0), new Point(8.0, 2.0), new Point(2.0, 2.0));
        comparator = new WidthComparator();
    }

    @Test
    public void testCompareFistGreaterSecond() {
        int result = comparator.compare(third, first);
        boolean actual = result > 0;
        assertTrue(actual);
    }

    @Test
    public void testCompareEquals() {
        int result = comparator.compare(first, second);
        assertEquals(result, 0);
    }

    @Test
    public void testCompareSecondGreaterFirst() {
        int result = comparator.compare(second, third);
        boolean actual = result < 0;
        assertTrue(actual);
    }
}