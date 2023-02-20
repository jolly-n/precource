package domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class LadderService {
    private Players players;
    private Height height;

    public void makeLine() {
        new Line(players.getSize());
    }

    public void makeHeight(int height) {
        this.height = new Height(height);
    }

    public void makePlayers(List<String> names) {
        players = new Players(makePlayer(names));
    }

    private List<Player> makePlayer(List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(toList());
    }
}
