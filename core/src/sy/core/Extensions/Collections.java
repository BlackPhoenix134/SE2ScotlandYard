package sy.core.Extensions;

import java.util.Collection;
import java.util.List;

import sy.core.Globals;

public abstract class Collections {
    public static <T> T getRandomItem(List<T> collection) {
        return collection.get(Globals.RANDOM.nextInt(collection.size()));
    }
}
