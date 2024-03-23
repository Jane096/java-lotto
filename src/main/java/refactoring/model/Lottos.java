package refactoring.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos sumAllLottos(List<Lotto> autoLottos, List<Lotto> manualLottos) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(autoLottos);
        lottos.addAll(manualLottos);

        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(this.lottos);
    }
}
