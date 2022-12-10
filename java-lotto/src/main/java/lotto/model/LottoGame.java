package lotto.model;

import java.util.List;

public class LottoGame {
    private final LottoMachine lottoMachine = new LottoMachine();

    private Lottos lottos;
    private WinningLotto winningLotto;

    public void buyLotto(int amount) {
        lottos = lottoMachine.buy(new Money(amount));
    }

    public void makeWinningLotto(List<Integer> winningNumbers) {
        winningLotto = new WinningLotto(new Lotto(winningNumbers));
    }

    public Lottos getLottos() {
        return lottos;
    }
}
