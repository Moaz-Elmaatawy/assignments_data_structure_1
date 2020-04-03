package eg.edu.alexu.csd.datastructure.stack;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void infixToPostfix() {
        Application app = new Application();
        String[] postfix = {
                "m k / f r / q / o * i / x / c / h u n - v g * b / + w - e - l z * - / - s a * - / t j * +",
                "m k * l d / v w * z i / h + / s / x * / /",
                "c q * t / n v / e / f * m * h / s / a - / p * x g * y b z * o j d * l / / * / r / + w * +",
                "d x / m / b * q * n * u / r / w y / o k + z c / p * + f / - e a / - /",
                "g h * k f * v / z t * l * y x * s / o * i m q / a w * u c * d * * + / * p / j * - * / r -",
                "m n * w * o u a / q * d + f + / p h * r * - c i * + * k / b + s +",
                "j x h / z a * p * f * t * o u / q * * w i * r * c * b / / * e / / d -",
                "y j * u / k w n g * d * v o / q / * r h s / f / z * - * t m * + * / e / /",
                "w n / v q a / f / j / x t * b * y l / p / k * d - u + e / * m / g * + / o r * i / c / / s - * h / z -",
                "j z c * p * i * o x / h g * t / a e / b / n y - * - - * l * k * s + v + d * w * /",
                "y u v * h b * e * i / j * / f * * k * g *",
                "u o * r y / e * p s * h / b * j - * d * l f / + * i -",
                "k r / v g o p * d * q / l z n * + h y / w * - j - x + i a * + c * s + * m / / b / * u / e - f - /",
                "u g * i * d * b / h * z e l * c - * w + q * a v / - / f *",
                "a n / e / d y b / p * k s / w / m v j / i / / t * / * * q g / - * c / r *",
                "a l f b * r * h * n * q / s o / v c * t * - - * x * d + i / j / /",
                "i m y q * o * g / w / r / k - / u n * + b x f / l a e / - j / * * + / t * d * c h * -",
                "k t x / e j / a * d / r * m * i - n * f * q h v c * l o + * - * g * - / u s * p * + /",
                "f t v j / c y * g l m z i * q d * x * * * o / - / n - p - b - / e / * * k + u w * + h a * - /",
                "i n * p * v / j / x * u b * q r h t / a / l c e f / + + k - / z / / - * y o / + d s / - / g - m -",
                "d z f / r / p l * e m i w / h * k * x c / g + / * - o + n - / * j / * b /",
                "b p / t v a / y * e j * m / s z / r - h n - + - / u * i / * d + *",
                "a d / v * l * e y / x / q z / s - * p / j / /",
                "b a / v w g * i / q * j / l c - * d y m / f * / o + p + s * - / / x / r -"
        };

        String[] infix = {
                "(m/k/((f/r/q*o/i/x/c)-h/(((u-n+v*g/b)-w)-e-(l*z))-s*a)+t*j)",
                "m*k/((l/d/((v*w/(z/i+(h))/s*x))))",
                "(c*q/t/(n/v/e*f*m/h/s-(a))*((p))+((x*g)+y/(b*z*(o/(j*d/l)))/r)*w)",
                "d/x/m*b*q*n/u/r/(w/y-(o+k+z/(c)*p)/f-e/a)",
                "((g*h/((k*f/v*(z*t*l-(y*x/s)*o*(i/(m/(q)+a*w*(u*c*d)))/p*j)))))-r",
                "((((m*n*w*(o/((u/a*q)+d+f)-(p)*h*r+c*i))))/(k)+(b))+s",
                "j/((x/h*((z*a*p*f*t*(((o))/u*q)/(w*i*(r)*c/b)))/e))-d",
                "(y*j/u/(k/(w*(n*g*d*(v/o/q)*(r-h/s/f*z)+t*m))/e))",
                "(w/n*(((v/((q/a/f/j)+x*t*b*(((y)/l/p*k-d+u)/e)/m*g)/(o*r/i/c))-s))/h)-z",
                "(j/(((((z*c*p*i*(o/x-(h*g/t-a/e/b*(n-y))))*l*k+s+v)))*d*w))",
                "((y*(u*v/(h*b*e/i*j)*f)*(k))*g)",
                "((u*o*(r/y*e*(p*s/(h)*b-(j))*d+l/f)))-(i)",
                "k/r/(v*((g/(o*p*d/q*(((l+(z*n)-(h/y*w)-j+x)+(i)*a)*c+s)/m))/b)/u-e-f)",
                "u*g*i*d/b*h/((z*(e*l-c)+w)*q-(a)/v)*f",
                "a/n/e*(d*(y/b*p*(k/s/w/((m/(v/j/i)*t))))-q/g)/c*r",
                "(a/(((l*((((f*b*r*h))*n/q)-(s/o-(v)*c*t))*x)+d)/i/j))",
                "(((i/((m/(y*q*o/g/w/r-k)+u*n)+b*((x/f*((l-(a/e))/j))))))*t*d-c*h)",
                "k/(t/x/((e/j*a/d*r*m-i)*n*f-q*(h-v*c*(l+o))*g)+u*s*p)",
                "f/((t*(v/j*(c*y/(g/(l-(m*(z*i*(q*d*x)))/o)-n-p-b)/e))+k)+u*w-h*a)",
                "((i*n*p/v/j*x/(u*b*(q-r/(h/t/a/(l+(c+(e/f))-k)/z))+y/o-d/s)-g))-m",
                "d*((((z/f/r*(p*l/(e-m*((i/w*h)*k/(x/c+g))+(o)-n)))/j)))/b",
                "(b/p*((((t*(v/a*y/((e*j/m)-(s/z-r+(h-(n))))*u/i)+d)))))",
                "a/d*v*l/(e/y/x*(q/z-s)/p/j)",
                "b/a/(v/(w*g/i*q/j*(((l-c)))-(d/(y/(m)*f)+o+p)*s))/(x)-r"
        };

        for (int i = 0; i < infix.length; ++i) {
            assertEquals(app.infixToPostfix(infix[i]), postfix[i]);
        }


        String test ="(1+2+3)(3+4+5)";
        Assertions.assertThrows(RuntimeException .class,() ->{app.infixToPostfix(test);});

        String test2 ="(1+2+3)*(3+4+5)";
        assertEquals(app.infixToPostfix(test2),"1 2 + 3 + 3 4 + 5 + *");

        String test3 ="1+*5";
        Assertions.assertThrows(RuntimeException .class,() ->{app.infixToPostfix(test3);});

        String test4 ="2*-3+6/-2";
        assertEquals(app.infixToPostfix(test4),"2 0 3 - * 6 0 2 - / +");

        String test5 ="5*-2--2";
        assertEquals(app.infixToPostfix(test5),"5 0 2 - * 0 2 - -");

        String test6 ="3--3+-8/-5-/5";
        Assertions.assertThrows(RuntimeException .class,() ->{app.infixToPostfix(test6);});

        String test7 ="3--3+-8/-5/5";
        assertEquals(app.infixToPostfix(test7),"3 0 3 - - 0 8 - 0 5 - / 5 / +");

        String test8 ="3/3/3/3/3/3";
        assertEquals(app.infixToPostfix(test8),"3 3 / 3 / 3 / 3 / 3 /");

        String test9 ="3/-3/-3/-3/-3/-3";
        assertEquals(app.infixToPostfix(test9),"3 0 3 - / 0 3 - / 0 3 - / 0 3 - / 0 3 - /");

        String test10 ="-3";
        assertEquals(app.infixToPostfix(test10),"0 3 -");

        String test11 ="-3051*-100+50";
        assertEquals(app.infixToPostfix(test11),"0 3051 - 0 100 - * 50 +");

        String test12 ="1+2&";
        Assertions.assertThrows(RuntimeException .class,() ->{app.infixToPostfix(test12);});

        String test13 ="1++2";
        Assertions.assertThrows(RuntimeException .class,() ->{app.infixToPostfix(test13);});

        String test14 ="++1+2";
        Assertions.assertThrows(RuntimeException .class,() ->{app.infixToPostfix(test14);});

    }

    @Test
    public void evaluate() {
        Application app=new Application();

        String test="a b c / +";
        Assertions.assertThrows(RuntimeException .class,() ->{app.evaluate(test);});

        String test1="1 2 / +";
        Assertions.assertThrows(RuntimeException .class,() ->{app.evaluate(test1);});

        String test2="1 2 3  +";
        Assertions.assertThrows(RuntimeException .class,() ->{app.evaluate(test2);});

        String test3="5 4 * 0 3 - * 5 1 / +";
        assertEquals(app.evaluate(test3),-55);

        assertEquals(app.evaluate("555 2451 * 50 - 20 /"),68012);

        assertEquals(app.evaluate("0 11 - 0 100 - * 100 + 5 -"),1195);

        assertEquals(app.evaluate("0 5 5 7 / 2 * + *"),0);

    }
}