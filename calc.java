package assigment;

public class calc implements ICalculator {

	@Override
	public int add(int x, int y) {
		return x+y;
	}

	@Override
	public float divide(int x, int y) throws RuntimeException {
		if(y==0) throw new ArithmeticException("zero division");
		return (float)x/y;
	}
	

}
