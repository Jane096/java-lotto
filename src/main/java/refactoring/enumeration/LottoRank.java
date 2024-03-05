package refactoring.enumeration;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public enum LottoRank {

    FIRST(6, 2_000_000_000, (match, isBonus) -> match == 6, "6개 일치 (2000000000원) - %s개"),
    SECOND(5, 30_000_000, (match, isBonus) -> match == 5 && isBonus, "5개 일치, 보너스 볼 일치 (30000000원) - %s개"),
    THIRD(5, 1_500_000, (match, isBonus) -> match == 5 && !isBonus, "5개 일치 (1500000원) - %s개"),
    FOURTH(4, 50_000, (match, isBonus) -> match == 4, "4개 일치 (50000원) - %s개"),
    FIFTH(3, 5_000, (match, isBonus) -> match == 3, "3개 일치 (5000원) - %s개"),
    ZERO(0, 0, (match, isBonus) -> match < 3  , "");

    private final int match;
    private final int prize;
    private final BiFunction<Integer, Boolean, Boolean> condition;
    private final String message;

    private LottoRank(int match, int prize, BiFunction<Integer, Boolean, Boolean> condition, String message) {
        this.match = match;
        this.prize = prize;
        this.condition = condition;
        this.message = message;
    }

    private static final List<LottoRank> ALL_LOTTO_RANK = Arrays.stream(LottoRank.values()).collect(Collectors.toList());

    public static int getPriceByRank(LottoRank rank) {
        return rank.prize;
    }

    public String getMessage() {
        return this.message;
    }

    public static LottoRank getRank(int match, boolean isBonus) {
        return ALL_LOTTO_RANK.stream()
                .filter(lottoRank -> lottoRank.condition(match, isBonus))
                .findAny()
                .orElse(ZERO);
    }

    private boolean condition(int match, boolean isBonus) {
        return this.condition.apply(match, isBonus);
    }

    public static Map<LottoRank, Integer> initialize() {
        Map<LottoRank, Integer> winnerBoard = new EnumMap<>(LottoRank.class);
        ALL_LOTTO_RANK.forEach(rank -> winnerBoard.put(rank, 0));
        return winnerBoard;
    }
}
