package dk.easv.strategy;

public class Context {
    private Strategy strategy;

    public int executeStrategy(int num) {
        return strategy.divide(num);
    }

    public void setStrategy(String strategyType) {
        if (strategyType.equals("four")) {
            strategy = new DivideFour();
        }
        else if (strategyType.equals("two")) {
            strategy = new DividerTwo();
        }
    }
}
