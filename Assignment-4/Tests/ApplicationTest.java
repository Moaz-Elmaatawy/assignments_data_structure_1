package eg.edu.alexu.csd.datastructure.linkedList.cs;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void set_and_print () {
        Application app = new Application();

        int[][] terms = new int[][] {
                {1,5},
                {0,0},
                {8,-1},
                {4,-5},
                {3,2}
        };
        app.setPolynomial('A', terms);
        String a= app.print('A');
        assertEquals(a,"A is : x^(5)+ 3x^(2)+ 0+ 8x^(-1)+ 4x^(-5)");

        terms = new int[][] {
                {9,8},
                {5,0},
                {3,1},
                {0,8},
                {9,1}
        };
        app.setPolynomial('B', terms);
        String b=app.print('B');
        assertEquals(b,"B is : 0+ 9x^(8)+ 9x^(1)+ 3x^(1)+ 5x^(0)");

    }

    @Test
    public void empty() {
        Application app = new Application();

        int[][] terms = new int[][] {
                {1,5},
                {0,0},
                {8,-1},
                {4,-5},
                {3,2}
        };


        app.setPolynomial('A',terms);
        assertEquals(app.is_empty('A'),false);
        assertEquals(app.is_empty('B'),true);
    }

    @Test
    public void add() {
        Application app = new Application();

        int[][] terms = new int[][] {
                {1,5},
                {0,0},
                {8,-1},
                {4,-5},
                {3,2}
        };
        int[][] terms2 = new int[][] {
                {9,8},
                {5,0},
                {3,1},
                {0,8},
                {9,1}
        };
        app.setPolynomial('A',terms);
        app.setPolynomial('B',terms2);
        int[][] terms3 = new int[][] {
                {4,-5},
                {8,-1},
                {5,0},
                {12,1},
                {3,2},
                {1,5},
                {9,8}
        };

        assertArrayEquals(app.add('A','B'),terms3);
    }

    @Test
    public void sub() {
        Application app = new Application();

        int[][] terms = new int[][] {
                {1,5},
                {0,0},
                {8,-1},
                {4,-5},
                {3,2}
        };
        int[][] terms2 = new int[][] {
                {9,8},
                {5,0},
                {3,1},
                {0,8},
                {9,1}
        };
        app.setPolynomial('A',terms);
        app.setPolynomial('B',terms2);
        int[][] terms3 = new int[][] {
                {-1,-1},
                {-5,0},
                {-3,1},
                {3,2},
                {5,5},
                {-9,8}
        };

        assertArrayEquals(app.subtract('A','B'),terms3);
    }

    @Test
    public void multiply() {
        Application app = new Application();

        int[][] terms = new int[][] {
                {1,5},
                {0,0},
                {8,-1},
                {4,-5},
                {3,2}
        };
        int[][] terms2 = new int[][] {
                {9,8},
                {5,0},
                {3,1},
                {0,8},
                {9,1}
        };
        app.setPolynomial('A',terms);
        app.setPolynomial('B',terms2);
        int[][] terms3 = new int[][] {
                {20,-5},
                {48,-4},
                {40,-1},
                {96,0},
                {15,2},
                {72,3},
                {5,5},
                {12,6},
                {72,7},
                {27,10},
                {9,13}
        };
        assertArrayEquals(app.multiply('A','B'),terms3);
    }

    @Test
    public void clear() {
        Application app = new Application();

        int[][] terms = new int[][] {
                {1,5},
                {0,0},
                {8,-1},
                {4,-5},
                {3,2}
        };
        int[][] terms2 = new int[][] {
                {9,8},
                {5,0},
                {3,1},
                {0,8},
                {9,1}
        };
        app.setPolynomial('A',terms);
        app.setPolynomial('B',terms2);

        app.clearPolynomial('A');
        assertEquals(app.is_empty('A'),true);

    }

    @Test
    public void evaluate() {Application app = new Application();

        int[][] terms = new int[][] {
                {1,5},
                {0,0},
                {8,-1},
                {4,-5},
                {3,2}
        };
        app.setPolynomial('A',terms);
        assertEquals(app.evaluatePolynomial('A',1),16.0,0.001);
        assertEquals(app.evaluatePolynomial('A',2),48.125,0.001);
    }
}