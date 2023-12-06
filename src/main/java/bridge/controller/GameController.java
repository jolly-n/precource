package bridge.controller;

import bridge.domain.Bridge;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeRandomNumberGenerator;
import bridge.domain.RetryStatus;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.function.Supplier;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;

    public GameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    public void run() {
        BridgeGame bridgeGame = makeBridgeGame();
        while (bridgeGame.isInProgress()) {
            play(bridgeGame);
            if (bridgeGame.isFail()) {
                handleFailedGame(bridgeGame);
            }
        }
        outputView.printResult(bridgeGame.getBridgeGameMap(), bridgeGame.getGameResult(), bridgeGame.getTryCount());
    }

    private BridgeGame makeBridgeGame() {
        outputView.printStartMessage();
        Bridge bridge = retry(this::makeBridge);
        return new BridgeGame(bridge);
    }

    private Bridge makeBridge() {
        int size = inputView.readBridgeSize();
        return new Bridge(bridgeMaker.makeBridge(size));
    }

    private void play(BridgeGame bridgeGame) {
        bridgeGame.move(retry(inputView::readMoving));
        outputView.printMap(bridgeGame.getBridgeGameMap());
    }

    private void handleFailedGame(BridgeGame bridgeGame) {
        RetryStatus retryStatus = retry(() -> RetryStatus.find(inputView.readGameCommand()));
        if (retryStatus == RetryStatus.RETRY) {
            bridgeGame.retry();
        }
        if (retryStatus == RetryStatus.QUIT) {
            bridgeGame.finishGame();
        }
    }

    public <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
