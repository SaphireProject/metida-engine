package metida.data;

import java.util.List;

public class ParameterMetida {
    private int countOfPlayers;
    private int heightOfMapForGame;
    private int widthOfMapForGame;
    private List<StrategyJson> strategies;

    public ParameterMetida(int countOfPlayers , int heightOfMapForGame , int widthOfMapForGame , List<StrategyJson> strategies) {
        this.countOfPlayers = countOfPlayers;
        this.heightOfMapForGame = heightOfMapForGame;
        this.widthOfMapForGame = widthOfMapForGame;
        this.strategies = strategies;
    }

    public ParameterMetida() {
    }

    public int getCountOfPlayers() {
        return countOfPlayers;
    }

    public void setCountOfPlayers(int countOfPlayers) {
        this.countOfPlayers = countOfPlayers;
    }

    public int getHeightOfMapForGame() {
        return heightOfMapForGame;
    }

    public void setHeightOfMapForGame(int heightOfMapForGame) {
        this.heightOfMapForGame = heightOfMapForGame;
    }

    public int getWidthOfMapForGame() {
        return widthOfMapForGame;
    }

    public void setWidthOfMapForGame(int widthOfMapForGame) {
        this.widthOfMapForGame = widthOfMapForGame;
    }

    public List<StrategyJson> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<StrategyJson> strategies) {
        this.strategies = strategies;
    }

    @Override
    public String toString() {
        return "ParameterMetida{" +
                "countOfPlayers=" + countOfPlayers +
                ", heightOfMapForGame=" + heightOfMapForGame +
                ", widthOfMapForGame=" + widthOfMapForGame +
                ", strategies=" + strategies +
                '}';
    }
}
