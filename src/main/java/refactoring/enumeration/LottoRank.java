package refactoring.enumeration;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum LottoRank {

    FIRST(6, 2_000_000_000, false, "6개 일치 (2000000000원) - %s개"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치 (30000000원) - %s개"),
    THIRD(5, 1_500_000, false, "5개 일치 (1500000원) - %s개"),
    FOURTH(4, 50_000, false, "4개 일치 (50000원) - %s개"),
    FIFTH(3, 5_000, false, "3개 일치 (5000원) - %s개"),
    ZERO(0, 0, false, "");

    private final int match;
    private final int prize;
    private final boolean isBonus;
    private final String message;

    LottoRank(int match, int prize, boolean isBonus, String message) {
        this.match = match;
        this.prize = prize;
        this.isBonus = isBonus;
        this.message = message;
    }

    public static final List<LottoRank> ALL_LOTTO_RANK = Arrays.stream(LottoRank.values()).collect(Collectors.toList());

    public static int getPriceByRank(LottoRank rank) {
        return rank.prize;
    }

    public String getMessage() {
        return this.message;
    }

    public static LottoRank getRank(int match, boolean isBonus) {
        if (match == THIRD.match && isBonus) {
            return SECOND;
        }

        return ALL_LOTTO_RANK.stream()
                .filter(lottoRank -> lottoRank.match == match)
                .findAny()
                .orElse(ZERO);
    }

    public static Map<LottoRank, Integer> initialize() {
        Map<LottoRank, Integer> winnerBoard = new EnumMap<>(LottoRank.class);
        ALL_LOTTO_RANK.forEach(rank -> winnerBoard.put(rank, 0));
        return winnerBoard;
    }
}
