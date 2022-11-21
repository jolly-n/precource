package bridge.controller;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.domain.Bridge;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class BridgeGameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BridgeMaker bridgeMaker;

    private BridgeGame bridgeGame;
    private boolean isPlaying;

    public BridgeGameController(BridgeNumberGenerator generator) {
        bridgeMaker = new BridgeMaker(generator);
    }

    public void play() {
        start();
        while (isPlaying) {
            moveBridge();
        }
        end();
    }

    private void start() {
        outputView.printGameStartMsg();
        bridgeGame = new BridgeGame(makeBridge());
        bridgeGame.start();
        isPlaying = true;
    }

    private void end() {
        outputView.printResult(bridgeGame.getBridgeGameResult());
    }

    private Bridge makeBridge() {
        outputView.printBridgeSizeMsg();
        while (true) {
            try {
                int size = inputView.readBridgeSize();
                return bridgeMaker.makeBridge(makeShape(size));
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private List<String> makeShape(int size) {
        return bridgeMaker.makeBridge(size);
    }

    private void moveBridge() {
        boolean canMove = true;
        int moveCount = 0;
        while (canMove && bridgeGame.hasBridgeToMove(moveCount)) {
            outputView.printMovingMsg();
            canMove = bridgeGame.move(moveCount, inputView.readMoving());
            outputView.printMap(bridgeGame.getBridgeGameResult());
            moveCount++;
        }
        finish(canMove);
    }

    private void finish(boolean canMove) {
        if (canMove) {
            succeed();
            return;
        }
        fail();
    }

    private void succeed() {
        bridgeGame.succeed();
        isPlaying = false;
    }

    private void fail() {
        outputView.printGameCommandMsg();
        if (bridgeGame.doesRetry(inputView.readGameCommand())) {
            bridgeGame.retry();
            return;
        }
        isPlaying = false;
    }
}
