import java.util.*;

public class ItemComparator implements Comparator<Items> {
    @Override
    public int compare(Items o1, Items o2) {
        return o2.getTailleItem() - o1.getTailleItem();
    }
}
