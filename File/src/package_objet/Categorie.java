package package_objet;

import java.io.Serializable;
import java.util.Scanner;
import java.util.stream.IntStream;

public abstract class Categorie implements Serializable {
    protected int selected;

    public abstract String getName();

    public abstract Product[] getProposition();

    public void inputSelection(Scanner scanner) {
        IntStream.range(0, getProposition().length)
                .forEach(i -> System.out.printf("[%d - %s]", i + 1, getProposition()[i]));
        System.out.printf("%nQue souhaitez vous comme %s ? [Saisir le chiffre correspondant]%n", getName());
        selected = InputUtil.inputInteger(scanner, 1, getProposition().length)-1;
    }
    public Product selected(){
        return getProposition()[selected];
    }

    @Override
    public String toString() {
        return selected().toString();
    }
}
